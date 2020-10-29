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

package io.aiontechnology.mentorsuccess.api.model.outbound.student;

import io.aiontechnology.mentorsuccess.entity.ContactMethod;
import io.aiontechnology.mentorsuccess.entity.RoleType;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Model object representing a contact to be returned to a client.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
@Data
@Builder(setterPrefix = "with")
public class OutboundContactModel {

    private final RoleType type;

    /** The contact's first name. */
    private final String firstName;

    /** The contact's last name. */
    private final String lastName;

    /** The contact's email. */
    private final String email;

    /** The contact's work phone number. */
    private final String workPhone;

    /** The contact's cell phone number. */
    private final String cellPhone;

    private Boolean isEmergencyContact;

    private ContactMethod preferredContactMethod;

    private String comment;

}
