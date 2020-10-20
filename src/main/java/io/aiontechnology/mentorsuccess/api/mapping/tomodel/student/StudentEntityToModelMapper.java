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
import io.aiontechnology.mentorsuccess.api.model.inbound.TeacherModel;
import io.aiontechnology.mentorsuccess.api.model.outbound.student.OutboundStudentModel;
import io.aiontechnology.mentorsuccess.api.model.outbound.student.OutboundEmergencyContactModel;
import io.aiontechnology.mentorsuccess.entity.SchoolPersonRole;
import io.aiontechnology.mentorsuccess.entity.Student;
import io.aiontechnology.mentorsuccess.entity.StudentBehavior;
import io.aiontechnology.mentorsuccess.entity.StudentLeadershipSkill;
import io.aiontechnology.mentorsuccess.entity.StudentLeadershipTrait;
import io.aiontechnology.mentorsuccess.entity.StudentPerson;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
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

    private final OneWayCollectionMapper<StudentBehavior, String> studentBehaviorEntityToModelMapper;

    private final OneWayCollectionMapper<StudentLeadershipSkill, String> studentLeadershipSkillEntityToModelMapper;

    private final OneWayCollectionMapper<StudentLeadershipTrait, String> studentLeadershipTraitEntityToModelMapper;

    private final OneWayCollectionMapper<StudentPerson, OutboundEmergencyContactModel> studentPersonEntityToModelMapper;

    @Qualifier("teacherAssemblerMapperAdaptor")
    private final OneWayMapper<SchoolPersonRole, TeacherModel> teacherEntityToModelMapper;

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
                        .withFirstName(student.getFirstName())
                        .withLastName(student.getLastName())
                        .withGrade(student.getGrade())
                        .withLocation(student.getLocation())
                        .withPreferredTime(student.getPreferredTime())
                        .withMediaReleaseSigned(student.getIsMediaReleaseSigned())
                        .withStudentBehaviors(getStudentBehaviorEntityToModelMapper().map(student.getStudentBehaviors()))
                        .withStudentLeadershipSkills(getStudentLeadershipSkillEntityToModelMapper().map(student.getStudentLeadershipSkills()))
                        .withStudentLeadershipTraits(getStudentLeadershipTraitEntityToModelMapper().map(student.getStudentLeadershipTraits()))
                        .withEmergencyContacts(getStudentPersonEntityToModelMapper().map(student.getStudentPersons()))
                        .withTeacher(getTeacherEntityToModelMapper().map(student.getTeacher())
                                .orElse(null))
                        .build());
    }

}
