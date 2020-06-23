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

import io.aiontechnology.mentorsuccess.api.error.NotImplementedException;
import io.aiontechnology.mentorsuccess.api.model.AddressModel;
import io.aiontechnology.mentorsuccess.entity.School;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for {@link AddressMapper}.
 *
 * @author <a href="mailto:whitney@aiontechnology.io">Whitney Hunter</a>
 * @since 1.0.0
 */
public class AddressMapperTest {

    private static final String STREET1 = "STREET1";
    private static final String STREET2 = "STREET2";
    private static final String CITY = "CITY";
    private static final String STATE = "WA";
    private static final String ZIP = "98682";

    @Test
    void testMapEntityToModel() throws Exception {
        // setup the fixture
        School school = new School();
        school.setStreet1(STREET1);
        school.setStreet2(STREET2);
        school.setCity(CITY);
        school.setState(STATE);
        school.setZip(ZIP);

        AddressMapper mapper = new AddressMapper();

        // execute the SUT
        AddressModel result = mapper.mapEntityToModel(school);

        // validation
        assertThat(result.getStreet1()).isEqualTo(STREET1);
        assertThat(result.getStreet2()).isEqualTo(STREET2);
        assertThat(result.getCity()).isEqualTo(CITY);
        assertThat(result.getState()).isEqualTo(STATE);
        assertThat(result.getZip()).isEqualTo(ZIP);
    }

    @Test
    void testMapModelToEntity() throws Exception {
        // setup the fixture
        AddressModel addressModel = AddressModel.builder()
                .withStreet1(STREET1)
                .withStreet2(STREET2)
                .withCity(CITY)
                .withState(STATE)
                .withZip(ZIP)
                .build();

        AddressMapper mapper = new AddressMapper();

        // execute the SUT
        assertThrows(NotImplementedException.class, () ->
                mapper.mapModelToEntity(addressModel));

        // validation
    }

    @Test
    void testMapModelToEntity_provideEntity() throws Exception {
        // setup the fixture
        AddressModel addressModel = AddressModel.builder()
                .withStreet1(STREET1)
                .withStreet2(STREET2)
                .withCity(CITY)
                .withState(STATE)
                .withZip(ZIP)
                .build();
        School school = new School();

        AddressMapper mapper = new AddressMapper();

        // execute the SUT
        assertThrows(NotImplementedException.class, () ->
                mapper.mapModelToEntity(addressModel, school));

        // validation
    }

}
