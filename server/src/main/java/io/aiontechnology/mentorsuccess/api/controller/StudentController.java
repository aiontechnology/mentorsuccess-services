/*
 * Copyright 2020-2021 Aion Technology LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.aiontechnology.mentorsuccess.api.controller;

import io.aiontechnology.atlas.mapping.OneWayMapper;
import io.aiontechnology.atlas.mapping.OneWayUpdateMapper;
import io.aiontechnology.mentorsuccess.api.assembler.LinkProvider;
import io.aiontechnology.mentorsuccess.api.assembler.StudentModelAssembler;
import io.aiontechnology.mentorsuccess.api.error.NotFoundException;
import io.aiontechnology.mentorsuccess.entity.School;
import io.aiontechnology.mentorsuccess.entity.Student;
import io.aiontechnology.mentorsuccess.model.inbound.student.InboundStudent;
import io.aiontechnology.mentorsuccess.model.outbound.student.OutboundStudent;
import io.aiontechnology.mentorsuccess.service.SchoolService;
import io.aiontechnology.mentorsuccess.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * Controller that vends a REST interface for dealing with students.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
@RestController
@RequestMapping("/api/v1/schools/{schoolId}/students")
@RequiredArgsConstructor
@Slf4j
public class StudentController {

    /** Assembler for creating {@link OutboundStudent} instances */
    private final StudentModelAssembler studentModelAssembler;

    /** Mapper for converting {@link OutboundStudent} instances to {@link Student} */
    private final OneWayMapper<InboundStudent, Student> studentModelToEntityMapper;

    private final OneWayUpdateMapper<InboundStudent, Student> studentModelToEntityUpdateMapper;

    /** Service with business logic for schools */
    private final SchoolService schoolService;

    /** Service with business logic for students */
    private final StudentService studentService;
    /** {@link LinkProvider} implementation for schools. */
    private final LinkProvider<OutboundStudent, Student> linkProvider = (studentModel, student) ->
            Arrays.asList(
                    linkTo(StudentController.class, student.getSchool().getId()).slash(student.getId()).withSelfRel()
            );

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    @PreAuthorize("hasAuthority('student:create')")
    public OutboundStudent createStudent(@PathVariable("schoolId") UUID schoolId,
            @RequestBody @Valid InboundStudent inboundStudent) {
        log.debug("Creating student {}, for school {}", inboundStudent, schoolId);
        School school = schoolService.getSchoolById(schoolId)
                .orElseThrow(() -> new NotFoundException("School was not found"));

        return Optional.ofNullable(inboundStudent)
                .flatMap(studentModelToEntityMapper::map)
                .map(school::addStudent)
                .map(studentService::updateStudent)
                .map(s -> studentModelAssembler.toModel(s, linkProvider))
                .orElseThrow(() -> new IllegalArgumentException(("Unable to create student")));
    }

    /**
     * A REST endpoint for retrieving all students for a school.
     *
     * @param schoolId The id of the school.
     * @return A collection of {@link OutboundStudent} instances for the given school.
     */
    @GetMapping
    @PreAuthorize("hasAuthority('student:read')")
    public CollectionModel<OutboundStudent> getAllStudentsForSchool(@PathVariable("schoolId") UUID schoolId) {
        log.debug("Getting all students for school: {}", schoolId);
        Collection<Student> students = schoolService.getSchoolById(schoolId)
                .map(School::getStudents)
                .orElse(Collections.EMPTY_LIST);
        Collection<OutboundStudent> studentModels = students.stream()
                .map(s -> studentModelAssembler.toModel(s, linkProvider))
                .collect(Collectors.toList());
        return CollectionModel.of(studentModels);
    }

    @GetMapping("/{studentId}")
    @PreAuthorize("hasAuthority('student:read')")
    public OutboundStudent getStudent(@PathVariable("schoolId") UUID schoolId, @PathVariable("studentId") UUID studentId) {
        return studentService.getStudentById(studentId)
                .map(s -> studentModelAssembler.toModel(s, linkProvider))
                .orElseThrow(() -> new NotFoundException("Student was not found"));
    }

    @PutMapping("/{studentId}")
    @PreAuthorize("hasAuthority('student:update')")
    public OutboundStudent updateStudent(@PathVariable("schoolId") UUID schoolId,
            @PathVariable("studentId") UUID studentId, @RequestBody @Valid InboundStudent inboundStudent) {
        Student student = studentService.getStudentById(studentId)
                .orElseThrow(() -> new NotFoundException("Student was not found"));

        return Optional.ofNullable(inboundStudent)
                .flatMap(inbound -> studentModelToEntityUpdateMapper.map(inbound, student))
                .map(studentService::updateStudent)
                .map(s -> studentModelAssembler.toModel(s, linkProvider))
                .orElseThrow(() -> new IllegalArgumentException(("Unable to update student")));
    }

    @DeleteMapping("/{studentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('student:delete')")
    public void deactivateStudent(@PathVariable("schoolId") UUID schoolId, @PathVariable("studentId") UUID studentId) {
        studentService.getStudentById(studentId)
                .ifPresent(studentService::deactivateStudent);
    }

}
