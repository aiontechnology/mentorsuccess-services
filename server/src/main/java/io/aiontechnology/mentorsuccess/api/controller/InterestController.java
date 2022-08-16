/*
 * Copyright 2020-2022 Aion Technology LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.aiontechnology.mentorsuccess.api.controller;

import io.aiontechnology.mentorsuccess.api.assembler.NameableToStringModelMapper;
import io.aiontechnology.mentorsuccess.entity.reference.Interest;
import io.aiontechnology.mentorsuccess.service.InterestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Controller that vends a REST interface for dealing with interests.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@RestController
@RequestMapping("/api/v1/interests")
@RequiredArgsConstructor
@Slf4j
public class InterestController {

    /** A HATEOAS assembler for interest strings. */
    private final NameableToStringModelMapper<Interest> interestModelAssembler;

    /** Service for interacting with {@link Interest Interests}. */
    private final InterestService interestService;

    /**
     * A REST endpoint for getting all interests.
     *
     * @return A collection of models that represents the interests in the system.
     */
    @GetMapping
    @PreAuthorize("hasAuthority('resource:read')")
    public CollectionModel<String> getInterests() {
        var interestModels = StreamSupport
                .stream(interestService.getAllInterests().spliterator(), false)
                .map(interestModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(interestModels);
    }

    /**
     * The new values are provided as a map from new value to old value. "CREATE_ME" is a special value that means that
     * the new value should be created.
     * {
     * "a": "b"         // b -> a
     * "c": "CREATE_ME" // null -> c
     * }
     *
     * @param updatedInterests
     * @return
     */
    @PutMapping
    @PreAuthorize("hasAuthority('resources:update')")
    public CollectionModel<String> setInterests(@RequestBody Map<String, String> updatedInterests) {
        log.debug("Updating updatedInterests: {}", updatedInterests);
        interestService.updateInterests(updatedInterests);
        return getInterests();
    }

}
