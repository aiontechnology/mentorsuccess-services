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

package io.aiontechnology.mentorsuccess.api.model.inbound.student;

import io.aiontechnology.mentorsuccess.entity.RoleType;
import io.aiontechnology.mentorsuccess.util.EnumNamePattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.net.URI;

/**
 * A model object for the connection between schools and people.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
@AllArgsConstructor
@Getter
public class InboundEmergencyContactModel {

    @NotNull(message = "{emergencycontact.type.notNull}")
    @EnumNamePattern(regexp = "PARENT|GUARDIAN", message = "{emergencycontact.type.invalid}")
    private final RoleType type;

    /** The person's first name. */
    @NotNull(message = "{emergencycontact.firstName.notNull}")
    @Size(max = 50, message = "{emergencycontact.firstName.size}")
    private final String firstName;

    /** The person's last name. */
    @NotNull(message = "{emergencycontact.lastName.notNull}")
    @Size(max = 50, message = "{emergencycontact.lastName.size}")
    private final String lastName;

    /** The person's email. */
    @Pattern(regexp = "(\\w*@\\w*.\\w{3}){1,50}", message = "{emergencycontact.email.invalid}")
    private final String email;

    /** The person's work phone number. */
    @Pattern(regexp = "\\(\\d{3}\\) \\d{3}-\\d{4}", message = "{emergencycontact.workPhone.invalid}")
    private final String workPhone;

    /** The person's cell phone number. */
    @Pattern(regexp = "\\(\\d{3}\\) \\d{3}-\\d{4}", message = "{emergencycontact.cellPhone.invalid}")
    private final String cellPhone;

}
