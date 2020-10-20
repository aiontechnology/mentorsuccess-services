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

package io.aiontechnology.mentorsuccess.api.mapping.toentity.person;

import io.aiontechnology.mentorsuccess.api.mapping.OneWayUpdateMapper;
import io.aiontechnology.mentorsuccess.api.model.inbound.reference.ProgramAdminModel;
import io.aiontechnology.mentorsuccess.entity.Person;
import io.aiontechnology.mentorsuccess.entity.SchoolPersonRole;
import io.aiontechnology.mentorsuccess.util.PhoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

import static io.aiontechnology.mentorsuccess.entity.RoleType.PROGRAM_ADMIN;

/**
 * @author Whitney Hunter
 * @since 0.3.0
 */
@Component
@RequiredArgsConstructor
public class ProgramAdminModelToEntityUpdateMapper implements OneWayUpdateMapper<ProgramAdminModel, SchoolPersonRole> {

    /** Phone number formatting service */
    private final PhoneService phoneService;

    /**
     * Map the given {@link ProgramAdminModel} ot the given {@link SchoolPersonRole}.
     *
     * @param programAdminModel The {@link ProgramAdminModel} to map.
     * @param role The {@link SchoolPersonRole} to map to.
     * @return The resulting {@link SchoolPersonRole}.
     */
    @Override
    public Optional<SchoolPersonRole> map(ProgramAdminModel programAdminModel, SchoolPersonRole role) {
        Objects.requireNonNull(role);
        return Optional.ofNullable(programAdminModel)
                .map(p -> {
                    Person person = role.getPerson() != null ? role.getPerson() : new Person();
                    person.setFirstName(p.getFirstName());
                    person.setLastName(p.getLastName());
                    person.setEmail(p.getEmail());
                    person.setWorkPhone(phoneService.normalize(p.getWorkPhone()));
                    person.setCellPhone(phoneService.normalize(p.getCellPhone()));

                    role.setPerson(person);
                    role.setIsActive(true);
                    role.setType(PROGRAM_ADMIN);
                    return role;
                });
    }

}
