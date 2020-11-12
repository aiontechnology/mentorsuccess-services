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

package io.aiontechnology.mentorsuccess.api.mapping.tomodel.school;

import io.aiontechnology.mentorsuccess.api.mapping.tomodel.misc.AddressEntityToModelMapper;
import io.aiontechnology.mentorsuccess.entity.School;
import io.aiontechnology.mentorsuccess.model.outbound.OutboundSchool;
import io.aiontechnology.mentorsuccess.util.PhoneService;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Whitney Hunter
 * @since 0.3.0
 */
public class SchoolEntityToModelMapperTest {

    @Test
    void testMapping() throws Exception {
        // setup the fixture
        UUID id = UUID.randomUUID();
        String name = "NAME";
        String street1 = "STREET1";
        String street2 = "STREET2";
        String city = "CITY";
        String state = "STATE";
        String zip = "ZIP";
        String phone = "PHONE";
        String district = "DISTRICT";
        boolean isPrivate = true;

        School school = new School();
        school.setId(id);
        school.setName(name);
        school.setStreet1(street1);
        school.setStreet2(street2);
        school.setCity(city);
        school.setState(state);
        school.setZip(zip);
        school.setPhone(phone);
        school.setDistrict(district);
        school.setIsPrivate(isPrivate);

        SchoolEntityToModelMapper schoolEntityToModelMapper = new SchoolEntityToModelMapper(
                new AddressEntityToModelMapper(), new PhoneService());

        // execute the SUT
        Optional<OutboundSchool> result = schoolEntityToModelMapper.map(school);

        // validation
        assertThat(result).isNotEmpty();
        assertThat(result.get().getId()).isEqualTo(id);
        assertThat(result.get().getName()).isEqualTo(name);
        assertThat(result.get().getAddress().getStreet1()).isEqualTo(street1);
        assertThat(result.get().getAddress().getStreet2()).isEqualTo(street2);
        assertThat(result.get().getAddress().getCity()).isEqualTo(city);
        assertThat(result.get().getAddress().getState()).isEqualTo(state);
        assertThat(result.get().getAddress().getZip()).isEqualTo(zip);
        assertThat(result.get().getPhone()).isEqualTo(phone);
        assertThat(result.get().getDistrict()).isEqualTo(district);
        assertThat(result.get().getIsPrivate()).isEqualTo(isPrivate);
    }

    @Test
    void testNull() throws Exception {
        // setup the fixture
        SchoolEntityToModelMapper schoolEntityToModelMapper = new SchoolEntityToModelMapper(
                new AddressEntityToModelMapper(), new PhoneService());

        // execute the SUT
        Optional<OutboundSchool> result = schoolEntityToModelMapper.map(null);

        // validation
        assertThat(result).isEmpty();
    }

}
