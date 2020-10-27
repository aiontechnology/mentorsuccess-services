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

import io.aiontechnology.mentorsuccess.api.mapping.OneWayCollectionMapper;
import io.aiontechnology.mentorsuccess.api.mapping.OneWayCollectionUpdateMapper;
import io.aiontechnology.mentorsuccess.api.mapping.OneWayMapper;
import io.aiontechnology.mentorsuccess.api.mapping.OneWayToCollectionUpdateMapper;
import io.aiontechnology.mentorsuccess.api.mapping.OneWayUpdateMapper;
import io.aiontechnology.mentorsuccess.api.model.inbound.reference.BehaviorModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.reference.InterestModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.reference.LeadershipSkillModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.reference.LeadershipTraitModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.student.InboundContactModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.student.InboundStudentBehaviorModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.student.InboundStudentLeadershipSkillModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.student.InboundStudentLeadershipTraitModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.student.InboundStudentModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.student.InboundStudentTeacherModel;
import io.aiontechnology.mentorsuccess.entity.Student;
import io.aiontechnology.mentorsuccess.entity.StudentBehavior;
import io.aiontechnology.mentorsuccess.entity.StudentLeadershipSkill;
import io.aiontechnology.mentorsuccess.entity.StudentLeadershipTrait;
import io.aiontechnology.mentorsuccess.entity.StudentPersonRole;
import io.aiontechnology.mentorsuccess.entity.StudentStaff;
import io.aiontechnology.mentorsuccess.entity.reference.Interest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Map an {@link InboundStudentModel} instance to a {@link Student} entity.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
@Component
@RequiredArgsConstructor
public class StudentModelToEntityUpdateMapper implements OneWayUpdateMapper<InboundStudentModel, Student> {

    private final OneWayCollectionMapper<InterestModel, Interest> interestModelToEntityMapper;

    private final OneWayToCollectionUpdateMapper<InboundStudentBehaviorModel, StudentBehavior> studentBehaviorModelToEntityMapper;

    private final OneWayToCollectionUpdateMapper<InboundStudentLeadershipSkillModel, StudentLeadershipSkill> studentLeadershipSkillModelToEntityMapper;

    private final OneWayToCollectionUpdateMapper<InboundStudentLeadershipTraitModel, StudentLeadershipTrait> studentLeadershipTraitModelToEntityMapper;

    private final OneWayCollectionUpdateMapper<InboundContactModel, StudentPersonRole> studentPersonModelToEntityMapper;

    private final OneWayUpdateMapper<InboundStudentTeacherModel, StudentStaff> studentTeacherModelToEntityMapper;

    /**
     * Map the set of {@link BehaviorModel} instances in the {@link InboundStudentModel} into a new
     * {@link InboundStudentBehaviorModel} assuming that the behaviors were provided by the student's teacher.
     */
    private final OneWayMapper<InboundStudentModel, InboundStudentBehaviorModel> studentBehaviorModelMapper =
            inboundStudentModel -> Optional.of(inboundStudentModel)
                    .map(InboundStudentModel::getBehaviors)
                    .map(behaviorModels -> new InboundStudentBehaviorModel(behaviorModels,
                            inboundStudentModel.getTeacher().getUri()));

    /**
     * Map the set of {@link LeadershipSkillModel} instances in the {@link InboundStudentModel} into a new
     * {@link InboundStudentLeadershipSkillModel} assuming that the skills were provided by the student's teacher.
     */
    private final OneWayMapper<InboundStudentModel, InboundStudentLeadershipSkillModel> studentLeadershipSkillModelMapper =
            inboundStudentModel -> Optional.of(inboundStudentModel)
                    .map(InboundStudentModel::getLeadershipSkills)
                    .map(leadershipSkillModels -> new InboundStudentLeadershipSkillModel(leadershipSkillModels,
                            inboundStudentModel.getTeacher().getUri()));

    /**
     * Map the set of {@link LeadershipTraitModel} instances in the {@link InboundStudentModel} into a new
     * {@link InboundStudentLeadershipTraitModel} assuming that the skills were provided by the student's teacher.
     */
    private final OneWayMapper<InboundStudentModel, InboundStudentLeadershipTraitModel> studentLeadershipTraitModelMapper =
            inboundStudentModel -> Optional.of(inboundStudentModel)
                    .map(InboundStudentModel::getLeadershipTraits)
                    .map(leadershipTraitModels -> new InboundStudentLeadershipTraitModel(leadershipTraitModels,
                            inboundStudentModel.getTeacher().getUri()));

    /**
     * Map the given {@link InboundStudentModel} to the given {@link Student}.
     *
     * @param inboundStudentModel The {@link InboundStudentModel} to map from.
     * @param student The {@link Student} to map to.
     * @return The mapped {@link Student}.
     */
    @Override
    public Optional<Student> map(InboundStudentModel inboundStudentModel, Student student) {
        return Optional.ofNullable(inboundStudentModel)
                .map(s -> {
                    student.setFirstName(s.getFirstName());
                    student.setLastName(s.getLastName());
                    student.setGrade(s.getGrade());
                    student.setAllergyInfo(s.getAllergyInfo());
                    student.setPreferredTime(s.getPreferredTime());
                    student.setLocation(s.getLocation());
                    student.setIsMediaReleaseSigned(s.getMediaReleaseSigned());
                    student.setIsActive(true);
                    student.setInterests(interestModelToEntityMapper.map(s.getInterests()));
                    studentBehaviorModelMapper
                            .map(s)
                            .ifPresent(inboundStudentBehaviorModel ->
                                    student.setStudentBehaviors(studentBehaviorModelToEntityMapper
                                            .map(inboundStudentBehaviorModel, student.getStudentBehaviors())));
                    studentLeadershipSkillModelMapper
                            .map(s)
                            .ifPresent(inboundStudentBehaviorModel ->
                                    student.setStudentLeadershipSkills(studentLeadershipSkillModelToEntityMapper
                                            .map(inboundStudentBehaviorModel, student.getStudentLeadershipSkills())));
                    studentLeadershipTraitModelMapper
                            .map(inboundStudentModel)
                            .ifPresent(inboundStudentBehaviorModel ->
                                    student.setStudentLeadershipTraits(studentLeadershipTraitModelToEntityMapper
                                            .map(inboundStudentBehaviorModel, student.getStudentLeadershipTraits())));
                    student.setStudentPersonRoles(studentPersonModelToEntityMapper
                            .map(s.getContacts(), student.getStudentPersonRoles()));
                    student.setTeacher(studentTeacherModelToEntityMapper.map(s.getTeacher(), student.getTeacher())
                            .orElse(null));
                    return student;
                });
    }

}
