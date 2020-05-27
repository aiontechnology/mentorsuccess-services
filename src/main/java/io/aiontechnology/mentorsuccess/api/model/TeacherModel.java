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
 * Model that represents a teacher in the API.
 *
 * @author Whitney Hunter
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(setterPrefix = "with")
@ToString
public class TeacherModel extends RepresentationModel<TeacherModel> implements Personnel {

    /** The first name of the teacher. */
    private final String firstName;

    /** The last name of the teacher. */
    private final String lastName;

    /** The teacher's email address. */
    private final String email;

    /** The teacher's work phone number. */
    private final String workPhone;

    /** The teacher's cell phone */
    private final String cellPhone;

    /** First grade taught by teacher. */
    private final Integer grade1;

    /** Second grade taught by teacher. Null if there is only one grade. */
    private final Integer grade2;

}
