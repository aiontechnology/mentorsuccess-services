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

import io.aiontechnology.atlas.classification.CollectionClassifier;
import io.aiontechnology.atlas.classification.impl.FunctionBasedCollectionClassifier;
import io.aiontechnology.atlas.mapping.OneWayCollectionMapper;
import io.aiontechnology.atlas.mapping.OneWayMapper;
import io.aiontechnology.mentorsuccess.entity.SchoolPersonRole;
import io.aiontechnology.mentorsuccess.entity.StudentBehavior;
import io.aiontechnology.mentorsuccess.entity.reference.Behavior;
import io.aiontechnology.mentorsuccess.model.enumeration.RoleType;
import io.aiontechnology.mentorsuccess.model.outbound.OutboundTeacher;
import io.aiontechnology.mentorsuccess.model.outbound.student.OutboundStudentBehavior;
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
public class StudentBehaviorEntityCollectionToModelCollectionMapper implements
        OneWayCollectionMapper<StudentBehavior, String> {

    private final CollectionClassifier<SchoolPersonRole, StudentBehavior> byRoleListClassifier =
            new FunctionBasedCollectionClassifier<>(StudentBehavior::getRole);

    private final CollectionClassifier<RoleType, StudentBehavior> byTypeListClassifier =
            new FunctionBasedCollectionClassifier<>((studentBehavior -> studentBehavior.getRole().getType()));

    private final OneWayCollectionMapper<Behavior, String> behaviorEntityToModelCollectionMapper;

    @Qualifier("teacherAssemblerMapperAdaptor")
    private final OneWayMapper<SchoolPersonRole, OutboundTeacher> teacherEntityToModelMapper;

    @Override
    public Collection<String> map(Collection<StudentBehavior> studentBehaviors) {
        Collection<OutboundStudentBehavior> outboundStudentBehaviors =
                byRoleListClassifier.classify(studentBehaviors).entrySet().stream()
                        .flatMap(e -> {
                            Map<RoleType, List<StudentBehavior>> roleTypeMap = byTypeListClassifier.classify(e.getValue());
                            return transformRoleTypeMap(e.getKey(), roleTypeMap).stream();
                        })
                        .collect(Collectors.toCollection(ArrayList::new));
        return outboundStudentBehaviors.stream().findFirst()
                .map(OutboundStudentBehavior::getBehaviors)
                .orElse(Collections.emptyList());
    }

    private Collection<OutboundStudentBehavior> transformRoleTypeMap(SchoolPersonRole teacher,
            Map<RoleType, List<StudentBehavior>> roleTypeMap) {
        return roleTypeMap.entrySet().stream()
                .map(e -> {
                    Set<Behavior> behaviors = e.getValue().stream()
                            .map(StudentBehavior::getBehavior)
                            .collect(Collectors.toSet());
                    return OutboundStudentBehavior.builder()
                            .withBehaviors(behaviorEntityToModelCollectionMapper.map(behaviors))
                            .withTeacher(teacherEntityToModelMapper.map(teacher).orElse(null))
                            .build();
                })
                .collect(Collectors.toCollection(ArrayList::new));
    }

}
