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

package io.aiontechnology.mentorsuccess.workflow.teacher;

import io.aiontechnology.mentorsuccess.entity.Person;
import io.aiontechnology.mentorsuccess.entity.Student;
import io.aiontechnology.mentorsuccess.model.inbound.InboundInvitation;
import io.aiontechnology.mentorsuccess.util.UriBuilder;
import io.aiontechnology.mentorsuccess.velocity.TeacherInvitationEmailGenerator;
import io.aiontechnology.mentorsuccess.workflow.EmailGeneratorSupport;
import io.aiontechnology.mentorsuccess.workflow.TaskUtilities;
import lombok.RequiredArgsConstructor;
import org.flowable.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Service;

import static io.aiontechnology.mentorsuccess.workflow.RegistrationWorkflowConstants.INVITATION;
import static io.aiontechnology.mentorsuccess.workflow.RegistrationWorkflowConstants.PROGRAM_ADMIN;
import static io.aiontechnology.mentorsuccess.workflow.RegistrationWorkflowConstants.STUDENT;
import static io.aiontechnology.mentorsuccess.workflow.RegistrationWorkflowConstants.TEACHER;

@Service
@RequiredArgsConstructor
public class InformationEmailGenerationTask extends EmailGeneratorSupport {

    private final TeacherInvitationEmailGenerator emailGenerator;

    private final TaskUtilities taskUtilities;

    @Override
    protected String getBody(DelegateExecution execution) {
        InboundInvitation invitation = taskUtilities.getRequiredVariable(execution, INVITATION,
                InboundInvitation.class);
        Person programAdmin = taskUtilities.getRequiredVariable(execution, PROGRAM_ADMIN, Person.class);
        Person teacher = taskUtilities.getRequiredVariable(execution, TEACHER, Person.class);
        Student student = taskUtilities.getRequiredVariable(execution, STUDENT, Student.class);
        return emailGenerator.render(teacher.getFirstName(), student.getFullName(), programAdmin.getFullName(),
                programAdmin.getEmail(), createInformationUri(execution, student, invitation));
    }

    @Override
    protected String getFrom(DelegateExecution execution) {
        return taskUtilities.getRequiredVariable(execution, PROGRAM_ADMIN, Person.class).getEmail();
    }

    @Override
    protected String getSubject(DelegateExecution execution) {
        return "His Heart Foundation - MentorSuccess™ Student Info Request";
    }

    @Override
    protected String getTo(DelegateExecution execution) {
        return taskUtilities.getRequiredVariable(execution, TEACHER, Person.class).getEmail();
    }

    private String createInformationUri(DelegateExecution execution, Student student, InboundInvitation invitation) {
        return new UriBuilder(invitation.getStudentRegistrationUri())
                .withPathAddition("students")
                .withPathAddition(student.getId().toString())
                .withPathAddition("information")
                .withPathAddition(execution.getProcessInstanceId())
                .build();
    }

}