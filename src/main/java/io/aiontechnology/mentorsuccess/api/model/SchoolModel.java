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
 * Model that represents a school in the API.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(setterPrefix = "with")
@ToString
public class SchoolModel extends RepresentationModel<SchoolModel> {

    /** The school's id */
    private final UUID id;

    /** The name of the school */
    private final String name;

    /** The school's address */
    private final AddressModel address;

    /** The school's phone number */
    private final String phone;

    /** The school district that the school is in */
    private final String district;

    /** Indicates whether the school is private or public */
    private final Boolean isPrivate;

}
