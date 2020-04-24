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

package io.aiontechnology.mentorsuccess.controller;

import io.aiontechnology.mentorsuccess.api.assembler.SchoolModelAssembler;
import io.aiontechnology.mentorsuccess.api.controller.SchoolController;
import io.aiontechnology.mentorsuccess.api.factory.SchoolFactory;
import io.aiontechnology.mentorsuccess.service.SchoolService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import javax.inject.Inject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SchoolController.class)
public class SchoolControllerTest {

    @Inject
    private MockMvc mockMvc;

    @MockBean
    private SchoolFactory schoolFactory;

    @MockBean
    private SchoolModelAssembler schoolModelAssembler;

    @MockBean
    private SchoolService schoolService;

    @Test
    void testCreateSchool() throws Exception {
        // setup the fixture

        // execute the SUT
//        mockMvc.perform(post("/api/v1/schools"))
//                .andDo(print())
//                .andExpect(status().isOk());

        // validation
    }

}
