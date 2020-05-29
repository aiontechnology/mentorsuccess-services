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

import io.aiontechnology.mentorsuccess.api.model.Personnel;
import io.aiontechnology.mentorsuccess.entity.Person;
import io.aiontechnology.mentorsuccess.entity.Role;
import io.aiontechnology.mentorsuccess.util.PhoneService;
import lombok.RequiredArgsConstructor;

/**
 * @author Whitney Hunter
 */
@RequiredArgsConstructor
public abstract class BaseFromPersonnelModelMapper<T extends Personnel> implements MutableMapper<T, Role> {

    private final PhoneService phoneService;

    @Override
    public Role map(T from) {
        Role role = new Role();
        return map(from, role);
    }

    @Override
    public Role map(T from, Role to) {
        Person person = new Person();
        person.setFirstName(from.getFirstName());
        person.setLastName(from.getLastName());
        person.setEmail(from.getEmail());
        person.setWorkPhone(phoneService.normalize(from.getWorkPhone()));
        person.setCellPhone(phoneService.normalize(from.getCellPhone()));

        to.setPerson(person);
        to.setIsActive(true);
        return doMapRole(from, to);
    }

    protected abstract Role doMapRole( T from, Role role);

}
