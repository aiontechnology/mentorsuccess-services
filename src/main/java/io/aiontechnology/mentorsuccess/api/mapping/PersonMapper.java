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

package io.aiontechnology.mentorsuccess.api.mapping;

import io.aiontechnology.mentorsuccess.api.model.PersonModel;
import io.aiontechnology.mentorsuccess.entity.Person;
import io.aiontechnology.mentorsuccess.util.PhoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Mapper between {@link Person} and {@link PersonModel}.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@Component
@RequiredArgsConstructor
public class PersonMapper implements Mapper<Person, PersonModel> {

    /** Service for formatting phone numbers */
    private final PhoneService phoneService;

    /**
     * Map a {@link Person} to a new {@link PersonModel}.
     *
     * @param person The {@link Person} to map.
     * @return The resulting {@link PersonModel}.
     */
    @Override
    public PersonModel mapEntityToModel(Person person) {
        return PersonModel.builder()
                .withId(person.getId())
                .withFirstName(person.getFirstName())
                .withLastName(person.getLastName())
                .withWorkPhone(phoneService.format(person.getWorkPhone()))
                .withCellPhone(phoneService.format(person.getCellPhone()))
                .withEmail(person.getEmail())
                .build();
    }

    /**
     * Map a {@link PersonModel} to a new {@link Person}.
     *
     * @param personModel The {@link PersonModel} to map.
     * @return The resulting {@link Person}.
     */
    @Override
    public Person mapModelToEntity(PersonModel personModel) {
        Person person = new Person();
        return mapModelToEntity(personModel, person);
    }

    /**
     * Map a {@link PersonModel} to the given {@link Person}.
     *
     * @param personModel The {@link PersonModel} to map.
     * @param person The {@link Person} to map to.
     * @return The resulting {@link Person}.
     */
    @Override
    public Person mapModelToEntity(PersonModel personModel, Person person) {
        person.setFirstName(personModel.getFirstName());
        person.setLastName(personModel.getLastName());
        person.setWorkPhone(phoneService.normalize(personModel.getWorkPhone()));
        person.setCellPhone(phoneService.normalize(personModel.getCellPhone()));
        person.setEmail(personModel.getEmail());
        return person;
    }

}
