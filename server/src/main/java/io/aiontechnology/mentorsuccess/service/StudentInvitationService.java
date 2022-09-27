/*
 * Copyright 2022 Aion Technology LLC
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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RuntimeService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static io.aiontechnology.mentorsuccess.workflow.RegistrationWorkflowConstants.EMAIL_SUBJECT;
import static io.aiontechnology.mentorsuccess.workflow.RegistrationWorkflowConstants.INVITATION;
import static io.aiontechnology.mentorsuccess.workflow.RegistrationWorkflowConstants.PROGRAM_ADMIN_EMAIL;
import static io.aiontechnology.mentorsuccess.workflow.RegistrationWorkflowConstants.PROGRAM_ADMIN_NAME;
import static io.aiontechnology.mentorsuccess.workflow.RegistrationWorkflowConstants.SCHOOL;

@Service
@RequiredArgsConstructor()
@Slf4j
public class StudentInvitationService {

    // Services
    private final RuntimeService runtimeService;

    public void invite(InboundInvitation invitation, School school) {
        Optional<Person> programAdmin = school.getRoles().stream()
                .filter(role -> role.getType().equals(RoleType.PROGRAM_ADMIN))
                .findFirst().map(role -> role.getPerson());
        String programAdminName =
                programAdmin.map(person -> person.getFirstName() + " " + person.getLastName()).orElse("");
        String programAdminEmail = programAdmin
                .map(person -> person.getEmail())
                .orElse("");

        Map<String, Object> variables = setProcessVariables(invitation, school, programAdminName,
                programAdminEmail);
        runtimeService.startProcessInstanceByKey("student-registration", variables);
    }

    private Map<String, Object> setProcessVariables(InboundInvitation invitation, School school,
            String programAdminName, String programAdminEmail) {
        Map<String, Object> variables = new HashMap<>();
        variables.put(SCHOOL, school);
        variables.put(PROGRAM_ADMIN_NAME, programAdminName);
        variables.put(PROGRAM_ADMIN_EMAIL, programAdminEmail);
        variables.put(INVITATION, invitation);
        variables.put(EMAIL_SUBJECT, "His Heart Foundation - MentorSuccess™ Student Registration");
        return variables;
    }

}
