/*
 * Copyright 2020-2021 Aion Technology LLC
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

package io.aiontechnology.mentorsuccess.model.outbound;

import io.aiontechnology.mentorsuccess.model.enumeration.ResourceLocation;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.hateoas.RepresentationModel;

/**
 * Model object representing a mentor to be returned to a client.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
@Value
@EqualsAndHashCode(callSuper = true)
@Builder(setterPrefix = "with")
public class OutboundMentor extends RepresentationModel<OutboundMentor> {

    /** The first name of the mentor. */
    String firstName;

    /** The last name of the mentor. */
    String lastName;

    /** The mentor's email address. */
    String email;

    /** The mentor's work phone number. */
    String workPhone;

    /** The mentor's cell phone */
    String cellPhone;

    /** The mentor's availability */
    String availability;

    /** The mentor's location (online, offline or both) */
    ResourceLocation location;

    /** Has the mentor signed the media release? */
    Boolean mediaReleaseSigned;

    /** Has the mentor had a bockground check completed? */
    Boolean backgroundCheckCompleted;

}
