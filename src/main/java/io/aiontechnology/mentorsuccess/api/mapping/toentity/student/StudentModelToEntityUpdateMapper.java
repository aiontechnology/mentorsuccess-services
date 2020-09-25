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
import io.aiontechnology.mentorsuccess.api.mapping.OneWayMapper;
import io.aiontechnology.mentorsuccess.api.mapping.OneWayToCollectionUpdateMapper;
import io.aiontechnology.mentorsuccess.api.mapping.OneWayUpdateMapper;
import io.aiontechnology.mentorsuccess.api.model.inbound.reference.BehaviorModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.reference.LeadershipSkillModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.reference.LeadershipTraitModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.student.InboundEmergencyContactModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.student.InboundStudentBehaviorModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.student.InboundStudentLeadershipSkillModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.student.InboundStudentLeadershipTraitModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.student.InboundStudentModel;
import io.aiontechnology.mentorsuccess.entity.SchoolPersonRole;
import io.aiontechnology.mentorsuccess.entity.Student;
import io.aiontechnology.mentorsuccess.entity.StudentBehavior;
import io.aiontechnology.mentorsuccess.entity.StudentLeadershipSkill;
import io.aiontechnology.mentorsuccess.entity.StudentLeadershipTrait;
import io.aiontechnology.mentorsuccess.entity.StudentPerson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author Whitney Hunter
 * @since 0.3.0
 */
@Component
@RequiredArgsConstructor
public class StudentModelToEntityUpdateMapper implements OneWayUpdateMapper<InboundStudentModel, Student> {

    private final OneWayToCollectionUpdateMapper<InboundStudentBehaviorModel, StudentBehavior> studentBehaviorModelToEntityMapper;

    private final OneWayToCollectionUpdateMapper<InboundStudentLeadershipSkillModel, StudentLeadershipSkill> studentLeadershipSkillModelToEntityMapper;

    private final OneWayToCollectionUpdateMapper<InboundStudentLeadershipTraitModel, StudentLeadershipTrait> studentLeadershipTraitModelToEntityMapper;

    private final OneWayCollectionMapper<InboundEmergencyContactModel, StudentPerson> studentPersonModelToEntityMapper;

    private final OneWayMapper<URI, SchoolPersonRole> teacherModelToEntityMapper;

    @Override
    public Optional<Student> map(InboundStudentModel inboundStudentModel, Student student) {
        return Optional.ofNullable(inboundStudentModel)
                .map(s -> {
                    student.setFirstName(inboundStudentModel.getFirstName());
                    student.setLastName(inboundStudentModel.getLastName());
                    student.setGrade(inboundStudentModel.getGrade());
                    student.setPreferredTime(inboundStudentModel.getPreferredTime());
                    student.setLocation(inboundStudentModel.getLocation());
                    student.setIsMediaReleaseSigned(inboundStudentModel.getMediaReleaseSigned());
                    student.setIsActive(true);
                    Optional<InboundStudentBehaviorModel> inboundStudentBehaviorModel =
                            createInboundStudentBehaviorModel(inboundStudentModel);
                    inboundStudentBehaviorModel.ifPresent(i -> student.setStudentBehaviors(
                            studentBehaviorModelToEntityMapper.map(i, student.getStudentBehaviors())));
                    processLeadershipSkills(inboundStudentModel, student);
                    processLeadershipTraits(inboundStudentModel, student);
                    student.setStudentPersons(studentPersonModelToEntityMapper.map(inboundStudentModel.getEmergencyContacts()));
                    student.setTeacher(teacherModelToEntityMapper.map(inboundStudentModel.getTeacher()).orElse(null));
                    return student;
                });
    }

    private Optional<InboundStudentBehaviorModel> createInboundStudentBehaviorModel(InboundStudentModel inboundStudentModel) {
        if (inboundStudentModel.getBehaviors() != null) {
            Set<BehaviorModel> behaviorModels = new HashSet<>(inboundStudentModel.getBehaviors());
            return Optional.of(new InboundStudentBehaviorModel(behaviorModels, inboundStudentModel.getTeacher()));
        }
        return Optional.empty();
    }

    private void processLeadershipSkills(InboundStudentModel inboundStudentModel, Student student) {
        if (inboundStudentModel.getLeadershipSkills() != null) {
            Set<LeadershipSkillModel> behaviorModels = new HashSet<>(inboundStudentModel.getLeadershipSkills());
            InboundStudentLeadershipSkillModel studentLeadershipSkill = new InboundStudentLeadershipSkillModel(
                    behaviorModels, inboundStudentModel.getTeacher());
            student.setStudentLeadershipSkills(studentLeadershipSkillModelToEntityMapper.map(studentLeadershipSkill,
                    student.getStudentLeadershipSkills()));
        }
    }

    private void processLeadershipTraits(InboundStudentModel inboundStudentModel, Student student) {
        if (inboundStudentModel.getLeadershipTraits() != null) {
            Set<LeadershipTraitModel> behaviorModels = new HashSet<>(inboundStudentModel.getLeadershipTraits());
            InboundStudentLeadershipTraitModel studentLeadershipTrait = new InboundStudentLeadershipTraitModel(
                    behaviorModels, inboundStudentModel.getTeacher());
            student.setStudentLeadershipTraits(studentLeadershipTraitModelToEntityMapper.map(studentLeadershipTrait,
                    student.getStudentLeadershipTraits()));
        }
    }

}
