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

package io.aiontechnology.mentorsuccess.api.mapping.tomodel.student;

import io.aiontechnology.atlas.mapping.OneWayCollectionMapper;
import io.aiontechnology.atlas.mapping.OneWayMapper;
import io.aiontechnology.mentorsuccess.entity.Student;
import io.aiontechnology.mentorsuccess.entity.StudentBehavior;
import io.aiontechnology.mentorsuccess.entity.StudentLeadershipSkill;
import io.aiontechnology.mentorsuccess.entity.StudentLeadershipTrait;
import io.aiontechnology.mentorsuccess.entity.StudentMentor;
import io.aiontechnology.mentorsuccess.entity.StudentPersonRole;
import io.aiontechnology.mentorsuccess.entity.StudentTeacher;
import io.aiontechnology.mentorsuccess.entity.reference.Interest;
import io.aiontechnology.mentorsuccess.model.outbound.student.OutboundContact;
import io.aiontechnology.mentorsuccess.model.outbound.student.OutboundStudent;
import io.aiontechnology.mentorsuccess.model.outbound.student.OutboundStudentMentor;
import io.aiontechnology.mentorsuccess.model.outbound.student.OutboundStudentTeacher;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Mapper that converts a {@link Student} to a {@link OutboundStudent}.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
@Component
@RequiredArgsConstructor
@Getter(AccessLevel.PRIVATE)
public class StudentEntityToModelMapper implements OneWayMapper<Student, OutboundStudent> {

    private final OneWayCollectionMapper<Interest, String> interestModelToEntityMapper;

    private final OneWayCollectionMapper<StudentBehavior, String> studentBehaviorEntityToModelMapper;

    private final OneWayCollectionMapper<StudentLeadershipSkill, String> studentLeadershipSkillEntityToModelMapper;

    private final OneWayCollectionMapper<StudentLeadershipTrait, String> studentLeadershipTraitEntityToModelMapper;

    private final OneWayCollectionMapper<StudentPersonRole, OutboundContact> studentPersonEntityToModelMapper;

    private final OneWayMapper<StudentTeacher, OutboundStudentTeacher> studentTeacherEntityToModelMapper;

    private final OneWayMapper<StudentMentor, OutboundStudentMentor> studentMentorEntityToModelMapper;

    /**
     * Map the given {@link Student} to a {@link OutboundStudent}.
     *
     * @param student The {@link Student} to map.
     * @return The mapped {@link OutboundStudent}.
     */
    @Override
    public Optional<OutboundStudent> map(Student student) {
        return Optional.ofNullable(student)
                .map(s -> OutboundStudent.builder()
                        .withId(s.getId())
                        .withFirstName(s.getFirstName())
                        .withLastName(s.getLastName())
                        .withGrade(s.getGrade())
                        .withLocation(s.getLocation())
                        .withPreferredTime(s.getPreferredTime())
                        .withStartDate(s.getStartDate())
                        .withMediaReleaseSigned(s.getIsMediaReleaseSigned())
                        .withInterests(getInterestModelToEntityMapper().map(s.getInterests()))
                        .withBehaviors(getStudentBehaviorEntityToModelMapper().map(s.getStudentBehaviors()))
                        .withLeadershipSkills(getStudentLeadershipSkillEntityToModelMapper().map(s.getStudentLeadershipSkills()))
                        .withLeadershipTraits(getStudentLeadershipTraitEntityToModelMapper().map(s.getStudentLeadershipTraits()))
                        .withContacts(getStudentPersonEntityToModelMapper().map(s.getStudentPersonRoles()))
                        .withTeacher(getStudentTeacherEntityToModelMapper().map(s.getTeacher()).orElse(null))
                        .withMentor(getStudentMentorEntityToModelMapper().map(s.getMentor()).orElse(null))
                        .build());
    }

}
