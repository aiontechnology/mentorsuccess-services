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

package io.aiontechnology.mentorsuccess.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.aiontechnology.mentorsuccess.api.assembler.LinkHelper;
import io.aiontechnology.mentorsuccess.api.assembler.SchoolModelAssembler;
import io.aiontechnology.mentorsuccess.api.assembler.TeacherModelAssembler;
import io.aiontechnology.mentorsuccess.api.mapping.AddressMapper;
import io.aiontechnology.mentorsuccess.api.mapping.SchoolMapper;
import io.aiontechnology.mentorsuccess.api.mapping.TeacherMapper;
import io.aiontechnology.mentorsuccess.api.model.AddressModel;
import io.aiontechnology.mentorsuccess.api.model.SchoolModel;
import io.aiontechnology.mentorsuccess.entity.School;
import io.aiontechnology.mentorsuccess.service.SchoolService;
import io.aiontechnology.mentorsuccess.util.PhoneService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.inject.Inject;
import java.util.Optional;
import java.util.UUID;

import static io.aiontechnology.mentorsuccess.api.controller.SchoolControllerTest.TestContext;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SchoolController.class)
@Import(TestContext.class)
public class SchoolControllerTest {

    private static final String NAME = "TEST";
    private static final String STREET1 = "STREET1";
    private static final String STREET2 = "STREET2";
    private static final String CITY = "CITY";
    private static final String STATE = "ST";
    private static final String ZIP = "12345";
    private static final String PHONE = "1234567890";
    private static final String DISTRICT = "DISTRICT";
    private static final Boolean IS_PRIVITE = Boolean.TRUE;

    @Inject
    private MockMvc mockMvc;

    @MockBean
    private SchoolService schoolService;

    @Test()
    void shouldCreateASchool() throws Exception {
        // setup the fixture
        AddressModel addressModel = AddressModel.builder()
                .withStreet1(STREET1)
                .withStreet2(STREET2)
                .withCity(CITY)
                .withState(STATE)
                .withZip(ZIP)
                .build();
        SchoolModel schoolModel = SchoolModel.builder()
                .withName(NAME)
                .withAddress(addressModel)
                .withPhone(PHONE)
                .withDistrict(DISTRICT)
                .withIsPrivate(IS_PRIVITE)
                .build();
        ObjectMapper mapper = new ObjectMapper();
        String body = mapper.writer().writeValueAsString(schoolModel);

        UUID id = UUID.randomUUID();
        School school = new School();
        school.setId(id);
        school.setName(NAME);
        school.setStreet1(STREET1);
        school.setStreet2(STREET2);
        school.setCity(CITY);
        school.setState(STATE);
        school.setZip(ZIP);
        school.setPhone(PHONE);
        school.setDistrict(DISTRICT);
        school.setIsPrivate(IS_PRIVITE);

        when(schoolService.createSchool(any(School.class))).thenReturn(school);

        // execute the SUT
        ResultActions resultActions = mockMvc.perform(post("/api/v1/schools")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body));

        // validation
        resultActions.andExpect(jsonPath("$.id", is(id.toString())))
                .andExpect(jsonPath("$.name", is(NAME)))
                .andExpect(jsonPath("$.address.street1", is(STREET1)))
                .andExpect(jsonPath("$.address.street2", is(STREET2)))
                .andExpect(jsonPath("$.address.city", is(CITY)))
                .andExpect(jsonPath("$.address.state", is(STATE)))
                .andExpect(jsonPath("$.address.zip", is(ZIP)))
                .andExpect(jsonPath("$.phone", is(PHONE)))
                .andExpect(jsonPath("$.district", is(DISTRICT)))
                .andExpect(jsonPath("$.isPrivate", is(IS_PRIVITE)))
                .andExpect(status().isCreated());

