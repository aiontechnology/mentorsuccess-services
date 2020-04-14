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

package io.aiontechnology.mentorsuccess.service;

import io.aiontechnology.mentorsuccess.entity.School;
import io.aiontechnology.mentorsuccess.repository.SchoolRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SchoolServiceTest {

    @Test
    void testCreateSchool() {
        // setup the fixture
        String name = "NAME";
        String street1 = "STREET1";
        String street2 = "STREET2";
        String city = "CITY";
        String state = "ST";
        String zip = "123456789";
        String phone = "1234567890";
        School school1 = new School(null, name, street1, street2, city, state, zip, phone);
        UUID uuid = UUID.randomUUID();
        School school2 = new School(uuid, name, street1, street2, city, state, zip, phone);

        SchoolRepository schoolRepository = mock(SchoolRepository.class);
        when(schoolRepository.save(any(School.class))).thenReturn(school2);

        SchoolService schoolService = new SchoolService(schoolRepository);

        // execute the SUT
        School result = schoolService.createSchool(school1);

        // validation
        Assertions.assertThat(result).isEqualTo(school2);
    }
}
