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

package io.aiontechnology.mentorsuccess.api.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

/**
 * @author Whitney Hunter
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(setterPrefix = "with")
@ToString
public class ProgramAdminModel extends RepresentationModel<ProgramAdminModel> {

    /** The first name of the program admin. */
    private final String firstName;

    /** The last name of the program admin. */
    private final String lastName;

    /** The program admin's email address. */
    private final String email;

    /** The program admin's work phone number. */
    private final String workPhone;

    /** The program admin's cell phone. */
    private final String cellPhone;

}
