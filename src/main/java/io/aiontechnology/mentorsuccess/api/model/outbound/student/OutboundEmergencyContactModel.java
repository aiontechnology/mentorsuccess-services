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

import io.aiontechnology.mentorsuccess.api.model.inbound.PersonModel;
import io.aiontechnology.mentorsuccess.entity.RoleType;
import io.aiontechnology.mentorsuccess.util.EnumNamePattern;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.net.URI;

/**
 * @author Whitney Hunter
 * @since 0.3.0
 */
@Data
@EqualsAndHashCode
@Builder(setterPrefix = "with")
public class OutboundEmergencyContactModel {

    private final RoleType type;

    /** The person's first name. */
    private final String firstName;

    /** The person's last name. */
    private final String lastName;

    /** The person's email. */
    private final String email;

    /** The person's work phone number. */
    private final String workPhone;

    /** The person's cell phone number. */
    private final String cellPhone;

}
