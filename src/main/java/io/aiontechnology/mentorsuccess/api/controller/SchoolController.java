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
import io.aiontechnology.mentorsuccess.api.assembler.SchoolModelAssembler;
import io.aiontechnology.mentorsuccess.api.error.NotFoundException;
import io.aiontechnology.mentorsuccess.api.mapping.OneWayMapper;
import io.aiontechnology.mentorsuccess.api.mapping.OneWayUpdateMapper;
import io.aiontechnology.mentorsuccess.api.model.inbound.SchoolModel;
import io.aiontechnology.mentorsuccess.entity.School;
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
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * Controller that vends a REST interface for dealing with schools.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@RestController
@RequestMapping("/api/v1/schools")
@RequiredArgsConstructor
@Slf4j
public class SchoolController {

    /** Assembler for creating {@link SchoolModel} instances */
    private final SchoolModelAssembler schoolModelAssembler;

    /** A mapper for converting {@link SchoolModel} instances to {@link School} */
    private final OneWayMapper<SchoolModel, School> schoolMapper;

    /** An update mapper for converting {@link SchoolModel} instances to {@link School} */
    private final OneWayUpdateMapper<SchoolModel, School> schoolUpdateMapper;

    /** Service with business logic for schools */
    private final SchoolService schoolService;

    /**
     * A REST endpoint for creating new schools.
     *
     * @param schoolModel The model that represents the desired new school.
     * @return A model that represents the created school.
     */
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public SchoolModel createSchool(@RequestBody @Valid SchoolModel schoolModel) {
        log.debug("Creating school: {}", schoolModel);
        return Optional.ofNullable(schoolModel)
                .flatMap(schoolMapper::map)
                .map(schoolService::createSchool)
                .map(s -> schoolModelAssembler.toModel(s, linkProvider))
                .orElseThrow(() -> new IllegalArgumentException("Unable to create school"));
    }

    /**
     * A REST endpoint for retrieving all schools.
     *
     * @return A collection of {@link SchoolModel} instances for all schools.
     */
    @GetMapping
    public CollectionModel<SchoolModel> getAllSchools() {
        log.debug("Getting all schools");
        var schools = StreamSupport.stream(schoolService.getAllSchools().spliterator(), false)
                .map(s -> schoolModelAssembler.toModel(s, linkProvider))
                .collect(Collectors.toList());
        return CollectionModel.of(schools);
    }

    /**
     * A REST endpoint for retrieving a particular school.
     *
     * @param schoolId The id of the desired school
     * @return The school if it is found.
     */
    @GetMapping("/{schoolId}")
    public SchoolModel getSchool(@PathVariable("schoolId") UUID schoolId) {
        log.debug("Getting school with id {}", schoolId);
        return schoolService.getSchoolById(schoolId)
                .map(s -> schoolModelAssembler.toModel(s, linkProvider))
                .orElseThrow(() -> new NotFoundException("School was not found"));
    }

    /**
     * A REST endpoint for updating a school.
     *
     * @param schoolId The id of the school to update.
     * @param schoolModel The model that represents the updated school.
     * @return A model that represents the school that has been updated.
     */
    @PutMapping("/{schoolId}")
    public SchoolModel updateSchool(@PathVariable("schoolId") UUID schoolId, @RequestBody @Valid SchoolModel schoolModel) {
        log.debug("Updating school {} with {}", schoolId, schoolModel);
        return schoolService.getSchoolById(schoolId)
                .flatMap(school -> schoolUpdateMapper.map(schoolModel, school))
                .map(schoolService::updateSchool)
                .map(s -> schoolModelAssembler.toModel(s, linkProvider))
                .orElseThrow(() -> new IllegalArgumentException("Unable to update school"));
    }

    /**
     * A REST endpoint for deleting a school.
     *
     * @param schoolId The id of the school to remove.
     */
    @DeleteMapping("/{schoolId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivateSchool(@PathVariable("schoolId") UUID schoolId) {
        log.debug("Deactivating school: {}", schoolId);
        schoolService.getSchoolById(schoolId)
                .ifPresent(schoolService::deactivateSchool);
    }

    /** {@link LinkProvider} implementation for schools. */
    private LinkProvider<SchoolModel, School> linkProvider = (schoolModel, school) ->
            Arrays.asList(
                    linkTo(SchoolController.class).slash(school.getId()).withSelfRel(),
                    linkTo(SchoolController.class).slash(school.getId()).slash("teachers").withRel("teachers"),
                    linkTo(SchoolController.class).slash(school.getId()).slash("programAdmins").withRel("programAdmins")
            );

}
