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
import io.aiontechnology.mentorsuccess.api.model.inbound.reference.LeadershipTraitModel;
import io.aiontechnology.mentorsuccess.api.model.outbound.student.OutboundStudentLeadershipTraitModel;
import io.aiontechnology.mentorsuccess.entity.RoleType;
import io.aiontechnology.mentorsuccess.entity.SchoolPersonRole;
import io.aiontechnology.mentorsuccess.entity.StudentLeadershipTrait;
import io.aiontechnology.mentorsuccess.entity.reference.LeadershipTrait;
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
public class StudentLeadershipTraitEntityCollectionToModelCollectionMapper implements
        OneWayCollectionMapper<StudentLeadershipTrait, String> {

    private final OneWayCollectionSeparatorMapper<SchoolPersonRole, StudentLeadershipTrait> byRoleListSeparatingMapper =
            new OneWayCollectionSeparatorMapper<>(StudentLeadershipTrait::getRole);

    private final OneWayCollectionSeparatorMapper<RoleType, StudentLeadershipTrait> byTypeListSeparatingMapper =
            new OneWayCollectionSeparatorMapper<>(studentLeadershipTrait -> studentLeadershipTrait.getRole().getType());

    private final OneWayCollectionMapper<LeadershipTrait, LeadershipTraitModel> leadershipTraitEntityToModelCollectionMapper;

    @Qualifier("teacherAssemblerMapperAdaptor")
    private final OneWayMapper<SchoolPersonRole, TeacherModel> teacherEntityToModelMapper;

    @Override
    public Collection<String> map(Collection<StudentLeadershipTrait> studentLeadershipTraits) {
        Collection<OutboundStudentLeadershipTraitModel> outboundStudentLeadershipTraitModels =
                byRoleListSeparatingMapper.map(studentLeadershipTraits).entrySet().stream()
                        .flatMap(e -> {
                            Map<RoleType, List<StudentLeadershipTrait>> roleTypeMap = byTypeListSeparatingMapper.map(e.getValue());
                            return transformRoleTypeMap(e.getKey(), roleTypeMap).stream();
                        })
                        .collect(Collectors.toCollection(ArrayList::new));
        return outboundStudentLeadershipTraitModels.stream().findFirst()
                .map(o -> o.getLeadershipTraits().stream()
                        .map(LeadershipTraitModel::getName)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    private Collection<OutboundStudentLeadershipTraitModel> transformRoleTypeMap(SchoolPersonRole teacher,
            Map<RoleType, List<StudentLeadershipTrait>> roleTypeMap) {
        return roleTypeMap.entrySet().stream()
                .map(e -> {
                    Set<LeadershipTrait> leadershipTraits = e.getValue().stream()
                            .map(StudentLeadershipTrait::getLeadershipTrait)
                            .collect(Collectors.toSet());
                    return OutboundStudentLeadershipTraitModel.builder()
                            .withLeadershipTraits(leadershipTraitEntityToModelCollectionMapper.map(leadershipTraits))
                            .withTeacher(teacherEntityToModelMapper.map(teacher).orElse(null))
                            .build();
                })
                .collect(Collectors.toCollection(ArrayList::new));
    }

}
