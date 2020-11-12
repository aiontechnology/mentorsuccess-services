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

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

/**
 * A model object for people.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@Data
@Builder(setterPrefix = "with")
@ToString
public class InboundPerson {

    /** The person's id. */
    private final UUID id;

    /** The person's first name. */
    @NotNull(message = "{person.firstName.notNull}")
    @Size(max = 50, message = "{person.firstName.size}")
    private final String firstName;

    /** The person's last name. */
    @NotNull(message = "{person.lastName.notNull}")
    @Size(max = 50, message = "{person.lastName.size}")
    private final String lastName;

    /** The person's email. */
    @Pattern(regexp = "(\\w*@\\w*.\\w{3}){1,50}", message = "{person.email.invalid}")
    private final String email;

    /** The person's work phone number. */
    @Pattern(regexp = "\\(\\d{3}\\) \\d{3}-\\d{4}", message = "{person.workPhone.invalid}")
    private final String workPhone;

    /** The person's cell phone number. */
    @Pattern(regexp = "\\(\\d{3}\\) \\d{3}-\\d{4}", message = "{person.cellPhone.invalid}")
    private final String cellPhone;

}
