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

package io.aiontechnology.mentorsuccess.workflow;

import io.aiontechnology.mentorsuccess.model.inbound.student.InboundStudent;
import io.aiontechnology.mentorsuccess.service.StudentRegistrationService;
import lombok.RequiredArgsConstructor;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static io.aiontechnology.mentorsuccess.workflow.RegistrationWorkflowConstants.SCHOOL_ID;
import static io.aiontechnology.mentorsuccess.workflow.RegistrationWorkflowConstants.STUDENT;

@Service("studentRegistrationStoreStudentTask")
@RequiredArgsConstructor
public class StudentRegistrationStoreStudentTask implements JavaDelegate {

    private final StudentRegistrationService studentRegistrationService;

    @Override
    public void execute(DelegateExecution execution) {
        UUID schoolId = execution.getVariable(SCHOOL_ID, UUID.class);
        InboundStudent student = execution.getVariable(STUDENT, InboundStudent.class);

        studentRegistrationService.createStudent(schoolId, student);
    }

}
