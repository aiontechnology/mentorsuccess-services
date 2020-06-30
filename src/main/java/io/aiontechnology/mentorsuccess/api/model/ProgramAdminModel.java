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

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author Whitney Hunter
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(setterPrefix = "with")
@ToString
public class ProgramAdminModel extends RepresentationModel<ProgramAdminModel> implements Personnel {

    /** The first name of the program admin. */
    @NotNull(message = "{programAdmin.firstName.notNull}")
    @Size(max = 50, message = "{programAdmin.firstName.size}")
    private final String firstName;

    /** The last name of the program admin. */
    @NotNull(message = "{programAdmin.lastName.notNull}")
    @Size(max = 50, message = "{programAdmin.lastName.size}")
    private final String lastName;

    /** The program admin's email address. */
    @Pattern(regexp = "(\\w*@\\w*.\\w{3}){1,50}", message = "{programAdmin.email.invalid}")
    private final String email;

    /** The program admin's work phone number. */
    @Pattern(regexp = "\\d{10}", message = "{programAdmin.workPhone.invalid}")
    private final String workPhone;

    /** The program admin's cell phone. */
    @Pattern(regexp = "\\d{10}", message = "{programAdmin.cellPhone.invalid}")
    private final String cellPhone;

}
