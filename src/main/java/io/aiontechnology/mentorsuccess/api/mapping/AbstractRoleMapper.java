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
 * Helper class for mapping to roles.
 *
 * @author <a href="mailto:whitney@aiontechnology.io">Whitney Hunter</a>
 * @since 1.0.0
 */
@RequiredArgsConstructor
public abstract class AbstractRoleMapper<Model extends Personnel> {

    /** Utility object for processing phone numbers. */
    private final PhoneService phoneService;

    /**
     * Map a personnel sub-type to a role. A new role is created.
     *
     * @param model The personnel sub-type to map.
     * @return The mapped {@link Role}.
     */
    public Role mapModelToEntity(Model model) {
        Role role = new Role();
        return mapModelToEntity(model, role);
    }

    /**
     * Map a personnel sub-type to a role. The role to map to is passed in.
     *
     * @param model The personnel sub-type to map.
     * @param role The {@link Role} to map into.
     * @return The mapped {@link Role}.
     */
    public Role mapModelToEntity(Model model, Role role) {
        Person person = new Person();
        person.setFirstName(model.getFirstName());
        person.setLastName(model.getLastName());
        person.setEmail(model.getEmail());
        person.setWorkPhone(phoneService.normalize(model.getWorkPhone()));
        person.setCellPhone(phoneService.normalize(model.getCellPhone()));

        role.setPerson(person);
        role.setIsActive(true);
        return doMapRole(model, role);
    }

    /**
     * Implemented by the sub-type to perform type specific mapping
     *
     * @param model The personnel sub-type to map.
     * @param role The {@link Role} to map into.
     * @return The mapped {@link Role}.
     */
    protected abstract Role doMapRole(Model model, Role role);

}
