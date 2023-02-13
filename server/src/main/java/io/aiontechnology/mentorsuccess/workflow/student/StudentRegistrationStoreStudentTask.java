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

package io.aiontechnology.mentorsuccess.workflow.student;

import io.aiontechnology.mentorsuccess.entity.Person;
import io.aiontechnology.mentorsuccess.entity.School;
import io.aiontechnology.mentorsuccess.model.inbound.student.InboundStudent;
import io.aiontechnology.mentorsuccess.service.StudentRegistrationService;
import io.aiontechnology.mentorsuccess.workflow.TaskUtilities;
import lombok.RequiredArgsConstructor;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import static io.aiontechnology.mentorsuccess.workflow.RegistrationWorkflowConstants.SCHOOL;
import static io.aiontechnology.mentorsuccess.workflow.RegistrationWorkflowConstants.STUDENT;
import static io.aiontechnology.mentorsuccess.workflow.RegistrationWorkflowConstants.STUDENT_ID;
import static io.aiontechnology.mentorsuccess.workflow.RegistrationWorkflowConstants.TEACHER_ID;

@Service
@RequiredArgsConstructor
public class StudentRegistrationStoreStudentTask implements JavaDelegate {

    private final StudentRegistrationService studentRegistrationService;

    private final TaskUtilities taskUtilities;

    @Override
    public void execute(DelegateExecution execution) {
        School school = taskUtilities.getRequiredVariable(execution, SCHOOL, School.class);
        InboundStudent student = taskUtilities.getRequiredVariable(execution, STUDENT, InboundStudent.class);

        studentRegistrationService.createStudent(school.getId(), student)
                .ifPresent(pair -> {
                    Person teacher = pair.getRight().getTeacher() != null
                            ? pair.getRight().getTeacher().getPerson()
                            : null;
                    execution.setVariable(TEACHER_ID, teacher.getId().toString());
                    execution.setVariable(STUDENT_ID, pair.getLeft().getId().toString());
                });
    }

}
