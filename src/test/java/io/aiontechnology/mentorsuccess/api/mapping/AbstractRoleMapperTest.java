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

import io.aiontechnology.mentorsuccess.api.model.TeacherModel;
import io.aiontechnology.mentorsuccess.entity.Role;
import io.aiontechnology.mentorsuccess.util.PhoneService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link AbstractRoleMapper}.
 *
 * @author <a href="mailto:whitney@aiontechnology.io">Whitney Hunter</a>
 * @since 1.0.0
 */
public class AbstractRoleMapperTest {

    @Test
    void testMapModelToEntity_newRole() throws Exception {
        // setup the fixture
        String FIRST_NAME = "Fred";
        String LAST_NAME = "Rogers";
        String EMAIL = "fred@rogers.com";
        String WORK_PHONE = "3601112222";
        String CELL_PHONE = "3603334444";
        TeacherModel teacherModel = TeacherModel.builder()
                .withFirstName(FIRST_NAME)
                .withLastName(LAST_NAME)
                .withEmail(EMAIL)
                .withWorkPhone(WORK_PHONE)
                .withCellPhone(CELL_PHONE)
                .build();

        TestRoleMapper mapper = new TestRoleMapper();

        // execute the SUT
        Role result = mapper.mapModelToEntity(teacherModel);

        // validation
        assertThat(result.getPerson().getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(result.getPerson().getLastName()).isEqualTo(LAST_NAME);
        assertThat(result.getPerson().getEmail()).isEqualTo(EMAIL);
        assertThat(result.getPerson().getWorkPhone()).isEqualTo(WORK_PHONE);
        assertThat(result.getPerson().getCellPhone()).isEqualTo(CELL_PHONE);
    }

    @Test
    void testMapModelToEntity_passedRole() throws Exception {
        // setup the fixture
        String FIRST_NAME = "Fred";
        String LAST_NAME = "Rogers";
        String EMAIL = "fred@rogers.com";
        String WORK_PHONE = "3601112222";
        String CELL_PHONE = "3603334444";
        TeacherModel teacherModel = TeacherModel.builder()
                .withFirstName(FIRST_NAME)
                .withLastName(LAST_NAME)
                .withEmail(EMAIL)
                .withWorkPhone(WORK_PHONE)
                .withCellPhone(CELL_PHONE)
                .build();
        Role role = new Role();

        TestRoleMapper mapper = new TestRoleMapper();

        // execute the SUT
        Role result = mapper.mapModelToEntity(teacherModel, role);

        // validation
        assertThat(result.getPerson().getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(result.getPerson().getLastName()).isEqualTo(LAST_NAME);
        assertThat(result.getPerson().getEmail()).isEqualTo(EMAIL);
        assertThat(result.getPerson().getWorkPhone()).isEqualTo(WORK_PHONE);
        assertThat(result.getPerson().getCellPhone()).isEqualTo(CELL_PHONE);
    }

    private static class TestRoleMapper extends AbstractRoleMapper<TeacherModel> {

        public TestRoleMapper() {
            super(new PhoneService());
        }

        @Override
        protected Role doMapRole(TeacherModel teacherModel, Role role) {
            return role;
        }
    }

}
