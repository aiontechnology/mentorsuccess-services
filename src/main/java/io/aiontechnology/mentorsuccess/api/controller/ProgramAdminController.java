/*
 * Copyright 2020 Aion Technology LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.aiontechnology.mentorsuccess.api.controller;

import io.aiontechnology.mentorsuccess.api.assembler.LinkProvider;
import io.aiontechnology.mentorsuccess.api.assembler.ProgramAdminModelAssembler;
import io.aiontechnology.mentorsuccess.api.error.NotFoundException;
import io.aiontechnology.mentorsuccess.api.mapping.ProgramAdminMapper;
import io.aiontechnology.mentorsuccess.api.model.ProgramAdminModel;
import io.aiontechnology.mentorsuccess.entity.Role;
import io.aiontechnology.mentorsuccess.service.RoleService;
import io.aiontechnology.mentorsuccess.service.SchoolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static io.aiontechnology.mentorsuccess.entity.Role.RoleType.PROGRAM_ADMIN;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * @author Whitney Hunter
 */
@RestController
@RequestMapping("/api/v1/schools/{schoolId}/programAdmins")
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
@Slf4j
public class ProgramAdminController {

    private final EntityManager entityManager;

    private final ProgramAdminMapper programAdminMapper;

    private final ProgramAdminModelAssembler programAdminModelAssembler;

    private final RoleService roleService;

    private final SchoolService schoolService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProgramAdminModel createProgramAdmin(@PathVariable("schoolId") UUID schoolId, @RequestBody ProgramAdminModel programAdminModel) {
        log.debug("Creating program administrator: {}", programAdminModel);
        return schoolService.getSchool(schoolId)
                .map(school -> Optional.ofNullable(programAdminModel)
                        .map(programAdminMapper::mapModelToEntity)
                        .map(school::addRole)
                        .map(roleService::createRole)
                        .map(role -> programAdminModelAssembler.toModel(role, linkProvider))
                        .orElseThrow(() -> new IllegalArgumentException("Unable to create program administrator"))
                )
                .orElseThrow(() -> new NotFoundException("School not found"));

    }

    @GetMapping
    public CollectionModel<ProgramAdminModel> getProgramAdmins(@PathVariable("schoolId") UUID schoolId) {
        log.debug("Getting all program admins for school {}", schoolId);
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("roleType").setParameter("type", PROGRAM_ADMIN.toString());
        return schoolService.getSchool(schoolId)
                .map(school -> school.getRoles().stream()
                        .map(role -> programAdminModelAssembler.toModel(role, linkProvider))
                        .collect(Collectors.toList()))
                .map(programAdmins -> CollectionModel.of(programAdmins))
                .orElseThrow(() -> new IllegalArgumentException("Requested school not found"));
    }

    private LinkProvider<ProgramAdminModel, Role> linkProvider = (programAdminModel, role) ->
            Arrays.asList(
                    linkTo(TeacherController.class, role.getSchool().getId()).slash(role.getId()).withSelfRel(),
                    linkTo(SchoolController.class).slash(role.getSchool().getId()).withRel("school")
            );

}
