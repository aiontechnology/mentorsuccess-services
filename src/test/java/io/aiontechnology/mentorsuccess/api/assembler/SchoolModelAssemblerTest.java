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

import io.aiontechnology.mentorsuccess.api.controller.SchoolController;
import io.aiontechnology.mentorsuccess.api.mapping.tomodel.misc.AddressEntityToModelMapper;
import io.aiontechnology.mentorsuccess.api.mapping.tomodel.school.SchoolEntityToModelMapper;
import io.aiontechnology.mentorsuccess.api.model.inbound.SchoolModel;
import io.aiontechnology.mentorsuccess.entity.School;
import io.aiontechnology.mentorsuccess.util.PhoneService;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.IanaLinkRelations;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * Tests for {@link SchoolModelAssembler}.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
public class SchoolModelAssemblerTest {

    @Test
    void shouldMapToModel() {
        // setup the fixture
        UUID id = UUID.randomUUID();
        String name = "NAME";
        String street1 = "STREET1";
        String street2 = "STREET2";
        String city = "CITY";
        String state = "ST";
        String zip = "123456789";
        String phone = "1234567890";
        String district = "DISTRICT";
        Boolean isPrivate = Boolean.TRUE;
        Boolean isActive = Boolean.TRUE;
        School school = new School(id, name, street1, street2, city, state, zip, phone, district, isPrivate, isActive, Collections.EMPTY_LIST, Collections.EMPTY_LIST);

        SchoolModelAssembler assembler = new SchoolModelAssembler(
                new SchoolEntityToModelMapper(new AddressEntityToModelMapper(), new PhoneService()), new LinkHelper<>());

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
        assertThat(schoolModel.getPhone()).isEqualTo("(123) 456-7890");
        assertThat(schoolModel.getDistrict()).isEqualTo(district);
        assertThat(schoolModel.getIsPrivate()).isTrue();
    }

    @Test
    void shouldContainLinks() {
        // setup the fixture
        UUID id = UUID.randomUUID();
        String name = "NAME";
        String street1 = "STREET1";
        String street2 = "STREET2";
        String city = "CITY";
        String state = "ST";
        String zip = "123456789";
        String phone = "1234567890";
        String district = "DISTRICT";
        Boolean isPrivate = Boolean.TRUE;
        Boolean isActive = Boolean.TRUE;
        School school = new School(id, name, street1, street2, city, state, zip, phone, district, isPrivate, isActive, Collections.EMPTY_LIST, Collections.EMPTY_LIST);

        LinkProvider<SchoolModel, School> linkProvider = (schoolModel, s) ->
                Arrays.asList(
                        linkTo(SchoolController.class).slash(school.getId()).withSelfRel(),
                        linkTo(SchoolController.class).slash(school.getId()).slash("teachers").withRel("teachers")
                );

        SchoolModelAssembler assembler = new SchoolModelAssembler(new SchoolEntityToModelMapper(
                new AddressEntityToModelMapper(), new PhoneService()), new LinkHelper<>());

        // execute the SUT
        SchoolModel schoolModel = assembler.toModel(school, linkProvider);

        // validation
        assertThat(schoolModel.getLink(IanaLinkRelations.SELF).get()).isNotNull();
    }

}
