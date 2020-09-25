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
import io.aiontechnology.mentorsuccess.api.model.inbound.PersonModel;
import io.aiontechnology.mentorsuccess.entity.Person;
import io.aiontechnology.mentorsuccess.util.PhoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Mapper that converts a {@link Person} to a {@link PersonModel}.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
@Component
@RequiredArgsConstructor
@Primary
public class PersonEntityToModelMapper implements OneWayMapper<Person, PersonModel> {

    /** Service for formatting phone numbers */
    private final PhoneService phoneService;

    /**
     * Map the given {@link Person} to a {@link PersonModel}.
     *
     * @param person The {@link Person} to map.
     * @return The mapped {@link PersonModel}.
     */
    @Override
    public Optional<PersonModel> map(Person person) {
        return Optional.ofNullable(person)
                .map(p -> PersonModel.builder()
                        .withId(p.getId())
                        .withFirstName(p.getFirstName())
                        .withLastName(p.getLastName())
                        .withWorkPhone(phoneService.format(p.getWorkPhone()))
                        .withCellPhone(phoneService.format(p.getCellPhone()))
                        .withEmail(p.getEmail())
                        .build());
    }

}
