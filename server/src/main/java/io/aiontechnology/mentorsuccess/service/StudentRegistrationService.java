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

import io.aiontechnology.atlas.mapping.OneWayMapper;
import io.aiontechnology.mentorsuccess.api.error.NotFoundException;
import io.aiontechnology.mentorsuccess.entity.SchoolSession;
import io.aiontechnology.mentorsuccess.entity.Student;
import io.aiontechnology.mentorsuccess.entity.StudentSchoolSession;
import io.aiontechnology.mentorsuccess.entity.workflow.StudentRegistration;
import io.aiontechnology.mentorsuccess.model.inbound.InboundInvitation;
import io.aiontechnology.mentorsuccess.model.inbound.student.InboundStudent;
import io.aiontechnology.mentorsuccess.model.inbound.student.InboundStudentRegistration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.TaskInfo;
import org.flowable.task.api.TaskQuery;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static io.aiontechnology.mentorsuccess.workflow.RegistrationWorkflowConstants.INVITATION;
import static io.aiontechnology.mentorsuccess.workflow.RegistrationWorkflowConstants.REGISTRATION;
import static io.aiontechnology.mentorsuccess.workflow.RegistrationWorkflowConstants.SCHOOL_ID;
import static io.aiontechnology.mentorsuccess.workflow.RegistrationWorkflowConstants.STUDENT;

@Service
@RequiredArgsConstructor()
@Slf4j
public class StudentRegistrationService {

    // Mappers
    private final OneWayMapper<InboundStudent, Student> studentModelToEntityMapper;

    private final OneWayMapper<InboundStudent, StudentSchoolSession> studentSessionModelToEntityMapper;

    private final OneWayMapper<InboundStudentRegistration, InboundStudent> studentRegistrationToStudentMapper;

    // Services
    private final TaskService taskService;

    private final RuntimeService runtimeService;

    private final SchoolService schoolService;

    private final StudentService studentService;

    @Transactional
    public void createStudent(UUID schoolId, InboundStudent inboundStudent) {
        schoolService.getSchoolById(schoolId)
                .ifPresent(school -> {
                    SchoolSession currentSession = school.getCurrentSession();

                    Student student = studentModelToEntityMapper.map(inboundStudent)
                            .orElseThrow(() -> new NotFoundException("Student was not found"));

                    StudentSchoolSession studentSchoolSession = studentSessionModelToEntityMapper.map(inboundStudent)
                            .map(ss -> ss.setSession(currentSession))
                            .orElseThrow(() -> new IllegalStateException("Mapping of student session failed"));

                    student.addStudentSession(studentSchoolSession);
                    school.addStudent(student);
                    studentService.updateStudent(student);
                });
    }

    public Optional<StudentRegistration> findWorkflowById(UUID processId) {
        StudentRegistration studentRegistration = new StudentRegistration();
        TaskQuery query = taskService.createTaskQuery()
                .processInstanceId(processId.toString())
                .includeProcessVariables();
        return query.list().stream()
                .findFirst()
                .map(task -> {
                    studentRegistration.setId(UUID.fromString(task.getProcessInstanceId()));
                    return task;
                })
                .map(TaskInfo::getProcessVariables)
                .map(variables -> {
                    InboundInvitation inboundInvitation = (InboundInvitation) variables.get(INVITATION);
                    studentRegistration.setStudentFirstName(inboundInvitation.getStudentFirstName());
                    studentRegistration.setStudentLastName(inboundInvitation.getStudentLastName());
                    studentRegistration.setParent1FirstName(inboundInvitation.getParent1FirstName());
                    studentRegistration.setParent1LastName(inboundInvitation.getParent1LastName());
                    studentRegistration.setParent1EmailAddress(inboundInvitation.getParent1EmailAddress());
                    return studentRegistration;
                });
    }

    public void processRegistration(UUID schoolId, UUID processId,
            InboundStudentRegistration inboundStudentRegistration) {
        studentRegistrationToStudentMapper.map(inboundStudentRegistration)
                .ifPresent(student -> {
                    completeTask(processId, Map.of(
                            SCHOOL_ID, schoolId,
                            REGISTRATION, inboundStudentRegistration,
                            STUDENT, student
                    ));
                });

    }

    private void completeTask(UUID processId, Map<String, Object> variables) {
        TaskQuery query = taskService.createTaskQuery()
                .processInstanceId(processId.toString())
                .includeProcessVariables();
        query.list().stream()
                .findFirst()
                .ifPresent(task -> {
                    runtimeService.setVariables(processId.toString(), variables);
                    taskService.complete(task.getId());
                });
    }

}
