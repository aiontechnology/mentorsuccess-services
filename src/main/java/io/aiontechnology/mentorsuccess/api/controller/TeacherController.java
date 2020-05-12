/*
 * Copyright 2020 Aion Technology LLC
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

import io.aiontechnology.mentorsuccess.api.assembler.LinkProvider;
import io.aiontechnology.mentorsuccess.api.assembler.TeacherModelAssembler;
import io.aiontechnology.mentorsuccess.api.mapping.FromTeacherModelMapper;
import io.aiontechnology.mentorsuccess.api.model.TeacherModel;
import io.aiontechnology.mentorsuccess.entity.Teacher;
import io.aiontechnology.mentorsuccess.service.SchoolService;
import io.aiontechnology.mentorsuccess.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * Controller for managing teachers in a school.
 *
 * @author Whitney Hunter
 */
@RestController
@RequestMapping("/api/v1/schools/{schoolId}/teachers")
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
@Slf4j
public class TeacherController {

    /** Mapper for converting {@link TeacherController} instances to {@link Teacher Teachers}. */
    private final FromTeacherModelMapper fromTeacherModelMapper;

    /** Service with business logic for schools */
    private final SchoolService schoolService;

    /** Assembler for creating {@link TeacherModel} instances */
    private final TeacherModelAssembler teacherModelAssembler;

    /** Service with business logic for teachers */
    private final TeacherService teacherService;

    /**
     * A REST endpoint for creating a teacher for a particular school.
     *
     * @param schoolId The ID of the school.
     * @param teacherModel The model that represents the desired new teacher.
     * @return A model that represents the created teacher.
     */
    @PostMapping
    public TeacherModel createTeacher(@PathVariable("schoolId") UUID schoolId, @RequestBody TeacherModel teacherModel) {
        log.debug("Creating teacher: {}", teacherModel);
        return schoolService.findSchool(schoolId)
                .map(school -> Optional.ofNullable(teacherModel)
                        .map(fromTeacherModelMapper::map)
                        .map(school::addTeacher)
                        .map(teacherService::createTeacher)
                        .map(t -> teacherModelAssembler.toModel(t, linkProvider))
                        .orElseThrow(() -> new IllegalArgumentException("Unable to create teacher")))
                .orElseThrow(() -> new IllegalArgumentException("School not found"));
    }

    @GetMapping
    public CollectionModel<TeacherModel> getTeachers(@PathVariable("schoolId") UUID schoolId) {
        log.debug("Getting all teachers for school {}", schoolId);
        return schoolService.findSchool(schoolId)
                .map(school -> school.getTeachers().stream()
                        .map(t -> teacherModelAssembler.toModel(t, linkProvider))
                        .collect(Collectors.toList()))
                .map(teachers -> new CollectionModel<>(teachers))
                .orElseThrow(() -> new IllegalArgumentException("Requested school not found"));
    }

    @DeleteMapping("/{teacherId}")
    public void deactivateTeacher(@PathVariable("schoolId") UUID studentId, @PathVariable("teacherId") UUID teacherId) {
        log.debug("Deactivating teacher");
        teacherService.findTeacher(teacherId)
                .ifPresent(teacherService::deactivateTeacher);
    }

    private LinkProvider<TeacherModel, Teacher> linkProvider = (teacherModel, teacher) ->
            Arrays.asList(
                    linkTo(TeacherController.class, teacher.getSchool().getId()).slash(teacher.getId()).withSelfRel(),
                    linkTo(SchoolController.class).slash(teacher.getSchool().getId()).withRel("school")
            );

}
