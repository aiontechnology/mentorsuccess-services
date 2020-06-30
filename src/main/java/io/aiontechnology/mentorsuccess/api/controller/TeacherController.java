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
import io.aiontechnology.mentorsuccess.api.error.NotFoundException;
import io.aiontechnology.mentorsuccess.api.mapping.TeacherMapper;
import io.aiontechnology.mentorsuccess.api.model.TeacherModel;
import io.aiontechnology.mentorsuccess.entity.Role;
import io.aiontechnology.mentorsuccess.service.RoleService;
import io.aiontechnology.mentorsuccess.service.SchoolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static io.aiontechnology.mentorsuccess.entity.Role.RoleType.TEACHER;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * Controller that vends a REST interface for dealing with teachers.
 *
 * @author <a href="mailto:whitney@aiontechnology.io">Whitney Hunter</a>
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/v1/schools/{schoolId}/teachers")
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
@Slf4j
public class TeacherController {

    /** The JPA entity manager */
    private final EntityManager entityManager;

    /** Mapper for converting {@link TeacherModel} instances to {@link Role Roles}. */
    private final TeacherMapper teacherMapper;

    /** Service with business logic for schools */
    private final SchoolService schoolService;

    /** Assembler for creating {@link TeacherModel} instances */
    private final TeacherModelAssembler teacherModelAssembler;

    /** Service with business logic for teachers */
    private final RoleService roleService;

    /**
     * A REST endpoint for creating a teacher for a particular school.
     *
     * @param schoolId The ID of the school.
     * @param teacherModel The model that represents the desired new teacher.
     * @return A model that represents the created teacher.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeacherModel createTeacher(@PathVariable("schoolId") UUID schoolId, @RequestBody @Valid TeacherModel teacherModel) {
        log.debug("Creating teacher: {}", teacherModel);
        return schoolService.getSchool(schoolId)
                .map(school -> Optional.ofNullable(teacherModel)
                        .map(teacherMapper::mapModelToEntity)
                        .map(school::addRole)
                        .map(roleService::createRole)
                        .map(role -> teacherModelAssembler.toModel(role, linkProvider))
                        .orElseThrow(() -> new IllegalArgumentException("Unable to create teacher")))
                .orElseThrow(() -> new IllegalArgumentException("School not found"));
    }

    /**
     * A REST endpoint for retrieving all teachers.
     *
     * @return A collection of {@link TeacherModel} instances for the requested school.
     */
    @GetMapping
    public CollectionModel<TeacherModel> getTeachers(@PathVariable("schoolId") UUID schoolId) {
        log.debug("Getting all teachers for school {}", schoolId);
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("roleType").setParameter("type", TEACHER.toString());
        return schoolService.getSchool(schoolId)
                .map(school -> school.getRoles().stream()
                        .map(role -> teacherModelAssembler.toModel(role, linkProvider))
                        .collect(Collectors.toList()))
                .map(teachers -> CollectionModel.of(teachers))
                .orElseThrow(() -> new IllegalArgumentException("Requested school not found"));
    }

    /**
     * A REST endpoint for getting a specific teacher for a particular school.
     *
     * @param schoolId The id of the school.
     * @param teacherId The id of the teacher.
     * @return The personnel if it could be found.
     */
    @GetMapping("/{teacherId}")
    public TeacherModel getPersonnel(@PathVariable("schoolId") UUID schoolId, @PathVariable("teacherId") UUID teacherId) {
        return roleService.findRole(teacherId)
                .map(role -> teacherModelAssembler.toModel(role, linkProvider))
                .orElseThrow(() -> new NotFoundException("Requested school not found"));
    }

    /**
     * A REST endpoint for deactivating a teacher.
     *
     * @param studentId The school from which the teacher should be deactivated.
     * @param teacherId The id of the teacher to remove.
     */
    @DeleteMapping("/{teacherId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivateTeacher(@PathVariable("schoolId") UUID studentId, @PathVariable("teacherId") UUID teacherId) {
        log.debug("Deactivating teacher");
        roleService.findRole(teacherId)
                .ifPresent(roleService::deactivateRole);
    }

    private LinkProvider<TeacherModel, Role> linkProvider = (teacherModel, role) ->
            Arrays.asList(
                    linkTo(TeacherController.class, role.getSchool().getId()).slash(role.getId()).withSelfRel(),
                    linkTo(SchoolController.class).slash(role.getSchool().getId()).withRel("school")
            );

}
