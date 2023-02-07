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

package io.aiontechnology.mentorsuccess.api.controller;

import io.aiontechnology.mentorsuccess.api.assembler.Assembler;
import io.aiontechnology.mentorsuccess.api.error.NotFoundException;
import io.aiontechnology.mentorsuccess.entity.School;
import io.aiontechnology.mentorsuccess.entity.SchoolSession;
import io.aiontechnology.mentorsuccess.entity.Student;
import io.aiontechnology.mentorsuccess.resource.StudentInformationResource;
import io.aiontechnology.mentorsuccess.service.SchoolService;
import io.aiontechnology.mentorsuccess.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/schools/{schoolId}/students/{studentId}/registrations")
@RequiredArgsConstructor
public class StudentInformationController {

    // Assemblers
    private final Assembler<Student, StudentInformationResource> studentInformationAssembler;

    // Services
    private final SchoolService schoolService;
    private final StudentService studentService;

    @GetMapping("/{registrationId}")
    public StudentInformationResource findRegistration(@PathVariable("schoolId") UUID schoolId,
            @PathVariable("studentId") UUID studentId, @PathVariable("registrationId") UUID registrationId) {
        School school = schoolService.getSchoolById(schoolId)
                .orElseThrow(() -> new NotFoundException("School was not found"));
        SchoolSession currentSession = school.getCurrentSession();
        return studentService.getStudentById(studentId, currentSession)
                .flatMap(studentInformationAssembler::map)
                .orElse(null);
    }

}
