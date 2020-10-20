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
import io.aiontechnology.mentorsuccess.api.mapping.OneWayMapper;
import io.aiontechnology.mentorsuccess.api.mapping.OneWayUpdateMapper;
import io.aiontechnology.mentorsuccess.api.model.inbound.reference.ProgramAdminModel;
import io.aiontechnology.mentorsuccess.entity.SchoolPersonRole;
import io.aiontechnology.mentorsuccess.entity.School;
import io.aiontechnology.mentorsuccess.service.RoleService;
import io.aiontechnology.mentorsuccess.service.SchoolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static io.aiontechnology.mentorsuccess.entity.RoleType.PROGRAM_ADMIN;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * Controller that vends a REST interface for dealing with program admins.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@RestController
@RequestMapping("/api/v1/schools/{schoolId}/programAdmins")
@RequiredArgsConstructor
@Slf4j
public class ProgramAdminController {

    /** The JPA entity manager */
    private final EntityManager entityManager;

    /** A mapper between {@link ProgramAdminModel ProgramAdminModels} and {@link SchoolPersonRole Roles}. */
    private final OneWayMapper<ProgramAdminModel, SchoolPersonRole> programAdminMapper;

    /** An update mapper between {@link ProgramAdminModel ProgramAdminModels} and {@link SchoolPersonRole Roles}. */
    private final OneWayUpdateMapper<ProgramAdminModel, SchoolPersonRole> programAdminUpdateMapper;

    /** A HATEOAS assembler for {@link ProgramAdminModel ProgramAdminModels}. */
    private final ProgramAdminModelAssembler programAdminModelAssembler;

    /** Service for interacting with {@link SchoolPersonRole Roles}. */
    private final RoleService roleService;

    /** Service for interacting with {@link School Schools}. */
    private final SchoolService schoolService;

    /**
     * A REST endpoint for creating new program admins.
     *
     * @param schoolId The id of the school for the program admin.
     * @param programAdminModel The model that represents the desired new program admin.
     * @return A model that represents the created program admin.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProgramAdminModel createProgramAdmin(@PathVariable("schoolId") UUID schoolId, @RequestBody @Valid ProgramAdminModel programAdminModel) {
        log.debug("Creating program administrator: {}", programAdminModel);
        return schoolService.getSchoolById(schoolId)
                .map(school -> Optional.ofNullable(programAdminModel)
                        .flatMap(programAdminMapper::map)
                        .map(school::addRole)
                        .map(roleService::createRole)
                        .map(role -> programAdminModelAssembler.toModel(role, linkProvider))
                        .orElseThrow(() -> new IllegalArgumentException("Unable to create program administrator"))
                )
                .orElseThrow(() -> new NotFoundException("School not found"));

    }

    /**
     * A REST endpoint for getting all program admins for a specific school.
     *
     * @param schoolId The id of the desired program admin.
     * @return A collection of {@link ProgramAdminModel} instances.
     */
    @GetMapping
    public CollectionModel<ProgramAdminModel> getProgramAdmins(@PathVariable("schoolId") UUID schoolId) {
        log.debug("Getting all program admins for school {}", schoolId);
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("roleType").setParameter("type", PROGRAM_ADMIN.toString());
        return schoolService.getSchoolById(schoolId)
                .map(school -> school.getRoles().stream()
                        .map(role -> programAdminModelAssembler.toModel(role, linkProvider))
                        .collect(Collectors.toList()))
                .map(programAdmins -> CollectionModel.of(programAdmins))
                .orElseThrow(() -> new IllegalArgumentException("Requested school not found"));
    }

    /**
     * A REST endpoint for getting a specific program admin for a particular school.
     *
     * @param schoolId The id of the school.
     * @param programAdminId The id of the program admin.
     * @return The personnel if it could be found.
     */
    @GetMapping("/{programAdminId}")
    public ProgramAdminModel getPersonnel(@PathVariable("schoolId") UUID schoolId, @PathVariable("programAdminId") UUID programAdminId) {
        return roleService.findRoleById(programAdminId)
                .map(role -> programAdminModelAssembler.toModel(role, linkProvider))
                .orElseThrow(() -> new NotFoundException("Requested school not found"));
    }

    /**
     * A REST endpoint for updating a program admin.
     *
     * @param schoolId The id of the school.
     * @param programAdminId The model that represents the updated program admin.
     * @return A model that represents the program admin that has been updated.
     */
    @PutMapping("/{programAdminId}")
    public ProgramAdminModel updateProgramAdmin(@PathVariable("schoolId") UUID schoolId,
            @PathVariable("programAdminId") UUID programAdminId,
            @RequestBody @Valid ProgramAdminModel programAdminModel) {
        return roleService.findRoleById(programAdminId)
                .flatMap(role -> programAdminUpdateMapper.map(programAdminModel, role))
                .map(roleService::updateRole)
                .map(role -> programAdminModelAssembler.toModel(role, linkProvider))
                .orElseThrow(() -> new IllegalArgumentException("Unable to update personnel"));
    }

    /**
     * A REST endpoint to deactivate a specific program admin for particular school.
     *
     * @param schoolId The id of the school.
     * @param programAdminId The id of the program admin.
     */
    @DeleteMapping("/{programAdminId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivatePersonnel(@PathVariable("schoolId") UUID schoolId, @PathVariable("programAdminId") UUID programAdminId) {
        log.debug("Deactivating personnel");
        roleService.findRoleById(programAdminId)
                .ifPresent(roleService::deactivateRole);
    }

    /** {@link LinkProvider} implementation for program admins. */
    private LinkProvider<ProgramAdminModel, SchoolPersonRole> linkProvider = (programAdminModel, role) ->
            Arrays.asList(
                    linkTo(ProgramAdminController.class, role.getSchool().getId()).slash(role.getId()).withSelfRel(),
                    linkTo(SchoolController.class).slash(role.getSchool().getId()).withRel("school")
            );

}
