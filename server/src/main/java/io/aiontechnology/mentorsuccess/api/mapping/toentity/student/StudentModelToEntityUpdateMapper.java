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

package io.aiontechnology.mentorsuccess.api.mapping.toentity.student;

import io.aiontechnology.atlas.mapping.OneWayCollectionMapper;
import io.aiontechnology.atlas.mapping.OneWayCollectionUpdateMapper;
import io.aiontechnology.atlas.mapping.OneWayMapper;
import io.aiontechnology.atlas.mapping.OneWayToCollectionUpdateMapper;
import io.aiontechnology.atlas.mapping.OneWayUpdateMapper;
import io.aiontechnology.mentorsuccess.entity.Student;
import io.aiontechnology.mentorsuccess.entity.StudentBehavior;
import io.aiontechnology.mentorsuccess.entity.StudentLeadershipSkill;
import io.aiontechnology.mentorsuccess.entity.StudentLeadershipTrait;
import io.aiontechnology.mentorsuccess.entity.StudentMentor;
import io.aiontechnology.mentorsuccess.entity.StudentPersonRole;
import io.aiontechnology.mentorsuccess.entity.StudentTeacher;
import io.aiontechnology.mentorsuccess.entity.reference.Interest;
import io.aiontechnology.mentorsuccess.model.inbound.student.InboundContact;
import io.aiontechnology.mentorsuccess.model.inbound.student.InboundStudent;
import io.aiontechnology.mentorsuccess.model.inbound.student.InboundStudentBehavior;
import io.aiontechnology.mentorsuccess.model.inbound.student.InboundStudentLeadershipSkill;
import io.aiontechnology.mentorsuccess.model.inbound.student.InboundStudentLeadershipTrait;
import io.aiontechnology.mentorsuccess.model.inbound.student.InboundStudentMentor;
import io.aiontechnology.mentorsuccess.model.inbound.student.InboundStudentTeacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Map an {@link InboundStudent} instance to a {@link Student} entity.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
@Component
@RequiredArgsConstructor
public class StudentModelToEntityUpdateMapper implements OneWayUpdateMapper<InboundStudent, Student> {

    private final OneWayCollectionMapper<String, Interest> interestModelToEntityMapper;

    private final OneWayToCollectionUpdateMapper<InboundStudentBehavior, StudentBehavior> studentBehaviorModelToEntityMapper;

    private final OneWayToCollectionUpdateMapper<InboundStudentLeadershipSkill, StudentLeadershipSkill> studentLeadershipSkillModelToEntityMapper;

    private final OneWayToCollectionUpdateMapper<InboundStudentLeadershipTrait, StudentLeadershipTrait> studentLeadershipTraitModelToEntityMapper;

    private final OneWayCollectionUpdateMapper<InboundContact, StudentPersonRole> studentPersonModelToEntityMapper;

    private final OneWayUpdateMapper<InboundStudentTeacher, StudentTeacher> studentTeacherModelToEntityMapper;

    private final OneWayUpdateMapper<InboundStudentMentor, StudentMentor> studentMentorModelToEntityMapper;

    /**
     * Map the set of behavior strings in the {@link InboundStudent} into a new
     * {@link InboundStudentBehavior} assuming that the behaviors were provided by the student's teacher.
     */
    private final OneWayMapper<InboundStudent, InboundStudentBehavior> studentBehaviorModelMapper =
            inboundStudent -> Optional.of(inboundStudent)
                    .map(InboundStudent::getBehaviors)
                    .map(behaviorModels -> InboundStudentBehavior.builder()
                            .withBehaviors(behaviorModels)
                            .withTeacher(inboundStudent.getTeacher().getUri())
                            .build());

    /**
     * Map the set of leadership skill strings in the {@link InboundStudent} into a new
     * {@link InboundStudentLeadershipSkill} assuming that the skills were provided by the student's teacher.
     */
    private final OneWayMapper<InboundStudent, InboundStudentLeadershipSkill> studentLeadershipSkillModelMapper =
            inboundStudent -> Optional.of(inboundStudent)
                    .map(InboundStudent::getLeadershipSkills)
                    .map(leadershipSkillModels -> InboundStudentLeadershipSkill.builder()
                            .withLeadershipSkills(leadershipSkillModels)
                            .withTeacher(inboundStudent.getTeacher().getUri())
                            .build());

    /**
     * Map the set of leadership trait strings in the {@link InboundStudent} into a new
     * {@link InboundStudentLeadershipTrait} assuming that the skills were provided by the student's teacher.
     */
    private final OneWayMapper<InboundStudent, InboundStudentLeadershipTrait> studentLeadershipTraitModelMapper =
            inboundStudent -> Optional.of(inboundStudent)
                    .map(InboundStudent::getLeadershipTraits)
                    .map(leadershipTraitModels -> InboundStudentLeadershipTrait.builder()
                            .withLeadershipTraits(leadershipTraitModels)
                            .withTeacher(inboundStudent.getTeacher().getUri())
                            .build());

    /**
     * Map the given {@link InboundStudent} to the given {@link Student}.
     *
     * @param inboundStudent The {@link InboundStudent} to map from.
     * @param student The {@link Student} to map to.
     * @return The mapped {@link Student}.
     */
    @Override
    public Optional<Student> map(InboundStudent inboundStudent, Student student) {
        return Optional.ofNullable(inboundStudent)
                .map(s -> {
                    student.setFirstName(s.getFirstName());
                    student.setLastName(s.getLastName());
                    student.setGrade(s.getGrade());
                    student.setPreferredTime(s.getPreferredTime());
                    student.setStartDate(s.getStartDate());
                    student.setLocation(s.getLocation());
                    student.setIsMediaReleaseSigned(s.getMediaReleaseSigned());
                    student.setIsActive(true);
                    student.setInterests(interestModelToEntityMapper.map(s.getInterests()));
                    studentBehaviorModelMapper
                            .map(s)
                            .ifPresent(inboundStudentBehavior ->
                                    student.setStudentBehaviors(studentBehaviorModelToEntityMapper
                                            .map(inboundStudentBehavior, student.getStudentBehaviors())));
                    studentLeadershipSkillModelMapper
                            .map(s)
                            .ifPresent(inboundStudentBehaviorModel ->
                                    student.setStudentLeadershipSkills(studentLeadershipSkillModelToEntityMapper
                                            .map(inboundStudentBehaviorModel, student.getStudentLeadershipSkills())));
                    studentLeadershipTraitModelMapper
                            .map(inboundStudent)
                            .ifPresent(inboundStudentBehaviorModel ->
                                    student.setStudentLeadershipTraits(studentLeadershipTraitModelToEntityMapper
                                            .map(inboundStudentBehaviorModel, student.getStudentLeadershipTraits())));
                    student.setStudentPersonRoles(studentPersonModelToEntityMapper
                            .map(s.getContacts(), student.getStudentPersonRoles()));
                    if (student.getTeacher() == null) {
                        student.setTeacher(new StudentTeacher());
                    }
                    student.setTeacher(studentTeacherModelToEntityMapper.map(s.getTeacher(), student.getTeacher())
                            .orElse(null));
                    if (student.getMentor() == null) {
                        student.setMentor(new StudentMentor());
                    }
                    student.setMentor(studentMentorModelToEntityMapper.map(s.getMentor(), student.getMentor())
                            .orElse(null));
                    return student;
                });
    }

}
