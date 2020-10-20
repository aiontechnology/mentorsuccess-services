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
import io.aiontechnology.mentorsuccess.api.mapping.OneWayCollectionSeparatorMapper;
import io.aiontechnology.mentorsuccess.api.mapping.OneWayMapper;
import io.aiontechnology.mentorsuccess.api.model.inbound.TeacherModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.reference.LeadershipSkillModel;
import io.aiontechnology.mentorsuccess.api.model.outbound.student.OutboundStudentLeadershipSkillModel;
import io.aiontechnology.mentorsuccess.entity.RoleType;
import io.aiontechnology.mentorsuccess.entity.SchoolPersonRole;
import io.aiontechnology.mentorsuccess.entity.StudentLeadershipSkill;
import io.aiontechnology.mentorsuccess.entity.reference.LeadershipSkill;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Whitney Hunter
 * @since 0.3.0
 */
@Component
@RequiredArgsConstructor
public class StudentLeadershipSkillEntityCollectionToModelCollectionMapper implements
        OneWayCollectionMapper<StudentLeadershipSkill, String> {

    private final OneWayCollectionSeparatorMapper<SchoolPersonRole, StudentLeadershipSkill> byRoleListSeparatingMapper =
            new OneWayCollectionSeparatorMapper<>(StudentLeadershipSkill::getRole);

    private final OneWayCollectionSeparatorMapper<RoleType, StudentLeadershipSkill> byTypeListSeparatingMapper =
            new OneWayCollectionSeparatorMapper<>((studentLeadershipSkill -> studentLeadershipSkill.getRole().getType()));

    private final OneWayCollectionMapper<LeadershipSkill, LeadershipSkillModel> leadershipSkillEntityToModelCollectionMapper;

    @Qualifier("teacherAssemblerMapperAdaptor")
    private final OneWayMapper<SchoolPersonRole, TeacherModel> teacherEntityToModelMapper;

    @Override
    public Collection<String> map(Collection<StudentLeadershipSkill> studentLeadershipSkills) {
        Collection<OutboundStudentLeadershipSkillModel> outboundStudentLeadershipSkillModels =
                byRoleListSeparatingMapper.map(studentLeadershipSkills).entrySet().stream()
                        .flatMap(e -> {
                            Map<RoleType, List<StudentLeadershipSkill>> roleTypeMap = byTypeListSeparatingMapper.map(e.getValue());
                            return transformRoleTypeMap(e.getKey(), roleTypeMap).stream();
                        })
                        .collect(Collectors.toCollection(ArrayList::new));
        return outboundStudentLeadershipSkillModels.stream().findFirst()
                .map(o -> o.getLeadershipSkills().stream()
                        .map(LeadershipSkillModel::getName)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    private Collection<OutboundStudentLeadershipSkillModel> transformRoleTypeMap(SchoolPersonRole teacher,
            Map<RoleType, List<StudentLeadershipSkill>> roleTypeMap) {
        return roleTypeMap.entrySet().stream()
                .map(e -> {
                    Set<LeadershipSkill> leadershipSkills = e.getValue().stream()
                            .map(StudentLeadershipSkill::getLeadershipSkill)
                            .collect(Collectors.toSet());
                    return OutboundStudentLeadershipSkillModel.builder()
                            .withLeadershipSkills(leadershipSkillEntityToModelCollectionMapper.map(leadershipSkills))
                            .withTeacher(teacherEntityToModelMapper.map(teacher).orElse(null))
                            .build();
                })
                .collect(Collectors.toCollection(ArrayList::new));
    }

}
