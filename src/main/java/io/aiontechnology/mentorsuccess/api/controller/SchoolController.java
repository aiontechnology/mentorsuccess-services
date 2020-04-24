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

import io.aiontechnology.mentorsuccess.api.assembler.SchoolModelAssembler;
import io.aiontechnology.mentorsuccess.api.factory.SchoolFactory;
import io.aiontechnology.mentorsuccess.api.model.SchoolModel;
import io.aiontechnology.mentorsuccess.entity.School;
import io.aiontechnology.mentorsuccess.service.SchoolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Controller for managing schools.
 */
@RestController
@RequestMapping("/api/v1/schools")
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
@Slf4j
public class SchoolController {

    /** Assembler for creating {@link SchoolModel} instances */
    private final SchoolModelAssembler schoolModelAssembler;

    /** Factory for converting {@link SchoolModel} instances to {@link School Schools} */
    private final SchoolFactory schoolFactory;

    /** Service with business logic for schools */
    private final SchoolService schoolService;

    /**
     * A REST endpoint for creating new schools.
     *
     * @param schoolModel The model that represents the desired new school.
     * @return A model that represents the created school.
     */
    @PostMapping()
    public SchoolModel createSchool(@RequestBody SchoolModel schoolModel) {
        return Optional.of(schoolModel)
                .map(schoolFactory::fromModel)
                .map(schoolService::createSchool)
                .map(schoolModelAssembler::toModel)
                .orElseThrow(() -> new IllegalArgumentException("Unable to create school"));
    }

    /**
     * A REST endpoint for retrieving all schools.
     *
     * @return A collection of {@link SchoolModel} instances for all schools.
     */
    @GetMapping
    public CollectionModel<SchoolModel> getAllSchools() {
        List<SchoolModel> schools = StreamSupport.stream(schoolService.getAllSchools().spliterator(), false)
                .map(schoolModelAssembler::toModel)
                .collect(Collectors.toList());
        return new CollectionModel<>(schools);
    }

    /**
     * A REST endpoint for deleting a school.
     *
     * @param id The id of the school to remove.
     */
    @DeleteMapping("/{id}")
    public void removeSchool(@PathVariable("id") UUID id) {
        schoolService.removeSchool(id);
    }

    /**
     * A REST endpoint for updating a school.
     *
     * @param id          The id of the school to update.
     * @param schoolModel The model that represents the updated school.
     * @return A model that represents the school that has been updated.
     */
    @PutMapping("/{id}")
    public SchoolModel updateSchool(@PathVariable("id") UUID id, @RequestBody SchoolModel schoolModel) {
        log.debug("Updating");
        return schoolService.findSchool(id)
                .map(school -> schoolFactory.fromModel(schoolModel, school))
                .map(schoolService::updateSchool)
                .map(schoolModelAssembler::toModel)
                .orElseThrow(() -> new IllegalArgumentException("Unable to update school"));
    }

}
