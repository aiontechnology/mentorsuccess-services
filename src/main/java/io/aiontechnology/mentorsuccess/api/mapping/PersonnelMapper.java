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

import io.aiontechnology.mentorsuccess.api.model.PersonnelModel;
import io.aiontechnology.mentorsuccess.entity.Role;
import io.aiontechnology.mentorsuccess.util.PhoneService;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Mapper between {@link Role} and {@link PersonnelModel}.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@Component
public class PersonnelMapper extends AbstractRoleMapper<PersonnelModel> implements Mapper<Role, PersonnelModel> {

    /**
     * Constructor
     *
     * @param phoneService Service for formatting phone numbers
     */
    @Inject
    public PersonnelMapper(PhoneService phoneService) {
        super(phoneService);
    }

    /**
     * Map a {@link Role} to a new {@link PersonnelModel}.
     *
     * @param role The {@link Role} to map.
     * @return The resulting {@link PersonnelModel}.
     */
    @Override
    public PersonnelModel mapEntityToModel(Role role) {
        return PersonnelModel.builder()
                .withFirstName(role.getPerson().getFirstName())
                .withLastName(role.getPerson().getLastName())
                .withEmail(role.getPerson().getEmail())
                .withWorkPhone(phoneService.format(role.getPerson().getWorkPhone()))
                .withCellPhone(phoneService.format(role.getPerson().getCellPhone()))
                .withType(role.getType())
                .build();
    }

    /**
     * Template method for mapping from a {@link PersonnelModel} to a {@link Role}.
     *
     * @param personnelModel The {@link PersonnelModel} to map from.
     * @param role The {@link Role} to map into.
     * @return The resulting {@link Role}.
     */
    @Override
    protected Role doMapRole(PersonnelModel personnelModel, Role role) {
        role.setType(personnelModel.getType());
        return role;
    }

}
