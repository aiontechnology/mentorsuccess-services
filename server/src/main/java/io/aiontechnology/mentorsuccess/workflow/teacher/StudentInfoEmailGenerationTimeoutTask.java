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

import io.aiontechnology.mentorsuccess.velocity.StudentInfoTimeoutEmailGenerator;
import io.aiontechnology.mentorsuccess.workflow.EmailGeneratorSupport;
import io.aiontechnology.mentorsuccess.workflow.TaskUtilities;
import lombok.RequiredArgsConstructor;
import org.flowable.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentInfoEmailGenerationTimeoutTask extends EmailGeneratorSupport {

    private final TaskUtilities taskUtilities;
    private final StudentInfoTimeoutEmailGenerator studentInfoTimeoutEmailGenerator;

    @Override
    protected String getBody(DelegateExecution execution) {
        String programAdminName = taskUtilities.getProgramAdminFullName(execution);
        String teacherName = taskUtilities.getTeacherFullName(execution).orElseThrow();
        String studentName = taskUtilities.getStudentFullName(execution).orElseThrow();
        return studentInfoTimeoutEmailGenerator.render(programAdminName, teacherName, studentName);
    }

    @Override
    protected String getFrom(DelegateExecution execution) {
        return "do-not-reply@mentorsuccesskids.com";
    }

    @Override
    protected String getSubject(DelegateExecution execution) {
        String studentName = taskUtilities.getStudentFullName(execution).orElse("");
        return "Student information request timed out for " + studentName;
    }

    @Override
    protected String getTo(DelegateExecution execution) {
        return taskUtilities.getProgramAdminEmail(execution);
    }

}
