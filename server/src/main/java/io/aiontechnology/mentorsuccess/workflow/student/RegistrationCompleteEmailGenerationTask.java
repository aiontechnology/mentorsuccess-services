/*
 * Copyright 2023 Aion Technology LLC
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

package io.aiontechnology.mentorsuccess.workflow.student;

import io.aiontechnology.mentorsuccess.api.mapping.toentity.misc.UriModelToRoleMapper;
import io.aiontechnology.mentorsuccess.entity.Person;
import io.aiontechnology.mentorsuccess.entity.SchoolPersonRole;
import io.aiontechnology.mentorsuccess.model.inbound.student.InboundStudentRegistration;
import io.aiontechnology.mentorsuccess.velocity.RegistrationCompleteEmailGenerator;
import io.aiontechnology.mentorsuccess.workflow.EmailGeneratorSupport;
import io.aiontechnology.mentorsuccess.workflow.TaskUtilities;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static io.aiontechnology.mentorsuccess.workflow.RegistrationWorkflowConstants.PROGRAM_ADMIN;
import static io.aiontechnology.mentorsuccess.workflow.RegistrationWorkflowConstants.REGISTRATION;
import static io.aiontechnology.mentorsuccess.workflow.RegistrationWorkflowConstants.TEACHER_ID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistrationCompleteEmailGenerationTask extends EmailGeneratorSupport {

    private final UriModelToRoleMapper uriToRoleMapper;

    private final RegistrationCompleteEmailGenerator emailGenerator;

    private final TaskUtilities taskUtilities;

    @Override
    protected String getBody(DelegateExecution execution) {
        Person programAdmin = taskUtilities.getRequiredVariable(execution, PROGRAM_ADMIN, Person.class);
        InboundStudentRegistration studentRegistration = taskUtilities.getRequiredVariable(execution, REGISTRATION,
                InboundStudentRegistration.class);
        Optional<Person> teacher = getTeacher(studentRegistration).map(SchoolPersonRole::getPerson);
        return emailGenerator.render(programAdmin.getFullName(),
                teacher.map(Person::getFullName).orElse(""), studentRegistration);
    }

    @Override
    protected String getFrom(DelegateExecution execution) {
        return "do-not-reply@mentorsuccesskids.com";
    }

    @Override
    protected String getSubject(DelegateExecution execution) {
        InboundStudentRegistration studentRegistration = taskUtilities.getRequiredVariable(execution, REGISTRATION,
                InboundStudentRegistration.class);
        return "Completed Registration: " + studentRegistration.getStudentFullName();
    }

    @Override
    protected String getTo(DelegateExecution execution) {
        return taskUtilities.getRequiredVariable(execution, PROGRAM_ADMIN, Person.class).getEmail();
    }

    @Override
    protected void setAdditionalVariables(DelegateExecution execution) {
        InboundStudentRegistration studentRegistration = taskUtilities.getRequiredVariable(execution, REGISTRATION,
                InboundStudentRegistration.class);
        Optional<SchoolPersonRole> teacher = getTeacher(studentRegistration);
        execution.setVariable(TEACHER_ID, teacher
                .map(SchoolPersonRole::getId)
                .map(UUID::toString)
                .orElse(null));
    }

    private Optional<SchoolPersonRole> getTeacher(InboundStudentRegistration studentRegistration) {
        return Optional.ofNullable(studentRegistration.getTeacher())
                .flatMap(uriToRoleMapper::map);
    }

}
