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

package io.aiontechnology.mentorsuccess.api.mapping.tomodel.student;

import io.aiontechnology.mentorsuccess.api.mapping.OneWayMapper;
import io.aiontechnology.mentorsuccess.api.model.inbound.PersonModel;
import io.aiontechnology.mentorsuccess.api.model.outbound.student.OutboundContactModel;
import io.aiontechnology.mentorsuccess.entity.Person;
import io.aiontechnology.mentorsuccess.entity.StudentPersonRole;
import io.aiontechnology.mentorsuccess.util.PhoneService;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static io.aiontechnology.mentorsuccess.entity.RoleType.PRINCIPAL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link StudentPersonEntityToModelMapper}.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
public class StudentPersonRoleEntityToModelMapperTest {

    @Test
    void testMapping() throws Exception {
        // setup the fixture
        Person person = new Person();
        PersonModel personModel = PersonModel.builder().build();

        OneWayMapper<Person, PersonModel> personEntityToModelMapper = mock(OneWayMapper.class);
        when(personEntityToModelMapper.map(person)).thenReturn(Optional.of(personModel));

        StudentPersonRole studentPersonRole = new StudentPersonRole();
        studentPersonRole.setPerson(person);
        studentPersonRole.setPersonType(PRINCIPAL);

        StudentPersonEntityToModelMapper studentPersonEntityToModelMapper =
                new StudentPersonEntityToModelMapper(personEntityToModelMapper, new PhoneService());

        // execute the SUT
        Optional<OutboundContactModel> result = studentPersonEntityToModelMapper.map(studentPersonRole);

        // validation
        assertThat(result).isNotEmpty();
        assertThat(result.get().getFirstName()).isEqualTo(personModel.getFirstName());
        assertThat(result.get().getLastName()).isEqualTo(personModel.getLastName());
        assertThat(result.get().getWorkPhone()).isEqualTo(personModel.getWorkPhone());
        assertThat(result.get().getCellPhone()).isEqualTo(personModel.getCellPhone());
        assertThat(result.get().getEmail()).isEqualTo(personModel.getEmail());
        assertThat(result.get().getType()).isEqualTo(PRINCIPAL);
    }

    @Test
    void testNull() throws Exception {
        // setup the fixture
        OneWayMapper<Person, PersonModel> personEntityToModelMapper = mock(OneWayMapper.class);
        when(personEntityToModelMapper.map(any())).thenReturn(Optional.empty());

        StudentPersonEntityToModelMapper studentPersonEntityToModelMapper =
                new StudentPersonEntityToModelMapper(personEntityToModelMapper, new PhoneService());

        // execute the SUT
        Optional<OutboundContactModel> result = studentPersonEntityToModelMapper.map(null);

        // validation
        assertThat(result).isEmpty();
    }

}
