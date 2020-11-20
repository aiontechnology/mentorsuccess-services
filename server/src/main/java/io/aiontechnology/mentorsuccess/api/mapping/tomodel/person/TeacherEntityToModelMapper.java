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

package io.aiontechnology.mentorsuccess.api.mapping.tomodel.person;

import io.aiontechnology.atlas.mapping.OneWayMapper;
import io.aiontechnology.mentorsuccess.entity.SchoolPersonRole;
import io.aiontechnology.mentorsuccess.model.inbound.InboundTeacher;
import io.aiontechnology.mentorsuccess.model.outbound.OutboundTeacher;
import io.aiontechnology.mentorsuccess.util.PhoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Mapper that converts a {@link SchoolPersonRole} to a {@link InboundTeacher}.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
@Component
@RequiredArgsConstructor
@Primary
public class TeacherEntityToModelMapper implements OneWayMapper<SchoolPersonRole, OutboundTeacher> {

    /** Service for formatting phone numbers */
    private final PhoneService phoneService;

    /**
     * Map the given {@link SchoolPersonRole} to a {@link InboundTeacher}.
     *
     * @param role The {@link SchoolPersonRole} to map.
     * @return The mapped {@link InboundTeacher}.
     */
    @Override
    public Optional<OutboundTeacher> map(SchoolPersonRole role) {
        return Optional.ofNullable(role)
                .map(t -> OutboundTeacher.builder()
                        .withFirstName(t.getPerson().getFirstName())
                        .withLastName(t.getPerson().getLastName())
                        .withEmail(t.getPerson().getEmail())
                        .withWorkPhone(phoneService.format(t.getPerson().getWorkPhone()))
                        .withCellPhone(phoneService.format(t.getPerson().getCellPhone()))
                        .withGrade1(t.getGrade1())
                        .withGrade2(t.getGrade2())
                        .build());
    }

}
