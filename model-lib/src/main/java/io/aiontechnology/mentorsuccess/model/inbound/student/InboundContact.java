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

package io.aiontechnology.mentorsuccess.model.inbound.student;

import io.aiontechnology.mentorsuccess.model.enumeration.ContactMethod;
import io.aiontechnology.mentorsuccess.model.enumeration.RoleType;
import io.aiontechnology.mentorsuccess.model.validation.EnumNamePattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * A model object for the connection between schools and people.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
@AllArgsConstructor
@Builder(setterPrefix = "with")
@Getter
public class InboundContact {

    @NotNull(message = "{contact.type.notNull}")
    @EnumNamePattern(regexp = "PARENT_GUARDIAN|GRANDPARENT", message = "{contact.type.invalid}")
    private final RoleType type;

    /** The contact's first name. */
    @NotNull(message = "{contact.firstName.notNull}")
    @Size(max = 50, message = "{contact.firstName.size}")
    private final String firstName;

    /** The contact's last name. */
    @NotNull(message = "{contact.lastName.notNull}")
    @Size(max = 50, message = "{contact.lastName.size}")
    private final String lastName;

    /** The contact's email. */
    @Pattern(regexp = "(\\w*@\\w*.\\w{3}){1,50}", message = "{contact.email.invalid}")
    private final String email;

    /** The contact's work phone number. */
    @Pattern(regexp = "\\(\\d{3}\\) \\d{3}-\\d{4}", message = "{contact.workPhone.invalid}")
    private final String workPhone;

    /** The contact's cell phone number. */
    @Pattern(regexp = "\\(\\d{3}\\) \\d{3}-\\d{4}", message = "{contact.cellPhone.invalid}")
    private final String cellPhone;

    @NotNull(message = "{contact.isEmergencyContact.notNull}")
    private final Boolean isEmergencyContact;

    @EnumNamePattern(regexp = "CELLPHONE|WORKPHONE|EMAIL", message = "{contact.preferredContactMethod.invalid}")
    private final ContactMethod preferredContactMethod;

    @Size(max = 255, message = "{contact.comment.size}")
    private final String comment;

}
