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

import io.aiontechnology.mentorsuccess.api.assembler.ActivityFocusModelAssembler;
import io.aiontechnology.mentorsuccess.api.model.inbound.ActivityFocusModel;
import io.aiontechnology.mentorsuccess.entity.ActivityFocus;
import io.aiontechnology.mentorsuccess.service.ActivityFocusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Controller that vends a REST interface for dealing with activity focuses.
 *
 * @author Whitney Hunter
 * @since 0.2.0
 */
@RestController
@RequestMapping("/api/v1/activityfocuses")
@RequiredArgsConstructor
@Slf4j
public class ActivityFocusController {

    /** A HATEOAS assembler for {@link ActivityFocusModel ActivityFocusModels}. */
    private final ActivityFocusModelAssembler activityFocusModelAssembler;

    /** Service for interacting with {@link ActivityFocus IActivityFocuss}. */
    private final ActivityFocusService activityFocusService;

    /**
     * A REST endpoint for getting all activity focuses.
     *
     * @return A collection of models that represents the activity focuses in the system.
     */
    @GetMapping
    public CollectionModel<ActivityFocusModel> getInterests() {
        var activityFocusModels = StreamSupport.stream(activityFocusService.getAllActivityFocuses().spliterator(), false)
                .map(activityFocusModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(activityFocusModels);
    }

}
