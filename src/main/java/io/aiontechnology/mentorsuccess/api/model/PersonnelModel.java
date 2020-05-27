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
public class PersonnelModel extends RepresentationModel<PersonnelModel> implements Personnel {

    /** The personnel type */
    private final String type;

    /** The first name. */
    private final String firstName;

    /** The last name. */
    private final String lastName;

    /** The email address. */
    private final String email;

    /** The work phone number. */
    private final String workPhone;

    /** The cell phone number. */
    private final String cellPhone;

}
