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

import io.aiontechnology.mentorsuccess.entity.Role;
import io.aiontechnology.mentorsuccess.util.EnumNamePattern;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static io.aiontechnology.mentorsuccess.entity.Role.*;

/**
 * @author Whitney Hunter
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(setterPrefix = "with")
@ToString
public class PersonnelModel extends RepresentationModel<PersonnelModel> implements Personnel {

    /** The personnel type */
    @NotNull(message = "{personnel.type.notNull}")
    @EnumNamePattern(regexp = "SOCIAL_WORKER|PRINCIPAL|COUNSELOR|STAFF", message = "{personnel.type.invalid}")
    private final RoleType type;

    /** The first name. */
    @NotNull(message = "{personnel.firstName.notNull}")
    @Size(max = 50, message = "{personnel.firstName.size}")
    private final String firstName;

    /** The last name. */
    @NotNull(message = "{personnel.lastName.notNull}")
    @Size(max = 50, message = "{personnel.lastName.size}")
    private final String lastName;

    /** The email address. */
    @Pattern(regexp = "(\\w*@\\w*.\\w{3}){1,50}", message = "{personnel.email.invalid}")
    private final String email;

    /** The work phone number. */
    @Pattern(regexp = "\\(\\d{3}\\) \\d{3}-\\d{4}", message = "{personnel.workPhone.invalid}")
    private final String workPhone;

    /** The cell phone number. */
    @Pattern(regexp = "\\(\\d{3}\\) \\d{3}-\\d{4}", message = "{personnel.cellPhone.invalid}")
    private final String cellPhone;

}
