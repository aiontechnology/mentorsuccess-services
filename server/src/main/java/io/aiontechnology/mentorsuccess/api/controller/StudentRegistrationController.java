/*
 * Copyright 2021 Aion Technology LLC
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

package io.aiontechnology.mentorsuccess.api.controller;

import io.aiontechnology.mentorsuccess.model.inbound.student.InboundStudentRegistration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * @author Whitney Hunter
 * @since 1.1.0
 */
@RestController
@RequestMapping("/api/v1/schools/{schoolId}")
@RequiredArgsConstructor
@Slf4j
public class StudentRegistrationController {

    /** The Flowable {@link RuntimeService}. */
    private final RuntimeService runtimeService;

    @PostMapping("/registration")
    @ResponseStatus(CREATED)
    @Transactional
    public String registerStudent(@PathVariable("schoolId") UUID schoolId,
            @RequestBody InboundStudentRegistration registration) {
        Map<String, Object> vars = new HashMap<>();
        vars.put("firstName", registration.getFirstName());
        vars.put("lastName", registration.getLastName());
        vars.put("parentEmailAddress", registration.getParentEmailAddress());
        ProcessInstance process = runtimeService.startProcessInstanceByKey("reg", vars);
        return "Done";
    }

}
