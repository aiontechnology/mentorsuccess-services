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

package io.aiontechnology.mentorsuccess.api.assembler;

import io.aiontechnology.mentorsuccess.api.model.SchoolModel;
import io.aiontechnology.mentorsuccess.entity.School;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.IanaLinkRelations;

import javax.inject.Inject;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class SchoolModelAssemblerTest {

    @Inject
    private SchoolModelAssembler assembler;

    @Test
    void testToModel() {
        // setup the fixture
        UUID id = UUID.randomUUID();
        String name = "NAME";
        String street1 = "STREET1";
        String street2 = "STREET2";
        String city = "CITY";
        String state = "ST";
        String zip = "123456789";
        String phone = "1234567890";
        School school = new School(id, name, street1, street2, city, state, zip, phone);

        // execute the SUT
        SchoolModel schoolModel = assembler.toModel(school);

        // validation
        assertThat(schoolModel.getId()).isEqualTo(id);
        assertThat(schoolModel.getName()).isEqualTo(name);
        assertThat(schoolModel.getAddress().getStreet1()).isEqualTo(street1);
        assertThat(schoolModel.getAddress().getStreet2()).isEqualTo(street2);
        assertThat(schoolModel.getAddress().getCity()).isEqualTo(city);
        assertThat(schoolModel.getAddress().getState()).isEqualTo(state);
        assertThat(schoolModel.getAddress().getZip()).isEqualTo(zip);
        assertThat(schoolModel.getPhone()).isEqualTo(phone);
        assertThat(schoolModel.getLink(IanaLinkRelations.SELF).get()).isNotNull();
    }
}
