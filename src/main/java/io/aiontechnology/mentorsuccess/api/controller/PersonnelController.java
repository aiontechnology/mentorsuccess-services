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
import io.aiontechnology.mentorsuccess.api.assembler.PersonnelModelAssembler;
import io.aiontechnology.mentorsuccess.api.exception.NotFoundException;
import io.aiontechnology.mentorsuccess.api.mapping.FromFromPersonnelMapper;
import io.aiontechnology.mentorsuccess.api.model.PersonnelModel;
import io.aiontechnology.mentorsuccess.entity.Role;
import io.aiontechnology.mentorsuccess.service.RoleService;
import io.aiontechnology.mentorsuccess.service.SchoolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static io.aiontechnology.mentorsuccess.entity.Role.RoleType.PROGRAM_ADMIN;
import static io.aiontechnology.mentorsuccess.entity.Role.RoleType.TEACHER;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * @author Whitney Hunter
 */
@RestController
@RequestMapping("/api/v1/schools/{schoolId}/personnel")
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
@Slf4j
public class PersonnelController {

    /** Mapper for converting {@link PersonnelModel} instances to {@link Role Roles}. */
    private final FromFromPersonnelMapper fromFromPersonnelMapper;

    /** Service with business logic for schools */
    private final SchoolService schoolService;

    /** Assembler for creating {@link PersonnelModel} instances */
    private final PersonnelModelAssembler personnelModelAssembler;

    /** Service with business logic for teachers */
    private final RoleService roleService;

    /**
     * A REST endpoint for creating a teacher for a particular school.
     *
     * @param schoolId The ID of the school.
     * @param personnelModel The model that represents the desired new teacher.
     * @return A model that represents the created teacher.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonnelModel createPersonnel(@PathVariable("schoolId") UUID schoolId, @RequestBody PersonnelModel personnelModel) {
        log.debug("Creating personnel: {}", personnelModel);
        return schoolService.findSchool(schoolId)
                .map(school -> Optional.ofNullable(personnelModel)
                        .map(fromFromPersonnelMapper::map)
                        .map(school::addRole)
                        .map(roleService::createRole)
                        .map(role -> personnelModelAssembler.toModel(role, linkProvider))
                        .orElseThrow(() -> new IllegalArgumentException("Unable to create personnel")))
                .orElseThrow(() -> new NotFoundException("Requested school not found"));
    }

    @GetMapping
    public CollectionModel<PersonnelModel> getAllPersonnel(@PathVariable("schoolId") UUID schoolId) {
        log.debug("Getting all personnel for school {}", schoolId);
        return schoolService.findSchool(schoolId)
                .map(school -> school.getRoles().stream()
                        .map(role -> personnelModelAssembler.toModel(role, linkProvider))
                        .filter(role -> !role.getType().equals(TEACHER))
                        .filter(role -> !role.getType().equals(PROGRAM_ADMIN))
                        .collect(Collectors.toList()))
                .map(teachers -> CollectionModel.of(teachers))
                .orElseThrow(() -> new NotFoundException("Requested school not found"));
    }

    @DeleteMapping("/{personnelId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivatePersonnel(@PathVariable("schoolId") UUID studentId, @PathVariable("personnelId") UUID personnelId) {
        log.debug("Deactivating personnel");
        roleService.findRole(personnelId)
                .ifPresent(roleService::deactivateRole);
    }

    private LinkProvider<PersonnelModel, Role> linkProvider = (personnelModel, role) ->
            Arrays.asList(
                    linkTo(TeacherController.class, role.getSchool().getId()).slash(role.getId()).withSelfRel(),
                    linkTo(SchoolController.class).slash(role.getSchool().getId()).withRel("school")
            );

}
