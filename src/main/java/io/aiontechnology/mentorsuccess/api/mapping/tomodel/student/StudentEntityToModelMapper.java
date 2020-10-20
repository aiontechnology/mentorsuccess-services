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

import io.aiontechnology.mentorsuccess.api.mapping.OneWayCollectionMapper;
import io.aiontechnology.mentorsuccess.api.mapping.OneWayMapper;
import io.aiontechnology.mentorsuccess.api.model.outbound.student.OutboundContactModel;
import io.aiontechnology.mentorsuccess.api.model.outbound.student.OutboundStudentModel;
import io.aiontechnology.mentorsuccess.api.model.outbound.student.OutboundStudentTeacherModel;
import io.aiontechnology.mentorsuccess.entity.Student;
import io.aiontechnology.mentorsuccess.entity.StudentBehavior;
import io.aiontechnology.mentorsuccess.entity.StudentLeadershipSkill;
import io.aiontechnology.mentorsuccess.entity.StudentLeadershipTrait;
import io.aiontechnology.mentorsuccess.entity.StudentPersonRole;
import io.aiontechnology.mentorsuccess.entity.StudentStaff;
import io.aiontechnology.mentorsuccess.entity.reference.Interest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Mapper that converts a {@link Student} to a {@link OutboundStudentModel}.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
@Component
@RequiredArgsConstructor
@Getter(AccessLevel.PRIVATE)
public class StudentEntityToModelMapper implements OneWayMapper<Student, OutboundStudentModel> {

    private final OneWayCollectionMapper<Interest, String> interestModelToEntityMapper;

    private final OneWayCollectionMapper<StudentBehavior, String> studentBehaviorEntityToModelMapper;

    private final OneWayCollectionMapper<StudentLeadershipSkill, String> studentLeadershipSkillEntityToModelMapper;

    private final OneWayCollectionMapper<StudentLeadershipTrait, String> studentLeadershipTraitEntityToModelMapper;

    private final OneWayCollectionMapper<StudentPersonRole, OutboundContactModel> studentPersonEntityToModelMapper;

    private final OneWayMapper<StudentStaff, OutboundStudentTeacherModel> studentStaffEntityToModelMapper;

    /**
     * Map the given {@link Student} to a {@link OutboundStudentModel}.
     *
     * @param student The {@link Student} to map.
     * @return The mapped {@link OutboundStudentModel}.
     */
    @Override
    public Optional<OutboundStudentModel> map(Student student) {
        return Optional.ofNullable(student)
                .map(s -> OutboundStudentModel.builder()
                        .withFirstName(s.getFirstName())
                        .withLastName(s.getLastName())
                        .withGrade(s.getGrade())
                        .withAllergyInfo(s.getAllergyInfo())
                        .withLocation(s.getLocation())
                        .withPreferredTime(s.getPreferredTime())
                        .withMediaReleaseSigned(s.getIsMediaReleaseSigned())
                        .withInterests(getInterestModelToEntityMapper().map(s.getInterests()))
                        .withBehaviors(getStudentBehaviorEntityToModelMapper().map(s.getStudentBehaviors()))
                        .withLeadershipSkills(getStudentLeadershipSkillEntityToModelMapper().map(s.getStudentLeadershipSkills()))
                        .withLeadershipTraits(getStudentLeadershipTraitEntityToModelMapper().map(s.getStudentLeadershipTraits()))
                        .withContacts(getStudentPersonEntityToModelMapper().map(s.getStudentPersonRoles()))
                        .withTeacher(getStudentStaffEntityToModelMapper().map(s.getTeacher()).orElse(null))
                        .build());
    }

}
