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

package io.aiontechnology.mentorsuccess.api.factory;

import io.aiontechnology.mentorsuccess.api.model.AddressModel;
import io.aiontechnology.mentorsuccess.api.model.SchoolModel;
import io.aiontechnology.mentorsuccess.entity.School;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class SchoolFactoryTest {

    private static final String NAME = "NAME";
    private static final String STREET1 = "STREET1";
    private static final String STREET2 = "STREET2";
    private static final String CITY = "CITY";
    private static final String STATE = "ST";
    private static final String ZIP = "12345";
    private static final String PHONE = "1234567890";
    private static final String DISTRICT = "DISTRICT";
    Boolean isPrivate = Boolean.TRUE;

    @Test
    void shouldCreateNewSchool() {
        // setup the fixture
        SchoolModel schoolModel = generateSchoolModel();

        SchoolFactory schoolFactory = new SchoolFactory();

        // execute the SUT
        School school = schoolFactory.fromModel(schoolModel);

        // validation
        assertThat(school.getId()).isNull();
        assertThat(school.getName()).isEqualTo(NAME);
        assertThat(school.getStreet1()).isEqualTo(STREET1);
        assertThat(school.getStreet2()).isEqualTo(STREET2);
        assertThat(school.getCity()).isEqualTo(CITY);
        assertThat(school.getState()).isEqualTo(STATE);
        assertThat(school.getPhone()).isEqualTo(PHONE);
        assertThat(school.getDistrict()).isEqualTo(DISTRICT);
        assertThat(school.getIsPrivate()).isTrue();
    }

    @Test
    void shouldUpdateGivenSchool() {
        // setup the fixture
        SchoolModel schoolModel = generateSchoolModel();
        UUID id = UUID.randomUUID();
        School school = new School(id, "n", "s1", "s2", "c", "s", "z", "p", "d", true);

        SchoolFactory schoolFactory = new SchoolFactory();

        // execute the SUT
        School updated = schoolFactory.fromModel(schoolModel, school);

        // validation
        assertThat(updated.getId()).isEqualTo(id);
        assertThat(updated.getName()).isEqualTo(NAME);
        assertThat(updated.getStreet1()).isEqualTo(STREET1);
        assertThat(updated.getStreet2()).isEqualTo(STREET2);
        assertThat(updated.getCity()).isEqualTo(CITY);
        assertThat(updated.getState()).isEqualTo(STATE);
        assertThat(updated.getPhone()).isEqualTo(PHONE);
        assertThat(updated.getDistrict()).isEqualTo(DISTRICT);
        assertThat(updated.getIsPrivate()).isTrue();
    }

    private SchoolModel generateSchoolModel() {
        UUID id = UUID.randomUUID();
        AddressModel addressModel = AddressModel.builder()
                .withStreet1(STREET1)
                .withStreet2(STREET2)
                .withCity(CITY)
                .withState(STATE)
                .withZip(ZIP)
                .build();
        return SchoolModel.builder()
                .withId(id)
                .withName(NAME)
                .withAddress(addressModel)
                .withPhone(PHONE)
                .withDistrict(DISTRICT)
                .withIsPrivate(isPrivate)
                .build();
    }

}
