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

import io.aiontechnology.mentorsuccess.api.mapping.OneWayMapper;
import io.aiontechnology.mentorsuccess.api.model.inbound.PersonnelModel;
import io.aiontechnology.mentorsuccess.entity.SchoolPersonRole;
import io.aiontechnology.mentorsuccess.util.PhoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Mapper that converts a {@link SchoolPersonRole} to a {@link PersonnelModel}.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
@Component
@RequiredArgsConstructor
public class PersonnelEntityToModelMapper implements OneWayMapper<SchoolPersonRole, PersonnelModel> {

    /** Service for formatting phone numbers */
    private final PhoneService phoneService;

    /**
     * Map the given {@link SchoolPersonRole} to a {@link PersonnelModel}.
     *
     * @param role The {@link SchoolPersonRole} to map.
     * @return The mapped {@link PersonnelModel}.
     */
    @Override
    public Optional<PersonnelModel> map(SchoolPersonRole role) {
        return Optional.ofNullable(role)
                .map(r -> PersonnelModel.builder()
                        .withFirstName(role.getPerson().getFirstName())
                        .withLastName(role.getPerson().getLastName())
                        .withEmail(role.getPerson().getEmail())
                        .withWorkPhone(phoneService.format(role.getPerson().getWorkPhone()))
                        .withCellPhone(phoneService.format(role.getPerson().getCellPhone()))
                        .withType(role.getType())
                        .build()
                );
    }

}
