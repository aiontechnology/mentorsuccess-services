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
 * @author <a href="mailto:whitney@aiontechnology.io">Whitney Hunter</a>
 * @since 1.0.0
 */
@Component
@RequiredArgsConstructor
public class PersonMapper implements Mapper<Person, PersonModel> {

    private final PhoneService phoneService;

    @Override
    public PersonModel mapEntityToModel(Person person) {
        return PersonModel.builder()
                .withId(person.getId())
                .withFirstName(person.getFirstName())
                .withLastName(person.getLastName())
                .withWorkPhone(phoneService.normalize(person.getWorkPhone()))
                .withCellPhone(phoneService.normalize(person.getCellPhone()))
                .withEmail(person.getEmail())
                .build();
    }

    @Override
    public Person mapModelToEntity(PersonModel personModel) {
        Person person = new Person();
        return mapModelToEntity(personModel, person);
    }

    @Override
    public Person mapModelToEntity(PersonModel personModel, Person person) {
        person.setFirstName(personModel.getFirstName());
        person.setLastName(personModel.getLastName());
        person.setWorkPhone(personModel.getWorkPhone());
        person.setCellPhone(personModel.getCellPhone());
        person.setEmail(personModel.getEmail());
        return person;
    }

}
