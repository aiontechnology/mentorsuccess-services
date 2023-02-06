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
import io.aiontechnology.mentorsuccess.workflow.TaskUtilities;
import lombok.RequiredArgsConstructor;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import java.util.Map;

import static io.aiontechnology.mentorsuccess.workflow.RegistrationWorkflowConstants.INVITATION;
import static io.aiontechnology.mentorsuccess.workflow.RegistrationWorkflowConstants.PROGRAM_ADMIN;
import static io.aiontechnology.mentorsuccess.workflow.RegistrationWorkflowConstants.STUDENT;
import static io.aiontechnology.mentorsuccess.workflow.RegistrationWorkflowConstants.TEACHER;

@Service
@RequiredArgsConstructor
public class StartTeacherInfoProcess implements JavaDelegate {

    private final RuntimeService runtimeService;

    private final TaskUtilities taskUtilities;

    @Override
    public void execute(DelegateExecution execution) {
        InboundInvitation invitation = taskUtilities.getRequiredVariable(execution, INVITATION,
                InboundInvitation.class);
        Person programAdmin = taskUtilities.getRequiredVariable(execution, PROGRAM_ADMIN, Person.class);
        Person teacher = taskUtilities.getRequiredVariable(execution, TEACHER, Person.class);
        Student student = taskUtilities.getRequiredVariable(execution, STUDENT, Student.class);
        runtimeService.startProcessInstanceByKey("request-student-info",
                Map.of(
                        INVITATION, invitation,
                        PROGRAM_ADMIN, programAdmin,
                        TEACHER, teacher,
                        STUDENT, student
                ));
    }

}
