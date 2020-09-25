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
import io.aiontechnology.mentorsuccess.api.model.inbound.reference.LeadershipTraitModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.student.InboundStudentLeadershipTraitModel;
import io.aiontechnology.mentorsuccess.entity.SchoolPersonRole;
import io.aiontechnology.mentorsuccess.entity.StudentLeadershipTrait;
import io.aiontechnology.mentorsuccess.entity.reference.LeadershipTrait;
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
public class StudentLeadershipTraitModelToEntityCollectionUpdateMapper implements
        OneWayToCollectionUpdateMapper<InboundStudentLeadershipTraitModel, StudentLeadershipTrait> {

    private final OneWayMapper<LeadershipTraitModel, LeadershipTrait> leadershipTraitModelToEntityMapper;

    private final OneWayMapper<URI, SchoolPersonRole> personModelToEntityMapper;

    @Override
    public Collection<StudentLeadershipTrait> map(InboundStudentLeadershipTraitModel inboundStudentLeadershipTraitModel,
            Collection<StudentLeadershipTrait> studentLeadershipTraits) {
        Collection<StudentLeadershipTrait> newCollection = createNewCollection(inboundStudentLeadershipTraitModel);
        Collection<StudentLeadershipTrait> toRemove = new ArrayList<>(studentLeadershipTraits);
        toRemove.removeAll(newCollection);
        Collection<StudentLeadershipTrait> toAdd = new ArrayList<>(newCollection);
        toAdd.removeAll(studentLeadershipTraits);

        studentLeadershipTraits.removeAll(toRemove);
        studentLeadershipTraits.addAll(toAdd);
        return studentLeadershipTraits;
    }

    private Collection<StudentLeadershipTrait> createNewCollection(InboundStudentLeadershipTraitModel inboundStudentLeadershipTraitModel) {
        return inboundStudentLeadershipTraitModel.getLeadershipTraits().stream()
                .map(leadershipTraitModel -> {
                    StudentLeadershipTrait studentLeadershipTrait = new StudentLeadershipTrait();
                    studentLeadershipTrait.setRole(personModelToEntityMapper
                            .map(inboundStudentLeadershipTraitModel.getTeacher())
                            .orElse(null));
                    studentLeadershipTrait.setLeadershipTrait(leadershipTraitModelToEntityMapper.
                            map(leadershipTraitModel)
                            .orElse(null));
                    return studentLeadershipTrait;
                })
                .collect(Collectors.toList());
    }

}
