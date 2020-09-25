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
import io.aiontechnology.mentorsuccess.api.error.NotFoundException;
import io.aiontechnology.mentorsuccess.api.mapping.OneWayMapper;
import io.aiontechnology.mentorsuccess.api.mapping.OneWayUpdateMapper;
import io.aiontechnology.mentorsuccess.api.model.inbound.PersonnelModel;
import io.aiontechnology.mentorsuccess.entity.SchoolPersonRole;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static io.aiontechnology.mentorsuccess.entity.RoleType.COUNSELOR;
import static io.aiontechnology.mentorsuccess.entity.RoleType.PRINCIPAL;
import static io.aiontechnology.mentorsuccess.entity.RoleType.SOCIAL_WORKER;
import static io.aiontechnology.mentorsuccess.entity.RoleType.STAFF;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * Controller that vends a REST interface for dealing with personnel.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@RestController
@RequestMapping("/api/v1/schools/{schoolId}/personnel")
@RequiredArgsConstructor
@Slf4j
public class PersonnelController {

    /** A mapper for converting {@link PersonnelModel} instances to {@link SchoolPersonRole Roles}. */
    private final OneWayMapper<PersonnelModel, SchoolPersonRole> personnelMapper;

    /** An update mapper for converting {@link PersonnelModel} instances to {@link SchoolPersonRole Roles}. */
    private final OneWayUpdateMapper<PersonnelModel, SchoolPersonRole> personnelUpdateMapper;

    /** Service with business logic for schools */
    private final SchoolService schoolService;

    /** Assembler for creating {@link PersonnelModel} instances */
    private final PersonnelModelAssembler personnelModelAssembler;

    /** Service with business logic for teachers */
    private final RoleService roleService;

    /**
     * A REST endpoint for creating a personnel for a particular school.
     *
     * @param schoolId The id of the school.
     * @param personnelModel The model that represents the desired new personnel.
     * @return A model that represents the created personnel.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonnelModel createPersonnel(@PathVariable("schoolId") UUID schoolId, @RequestBody @Valid PersonnelModel personnelModel) {
        log.debug("Creating personnel: {}", personnelModel);
        return schoolService.getSchoolById(schoolId)
                .map(school -> Optional.ofNullable(personnelModel)
                        .flatMap(personnelMapper::map)
                        .map(school::addRole)
                        .map(roleService::createRole)
                        .map(role -> personnelModelAssembler.toModel(role, linkProvider))
                        .orElseThrow(() -> new IllegalArgumentException("Unable to create personnel")))
                .orElseThrow(() -> new NotFoundException("Requested school not found"));
    }

    /**
     * A REST endpoint for getting all personnel for a particular school.
     *
     * @param schoolId The id of the school.
     * @return A collection of all personnel for the school.
     */
    @GetMapping
    public CollectionModel<PersonnelModel> getAllPersonnel(@PathVariable("schoolId") UUID schoolId) {
        log.debug("Getting all personnel for school {}", schoolId);
        return schoolService.getSchoolById(schoolId)
                .map(school -> school.getRoles().stream()
                        .filter(role -> role.getType().equals(SOCIAL_WORKER) ||
                                role.getType().equals(PRINCIPAL) ||
                                role.getType().equals(COUNSELOR) ||
                                role.getType().equals(STAFF))
                        .map(role -> personnelModelAssembler.toModel(role, linkProvider))
                        .collect(Collectors.toList()))
                .map(teachers -> CollectionModel.of(teachers))
                .orElseThrow(() -> new NotFoundException("Requested school not found"));
    }

    /**
     * A REST endpoint for getting a specific personnel for a particular school.
     *
     * @param schoolId The id of the school.
     * @param personnelId The id of the personnel.
     * @return The personnel if it could be found.
     */
    @GetMapping("/{personnelId}")
    public PersonnelModel getPersonnel(@PathVariable("schoolId") UUID schoolId, @PathVariable("personnelId") UUID personnelId) {
        return roleService.findRoleById(personnelId)
                .map(role -> personnelModelAssembler.toModel(role, linkProvider))
                .orElseThrow(() -> new NotFoundException("Requested school not found"));
    }

    /**
     * A REST endpoint for updating a personnel.
     *
     * @param schoolId The id of the school.
     * @param personnelModel The model that represents the updated personnel.
     * @return A model that represents the personnel that has been updated.
     */
    @PutMapping("/{personnelId}")
    public PersonnelModel updateSchool(@PathVariable("schoolId") UUID schoolId,
            @PathVariable("personnelId") UUID personnelId,
            @RequestBody @Valid PersonnelModel personnelModel) {
        return roleService.findRoleById(personnelId)
                .flatMap(role -> personnelUpdateMapper.map(personnelModel, role))
                .map(roleService::updateRole)
                .map(role -> personnelModelAssembler.toModel(role, linkProvider))
                .orElseThrow(() -> new IllegalArgumentException("Unable to update personnel"));
    }

    /**
     * A REST endpoint to deactivate a specific personnel for particular school.
     *
     * @param schoolId The id of the school.
     * @param personnelId The id of the personnel.
     */
    @DeleteMapping("/{personnelId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivatePersonnel(@PathVariable("schoolId") UUID schoolId, @PathVariable("personnelId") UUID personnelId) {
        log.debug("Deactivating personnel");
        roleService.findRoleById(personnelId)
                .ifPresent(roleService::deactivateRole);
    }

    /** {@link LinkProvider} implementation for personnel. */
    private LinkProvider<PersonnelModel, SchoolPersonRole> linkProvider = (personnelModel, role) ->
            Arrays.asList(
                    linkTo(PersonnelController.class, role.getSchool().getId()).slash(role.getId()).withSelfRel(),
                    linkTo(SchoolController.class).slash(role.getSchool().getId()).withRel("school")
            );

}
