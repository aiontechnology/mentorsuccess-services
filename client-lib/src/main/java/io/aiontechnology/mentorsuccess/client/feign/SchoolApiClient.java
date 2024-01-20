/*
 * Copyright 2020-2021 Aion Technology LLC
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.aiontechnology.mentorsuccess.client.feign;

import io.aiontechnology.mentorsuccess.model.inbound.InboundSchool;
import io.aiontechnology.mentorsuccess.model.outbound.OutboundBook;
import io.aiontechnology.mentorsuccess.model.outbound.OutboundGame;
import io.aiontechnology.mentorsuccess.model.outbound.OutboundSchool;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

/**
 * Client for the school API.
 *
 * @author Whitney Hunter
 * @since 0.4.0
 */
@FeignClient("schools")
public interface SchoolApiClient {

    /**
     * Send a POST to create a new school.
     *
     * @param school The new school.
     * @return The created school.
     */
    @PostMapping(value = "/api/v1/schools")
    OutboundSchool createSchool(InboundSchool school);

    /**
     * Send a GET to retrieve a single school.
     *
     * @param shoolId The id of the desired school.
     * @return The school.
     */
    @GetMapping(value = "/api/v1/schools/{schoolId}")
    OutboundSchool getSchool(@PathVariable("schoolId") UUID shoolId);

    @GetMapping(value = "/api/v1/schools/{schoolId}/books")
    CollectionModel<OutboundBook> getBooksForSchool(@PathVariable("schoolId") UUID schoolId);

    @GetMapping(value = "/api/v1/schools/{schoolId}/games")
    CollectionModel<OutboundGame> getGamesForSchool(@PathVariable("schoolId") UUID schoolId);

}
