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
import org.springframework.stereotype.Component;

/**
 * Mapper from {@link PersonModel} instances from {@link Person} instances.
 *
 * @author Whitney Hunter
 */
@Component
public class FromPersonModelMapper implements MutableMapper<PersonModel, Person> {

    /**
     * Map the given {@link PersonModel} to a new {@link Person} instance.
     *
     * @param personModel The {@link PersonModel} to map
     * @return The generated {@link Person} instance.
     */
    @Override
    public Person map(PersonModel personModel) {
        Person person = new Person();
        return map(personModel, person);
    }

    /**
     * Map the given {@link Person} from the values in the given {@link PersonModel}.
     *
     * @param personModel The {@link PersonModel} to map from.
     * @param person The {@link Person} to update.
     * @return The updated {@link Person}.
     */
    @Override
    public Person map(PersonModel personModel, Person person) {
        person.setName(personModel.getName());
        person.setPhone(personModel.getPhone());
        person.setEmail(personModel.getEmail());
        return person;
    }

}
