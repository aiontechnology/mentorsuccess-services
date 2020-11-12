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

import io.aiontechnology.atlas.mapping.OneWayMapper;
import io.aiontechnology.atlas.mapping.OneWayUpdateMapper;
import io.aiontechnology.mentorsuccess.api.assembler.LinkProvider;
import io.aiontechnology.mentorsuccess.api.assembler.TeacherModelAssembler;
import io.aiontechnology.mentorsuccess.api.error.NotFoundException;
import io.aiontechnology.mentorsuccess.entity.SchoolPersonRole;
import io.aiontechnology.mentorsuccess.model.inbound.InboundTeacher;
import io.aiontechnology.mentorsuccess.model.outbound.OutboundTeacher;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static io.aiontechnology.mentorsuccess.model.enumeration.RoleType.TEACHER;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * Controller that vends a REST interface for dealing with teachers.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@RestController
@RequestMapping("/api/v1/schools/{schoolId}/teachers")
@RequiredArgsConstructor
@Slf4j
public class TeacherController {

    /** The JPA entity manager */
    private final EntityManager entityManager;

    /** A mapper for converting {@link InboundTeacher} instances to {@link SchoolPersonRole Roles}. */
    private final OneWayMapper<InboundTeacher, SchoolPersonRole> teacherMapper;

    /** An update mapper for converting {@link InboundTeacher} instances to {@link SchoolPersonRole Roles}. */
    private final OneWayUpdateMapper<InboundTeacher, SchoolPersonRole> teacherUpdateMapper;

    /** Service with business logic for schools */
    private final SchoolService schoolService;

    /** Assembler for creating {@link InboundTeacher} instances */
    private final TeacherModelAssembler teacherModelAssembler;

    /** Service with business logic for teachers */
    private final RoleService roleService;
    /** {@link LinkProvider} implementation for teachers. */
    private final LinkProvider<OutboundTeacher, SchoolPersonRole> linkProvider = (teacherModel, role) ->
            Arrays.asList(
                    linkTo(TeacherController.class, role.getSchool().getId()).slash(role.getId()).withSelfRel(),
                    linkTo(SchoolController.class).slash(role.getSchool().getId()).withRel("school")
            );

    /**
     * A REST endpoint for creating a teacher for a particular school.
     *
     * @param schoolId The ID of the school.
     * @param inboundTeacher The model that represents the desired new teacher.
     * @return A model that represents the created teacher.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OutboundTeacher createTeacher(@PathVariable("schoolId") UUID schoolId, @RequestBody @Valid InboundTeacher inboundTeacher) {
        log.debug("Creating teacher: {}", inboundTeacher);
        return schoolService.getSchoolById(schoolId)
                .map(school -> Optional.ofNullable(inboundTeacher)
                        .flatMap(teacherMapper::map)
                        .map(school::addRole)
                        .map(roleService::createRole)
                        .map(role -> teacherModelAssembler.toModel(role, linkProvider))
                        .orElseThrow(() -> new IllegalArgumentException("Unable to create teacher")))
                .orElseThrow(() -> new NotFoundException("School not found"));
    }

    /**
     * A REST endpoint for retrieving all teachers.
     *
     * @param schoolId The id of the school.
     * @return A collection of {@link InboundTeacher} instances for the requested school.
     */
    @GetMapping
    public CollectionModel<OutboundTeacher> getTeachers(@PathVariable("schoolId") UUID schoolId) {
        log.debug("Getting all teachers for school {}", schoolId);
        var session = entityManager.unwrap(Session.class);
        session.enableFilter("roleType").setParameter("type", TEACHER.toString());
        return schoolService.getSchoolById(schoolId)
                .map(school -> school.getRoles().stream()
                        .map(role -> teacherModelAssembler.toModel(role, linkProvider))
                        .collect(Collectors.toList()))
                .map(CollectionModel::of)
                .orElseThrow(() -> new NotFoundException("Requested school not found"));
    }

    /**
     * A REST endpoint for getting a specific teacher for a particular school.
     *
     * @param schoolId The id of the school.
     * @param teacherId The id of the teacher.
     * @return The personnel if it could be found.
     */
    @GetMapping("/{teacherId}")
    public OutboundTeacher getTeacher(@PathVariable("schoolId") UUID schoolId,
            @PathVariable("teacherId") UUID teacherId) {
        return roleService.findRoleById(teacherId)
                .map(role -> teacherModelAssembler.toModel(role, linkProvider))
                .orElseThrow(() -> new NotFoundException("Requested school not found"));
    }

    /**
     * A REST endpoint for updating a teacher.
     *
     * @param schoolId The id of the school.
     * @param teacherId The id of the teacher to update.
     * @param inboundTeacher The model that represents the updated teacher.
     * @return A model that represents the teacher that has been updated.
     */
    @PutMapping("/{teacherId}")
    public OutboundTeacher updateTeacher(@PathVariable("schoolId") UUID schoolId,
            @PathVariable("teacherId") UUID teacherId,
            @RequestBody @Valid InboundTeacher inboundTeacher) {
        return roleService.findRoleById(teacherId)
                .flatMap(role -> teacherUpdateMapper.map(inboundTeacher, role))
                .map(roleService::updateRole)
                .map(role -> teacherModelAssembler.toModel(role, linkProvider))
                .orElseThrow(() -> new IllegalArgumentException("Unable to update personnel"));
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
        roleService.findRoleById(teacherId)
                .ifPresent(roleService::deactivateRole);
    }

}
