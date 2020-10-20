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

import io.aiontechnology.mentorsuccess.api.error.NotFoundException;
import io.aiontechnology.mentorsuccess.api.mapping.CollectionSyncHelper;
import io.aiontechnology.mentorsuccess.api.mapping.OneWayMapper;
import io.aiontechnology.mentorsuccess.api.mapping.OneWayToCollectionUpdateMapper;
import io.aiontechnology.mentorsuccess.api.model.inbound.reference.BehaviorModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.student.InboundStudentBehaviorModel;
import io.aiontechnology.mentorsuccess.entity.SchoolPersonRole;
import io.aiontechnology.mentorsuccess.entity.StudentBehavior;
import io.aiontechnology.mentorsuccess.entity.reference.Behavior;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author Whitney Hunter
 * @since 0.3.0
 */
@Component
@RequiredArgsConstructor
public class StudentBehaviorModelToEntityCollectionUpdateMapper implements
        OneWayToCollectionUpdateMapper<InboundStudentBehaviorModel, StudentBehavior> {

    private final OneWayMapper<BehaviorModel, Behavior> behaviorModelToEntityMapper;

    private final OneWayMapper<URI, SchoolPersonRole> teacherUriToEntityMapper;

    private final CollectionSyncHelper<StudentBehavior> syncHelper;

    @Override
    public Collection<StudentBehavior> map(InboundStudentBehaviorModel inboundStudentBehaviorModel,
            Collection<StudentBehavior> studentBehaviors) {
        Collection<StudentBehavior> newCollection = doMap(inboundStudentBehaviorModel);
        return syncHelper.sync(studentBehaviors, newCollection);
    }

    /**
     * Map the set of {@link BehaviorModel} instances contained in the provided {@link InboundStudentBehaviorModel}
     * to a collection of {@link StudentBehavior} instances.
     *
     * @param inboundStudentBehaviorModel The {@link InboundStudentBehaviorModel} from which to get the
     * {@link BehaviorModel} instances to map.
     * @return The resulting set of {@link StudentBehavior} instances.
     */
    private Collection<StudentBehavior> doMap(InboundStudentBehaviorModel inboundStudentBehaviorModel) {
        return inboundStudentBehaviorModel.getBehaviors().stream()
                .map(behaviorModel -> {
                    StudentBehavior studentBehavior = new StudentBehavior();
                    studentBehavior.setRole(teacherUriToEntityMapper
                            .map(inboundStudentBehaviorModel.getTeacher())
                            .orElseThrow(() -> new NotFoundException("Unable to find teacher")));
                    studentBehavior.setBehavior(behaviorModelToEntityMapper
                            .map(behaviorModel)
                            .orElseThrow(() -> new NotFoundException("Invalid behavior: " + behaviorModel.getName())));
                    return studentBehavior;
                })
                .collect(Collectors.toList());
    }

}
