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

package io.aiontechnology.mentorsuccess.model.inbound;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * A model object for teachers.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@Data
@Builder(setterPrefix = "with")
@ToString
public class InboundTeacher {

    /** The first name of the teacher. */
    @NotNull(message = "{teacher.firstName.notNull}")
    @Size(max = 50, message = "{teacher.firstName.size}")
    private final String firstName;

    /** The last name of the teacher. */
    @NotNull(message = "{teacher.lastName.notNull}")
    @Size(max = 50, message = "{teacher.lastName.size}")
    private final String lastName;

    /** The teacher's email address. */
    @Pattern(regexp = "(\\w*@\\w*.\\w{3}){1,50}", message = "{teacher.email.invalid}")
    private final String email;

    /** The teacher's work phone number. */
    @Pattern(regexp = "\\(\\d{3}\\) \\d{3}-\\d{4}", message = "{teacher.workPhone.invalid}")
    private final String workPhone;

    /** The teacher's cell phone */
    @Pattern(regexp = "\\(\\d{3}\\) \\d{3}-\\d{4}", message = "{teacher.cellPhone.invalid}")
    private final String cellPhone;

    /** First grade taught by teacher. */
    @Min(value = 1, message = "{teacher.grade1.invalid}")
    @Max(value = 6, message = "{teacher.grade1.invalid}")
    private final Integer grade1;

    /** Second grade taught by teacher. Null if there is only one grade. */
    @Min(value = 1, message = "{teacher.grade1.invalid}")
    @Max(value = 6, message = "{teacher.grade1.invalid}")
    private final Integer grade2;

}