        verify(schoolService).createSchool(any(School.class));
    }

    @Test
    void shouldRetrieveASchool() throws Exception {
        // setup the fixture
        UUID id = UUID.randomUUID();
        School school = new School();
        school.setId(id);
        school.setName(NAME);
        school.setStreet1(STREET1);
        school.setStreet2(STREET2);
        school.setCity(CITY);
        school.setState(STATE);
        school.setZip(ZIP);
        school.setPhone(PHONE);
        school.setDistrict(DISTRICT);
        school.setIsPrivate(IS_PRIVITE);

        when(schoolService.getSchool(id)).thenReturn(Optional.of(school));

        // execute the SUT
        ResultActions resultActions = mockMvc.perform(get("/api/v1/schools/" + id.toString())
                .contentType(MediaType.APPLICATION_JSON));

        // validation
        resultActions.andExpect(jsonPath("$.id", is(id.toString())))
                .andExpect(jsonPath("$.name", is(NAME)))
                .andExpect(jsonPath("$.address.street1", is(STREET1)))
                .andExpect(jsonPath("$.address.street2", is(STREET2)))
                .andExpect(jsonPath("$.address.city", is(CITY)))
                .andExpect(jsonPath("$.address.state", is(STATE)))
                .andExpect(jsonPath("$.address.zip", is(ZIP)))
                .andExpect(jsonPath("$.phone", is(PHONE)))
                .andExpect(jsonPath("$.district", is(DISTRICT)))
                .andExpect(jsonPath("$.isPrivate", is(IS_PRIVITE)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldNotFindSchool() throws Exception {
        // setup the fixture
        UUID id = UUID.randomUUID();
        when(schoolService.getSchool(id)).thenReturn(Optional.empty());

        // execute the SUT
        ResultActions resultActions = mockMvc.perform(get("/api/v1/schools/" + id.toString())
                .contentType(MediaType.APPLICATION_JSON));

        // validation
        resultActions.andExpect(status().isNotFound());
    }

    @Test
    void shouldGetAllSchools() throws Exception {
        // setup the fixture

        // execute the SUT
        mockMvc.perform(get("/api/v1/schools"))
                .andExpect(status().isOk());

        // validation
        verify(schoolService).getAllSchools();
    }

    @Test
    void shouldRemoveASchool() throws Exception {
        // setup the fixture
        UUID id = UUID.randomUUID();
        School school = new School();
        school.setId(id);
        school.setName(NAME);
        school.setStreet1(STREET1);
        school.setStreet2(STREET2);
        school.setCity(CITY);
        school.setState(STATE);
        school.setZip(ZIP);
        school.setPhone(PHONE);
        school.setDistrict(DISTRICT);
        school.setIsPrivate(IS_PRIVITE);

        when(schoolService.getSchool(id)).thenReturn(Optional.of(school));

        // execute the SUT
        ResultActions resultActions = mockMvc.perform(delete("/api/v1/schools/" + id.toString()));

        // validation
        resultActions.andExpect(status().isNoContent());
        verify(schoolService).deactivateSchool(school);
    }

    @Test()
    void shouldUpdateASchool() throws Exception {
        // setup the fixture
        AddressModel addressModel = AddressModel.builder()
                .withStreet1(STREET1)
                .withStreet2(STREET2)
                .withCity(CITY)
                .withState(STATE)
                .withZip(ZIP)
                .build();
        SchoolModel schoolModel = SchoolModel.builder()
                .withName(NAME)
                .withAddress(addressModel)
                .withPhone(PHONE)
                .withDistrict(DISTRICT)
                .withIsPrivate(IS_PRIVITE)
                .build();
        ObjectMapper mapper = new ObjectMapper();
        String body = mapper.writer().writeValueAsString(schoolModel);

        UUID id = UUID.randomUUID();
        School school = new School();
        school.setId(id);
        school.setName(NAME);
        school.setStreet1(STREET1);
        school.setStreet2(STREET2);
        school.setCity(CITY);
        school.setState(STATE);
        school.setZip(ZIP);
        school.setPhone(PHONE);
        school.setDistrict(DISTRICT);
        school.setIsPrivate(IS_PRIVITE);

        when(schoolService.getSchool(id)).thenReturn(Optional.of(school));
        when(schoolService.updateSchool(any(School.class))).thenReturn(school);

        // execute the SUT
        ResultActions resultActions = mockMvc.perform(put("/api/v1/schools/" + id.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(body));

        // validation
        resultActions.andExpect(jsonPath("$.id", is(id.toString())))
                .andExpect(jsonPath("$.name", is(NAME)))
                .andExpect(jsonPath("$.address.street1", is(STREET1)))
                .andExpect(jsonPath("$.address.street2", is(STREET2)))
                .andExpect(jsonPath("$.address.city", is(CITY)))
                .andExpect(jsonPath("$.address.state", is(STATE)))
                .andExpect(jsonPath("$.address.zip", is(ZIP)))
                .andExpect(jsonPath("$.phone", is(PHONE)))
                .andExpect(jsonPath("$.district", is(DISTRICT)))
                .andExpect(jsonPath("$.isPrivate", is(IS_PRIVITE)))
                .andExpect(status().isOk());

        verify(schoolService).updateSchool(any(School.class));
    }

    @TestConfiguration
    static class TestContext {

        @Bean
        AddressMapper addressMapper() {
            return new AddressMapper();
        }

        @Bean
        SchoolMapper schoolMapper(AddressMapper addressMapper) {
            return new SchoolMapper(addressMapper, new PhoneService());
        }

        @Bean
        SchoolModelAssembler schoolModelAssembler(SchoolMapper schoolMapper) {
            return new SchoolModelAssembler(schoolMapper, new LinkHelper<>());
        }

        @Bean
        TeacherModelAssembler teacherModelAssembler() {
            return new TeacherModelAssembler(new TeacherMapper(new PhoneService()), new LinkHelper<>());
        }

    }

}
