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

import io.aiontechnology.mentorsuccess.api.model.inbound.PersonnelModel;
import io.aiontechnology.mentorsuccess.entity.Person;
import io.aiontechnology.mentorsuccess.entity.SchoolPersonRole;
import io.aiontechnology.mentorsuccess.entity.RoleType;
import io.aiontechnology.mentorsuccess.util.PhoneService;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static io.aiontechnology.mentorsuccess.entity.RoleType.PRINCIPAL;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link PersonEntityToModelMapper}.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
public class PersonnelEntityToModelMapperTest {

    @Test
    void testMapping() throws Exception {
        // setup the fixture
        UUID id = UUID.randomUUID();
        String firstName = "FIRST_NAME";
        String lastName = "LAST_NAME";
        String workPhone = "WORK_PHONE";
        String cellPhone = "CELL_PHONE";
        String email = "EMAIL";

        Person person = new Person();
        person.setId(id);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setWorkPhone(workPhone);
        person.setCellPhone(cellPhone);
        person.setEmail(email);

        RoleType type = PRINCIPAL;
        SchoolPersonRole role = new SchoolPersonRole();
        role.setPerson(person);
        role.setType(type);

        PersonnelEntityToModelMapper personnelEntityToModelMapper = new PersonnelEntityToModelMapper(new PhoneService());

        // execute the SUT
        Optional<PersonnelModel> result = personnelEntityToModelMapper.map(role);

        // validation
        assertThat(result).isNotEmpty();
        assertThat(result.get().getType()).isEqualTo(type);
        assertThat(result.get().getFirstName()).isEqualTo(firstName);
        assertThat(result.get().getLastName()).isEqualTo(lastName);
        assertThat(result.get().getWorkPhone()).isEqualTo(workPhone);
        assertThat(result.get().getCellPhone()).isEqualTo(cellPhone);
        assertThat(result.get().getEmail()).isEqualTo(email);
    }

    @Test
    void testNull() throws Exception {
        // setup the fixture
        PersonnelEntityToModelMapper personnelEntityToModelMapper = new PersonnelEntityToModelMapper(new PhoneService());

        // execute the SUT
        Optional<PersonnelModel> result = personnelEntityToModelMapper.map(null);

        // validation
        assertThat(result).isEmpty();
    }

}
