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
import io.aiontechnology.mentorsuccess.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Collection;
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
public class SchoolController {

    private final SchoolModelAssembler schoolModelAssembler;

    private final SchoolFactory schoolFactory;

    private final SchoolService schoolService;

    @PostMapping()
    public SchoolModel createSchool(@RequestBody SchoolModel schoolModel) {
        return Optional.of(schoolModel)
                .map(schoolFactory::fromModel)
                .map(schoolService::createSchool)
                .map(schoolModelAssembler::toModel)
                .orElseThrow(() -> new IllegalArgumentException("Unable to create school"));
    }

    @GetMapping
    public Collection<SchoolModel> getAllSchools() {
        return StreamSupport.stream(schoolService.getAllSchools().spliterator(), false)
                .map(schoolModelAssembler::toModel)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void removeSchool(@PathVariable("id") UUID id) {
        schoolService.removeSchool(id);
    }

}
