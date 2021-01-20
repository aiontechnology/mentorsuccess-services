/*
 * Copyright 2020-2021 Aion Technology LLC
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.aiontechnology.mentorsuccess.api.mapping.tomodel.student;

import io.aiontechnology.atlas.mapping.OneWayMapper;
import io.aiontechnology.mentorsuccess.entity.SchoolPersonRole;
import io.aiontechnology.mentorsuccess.entity.StudentMentor;
import io.aiontechnology.mentorsuccess.model.outbound.OutboundMentor;
import io.aiontechnology.mentorsuccess.model.outbound.student.OutboundStudentMentor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Whitney Hunter
 * @since 0.6.0
 */
@Component
@RequiredArgsConstructor
public class StudentMentorEntityToModelMapper implements OneWayMapper<StudentMentor, OutboundStudentMentor> {

    @Qualifier("mentorAssemblerMapperAdaptor")
    private final OneWayMapper<SchoolPersonRole, OutboundMentor> mentorEntityToModelMapper;

    @Override
    public Optional<OutboundStudentMentor> map(StudentMentor studentMentor) {
        return Optional.ofNullable(studentMentor)
                .map(s -> OutboundStudentMentor.builder()
                        .withMentor(mentorEntityToModelMapper.map(s.getRole()).orElse(null))
                        .withTime(s.getTime())
                        .build());
    }

}
