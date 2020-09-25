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

import io.aiontechnology.mentorsuccess.api.mapping.OneWayMapper;
import io.aiontechnology.mentorsuccess.api.mapping.OneWayToCollectionUpdateMapper;
import io.aiontechnology.mentorsuccess.api.model.inbound.reference.LeadershipSkillModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.student.InboundStudentLeadershipSkillModel;
import io.aiontechnology.mentorsuccess.entity.SchoolPersonRole;
import io.aiontechnology.mentorsuccess.entity.StudentLeadershipSkill;
import io.aiontechnology.mentorsuccess.entity.reference.LeadershipSkill;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author Whitney Hunter
 * @since 0.3.0
 */
@Component
@RequiredArgsConstructor
public class StudentLeadershipSkillModelToEntityCollectionUpdateMapper implements
        OneWayToCollectionUpdateMapper<InboundStudentLeadershipSkillModel, StudentLeadershipSkill> {

    private final OneWayMapper<LeadershipSkillModel, LeadershipSkill> leadershipSkillModelToEntityMapper;

    private final OneWayMapper<URI, SchoolPersonRole> teacherModelToEntityMapper;

    @Override
    public Collection<StudentLeadershipSkill> map(InboundStudentLeadershipSkillModel inboundStudentLeadershipSkillModel,
            Collection<StudentLeadershipSkill> studentLeadershipSkills) {
        Collection<StudentLeadershipSkill> newCollection = createNewCollection(inboundStudentLeadershipSkillModel);
        Collection<StudentLeadershipSkill> toRemove = new ArrayList<>(studentLeadershipSkills);
        toRemove.removeAll(newCollection);
        Collection<StudentLeadershipSkill> toAdd = new ArrayList<>(newCollection);
        toAdd.removeAll(studentLeadershipSkills);

        studentLeadershipSkills.removeAll(toRemove);
        studentLeadershipSkills.addAll(toAdd);
        return studentLeadershipSkills;
    }

    private Collection<StudentLeadershipSkill> createNewCollection(InboundStudentLeadershipSkillModel inboundStudentLeadershipSkillModel) {
        return inboundStudentLeadershipSkillModel.getLeadershipSkills().stream()
                .map(leadershipSkillModel -> {
                    StudentLeadershipSkill studentLeadershipSkill = new StudentLeadershipSkill();
                    studentLeadershipSkill.setRole(teacherModelToEntityMapper
                            .map(inboundStudentLeadershipSkillModel.getTeacher())
                            .orElseThrow(() -> new IllegalStateException("no role found for")));
                    studentLeadershipSkill.setLeadershipSkill(leadershipSkillModelToEntityMapper.
                            map(leadershipSkillModel)
                            .orElse(null));
                    return studentLeadershipSkill;
                })
                .collect(Collectors.toList());
    }

}
