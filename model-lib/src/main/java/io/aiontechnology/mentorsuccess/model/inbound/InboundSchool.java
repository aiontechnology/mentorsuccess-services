/*
 * Copyright 2020-2022 Aion Technology LLC
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

import io.aiontechnology.mentorsuccess.model.validation.ValidOrNullPhone;
import lombok.Builder;
import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

/**
 * A model object for schools.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@Value
@Builder(setterPrefix = "with")
public class InboundSchool {

    /** The school's id */
    UUID id;

    /** The name of the school */
    @NotNull(message = "{school.name.notNull}")
    @Size(max = 100, message = "{school.name.size}")
    String name;

    @Size(max = 30, message = "{schoolsession.label.size}")
    String initialSessionLabel;

    /** The school's address */
    @Valid
    InboundAddress address;

    /** The school's phone number */
    @ValidOrNullPhone(message = "{school.phone.invalid}")
    String phone;

    /** The school district that the school is in */
    @Size(max = 50, message = "{school.district.size}")
    String district;

    /** Indicates whether the school is private or public */
    Boolean isPrivate;

}
