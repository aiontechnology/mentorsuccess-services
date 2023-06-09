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

import io.aiontechnology.mentorsuccess.entity.School;
import io.aiontechnology.mentorsuccess.entity.SchoolSession;
import io.aiontechnology.mentorsuccess.entity.Student;
import io.aiontechnology.mentorsuccess.entity.StudentSchoolSession;
import io.aiontechnology.mentorsuccess.service.StudentService;
import io.aiontechnology.mentorsuccess.workflow.TaskUtilities;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreWorkflowIdTask implements JavaDelegate {

    // Services
    private final StudentService studentService;

    // Other
    private final TaskUtilities taskUtilities;

    @Override
    public void execute(DelegateExecution execution) {
        School school = taskUtilities.getSchool(execution).orElseThrow();
        SchoolSession currentSchoolSession = school.getCurrentSession();
        Student student = taskUtilities.getStudent(execution).orElseThrow();
        StudentSchoolSession currentStudentSchoolSession =
                student.findCurrentSessionForStudent(currentSchoolSession).orElseThrow();
        currentStudentSchoolSession.setCompletedInfoFlowId(execution.getProcessInstanceId());
        studentService.updateStudent(student);
    }

}
