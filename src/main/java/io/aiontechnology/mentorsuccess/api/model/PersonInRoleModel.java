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

import java.util.UUID;

/**
 * A model object for a person in a role.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(setterPrefix = "with")
@ToString
public class PersonInRoleModel extends RepresentationModel<PersonInRoleModel> {

    /** The person's id. */
    private final UUID id;

    /** The person's role. */
    private final String roleName;

    /** The person's first name. */
    private final String firstName;

    /** The person's last name. */
    private final String lastName;

    /** The person's email. */
    private final String email;

    /** The person's work phone. */
    private final String workPhone;

    /** The person's cell phone. */
    private final String cellPhone;

}
