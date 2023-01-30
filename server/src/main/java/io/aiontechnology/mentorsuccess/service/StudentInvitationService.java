/*
 * Copyright 2022-2023 Aion Technology LLC
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

package io.aiontechnology.mentorsuccess.service;

import io.aiontechnology.mentorsuccess.entity.Person;
import io.aiontechnology.mentorsuccess.entity.School;
import io.aiontechnology.mentorsuccess.model.enumeration.RoleType;
import io.aiontechnology.mentorsuccess.model.inbound.InboundInvitation;
import io.aiontechnology.mentorsuccess.util.PhoneService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RuntimeService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static io.aiontechnology.mentorsuccess.workflow.RegistrationWorkflowConstants.EMAIL;
import static io.aiontechnology.mentorsuccess.workflow.RegistrationWorkflowConstants.INVITATION;
import static io.aiontechnology.mentorsuccess.workflow.RegistrationWorkflowConstants.PROGRAM_ADMIN;
import static io.aiontechnology.mentorsuccess.workflow.RegistrationWorkflowConstants.SCHOOL;

@Service
@RequiredArgsConstructor()
@Slf4j
public class StudentInvitationService {

    private final PhoneService phoneService;

    // Services
    private final RuntimeService runtimeService;

    public void invite(InboundInvitation invitation, School school) {
        Map<String, Object> variables = setProcessVariables(invitation, school);
        runtimeService.startProcessInstanceByKey("student-registration", variables);
    }

    private Map<String, Object> getEmailConfiguration() {
        return Map.of(
                "subject", "His Heart Foundation - MentorSuccess™ Student Registration",
                "timeout", "P7D"
        );
    }

    private Map<String, Object> getProgramAdminInfo(School school) {
        Optional<Person> programAdmin = school.getRoles().stream()
                .filter(role -> role.getType().equals(RoleType.PROGRAM_ADMIN))
                .findFirst().map(role -> role.getPerson());
        return Map.of(
                "name", programAdmin.map(person -> person.getFullName()).orElse(""),
                "email", programAdmin.map(person -> person.getEmail()).orElse("do-not-reply@mentorsuccesskids.com"),
                "phone", programAdmin.map(person -> person.getCellPhone()).map(phoneService::format).orElse("")
        );
    }

    private Map<String, Object> setProcessVariables(InboundInvitation invitation, School school) {
        Map<String, Object> variables = new HashMap<>();
        variables.put(SCHOOL, school);
        variables.put(PROGRAM_ADMIN, getProgramAdminInfo(school));
        variables.put(INVITATION, invitation);
        variables.put(EMAIL, getEmailConfiguration());
        return variables;
    }

}
