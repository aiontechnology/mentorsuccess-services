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

import io.aiontechnology.mentorsuccess.model.enumeration.RoleType;
import io.aiontechnology.mentorsuccess.model.validation.EnumNamePattern;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * A model object for school personnel.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@Value
@Builder(setterPrefix = "with")
public class InboundPersonnel {

    /** The personnel type */
    @NotNull(message = "{personnel.type.notNull}")
    @EnumNamePattern(regexp = "SOCIAL_WORKER|PRINCIPAL|COUNSELOR|STAFF", message = "{personnel.type.invalid}")
    RoleType type;

    /** The first name. */
    @NotNull(message = "{personnel.firstName.notNull}")
    @Size(max = 50, message = "{personnel.firstName.size}")
    String firstName;

    /** The last name. */
    @NotNull(message = "{personnel.lastName.notNull}")
    @Size(max = 50, message = "{personnel.lastName.size}")
    String lastName;

    /** The email address. */
    @Pattern(regexp = "(^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$){1,50}", message = "{personnel.email.invalid}")
    String email;

    /** The work phone number. */
    @Pattern(regexp = "\\(\\d{3}\\) \\d{3}-\\d{4}", message = "{personnel.workPhone.invalid}")
    String workPhone;

    /** The cell phone number. */
    @Pattern(regexp = "\\(\\d{3}\\) \\d{3}-\\d{4}", message = "{personnel.cellPhone.invalid}")
    String cellPhone;

}
