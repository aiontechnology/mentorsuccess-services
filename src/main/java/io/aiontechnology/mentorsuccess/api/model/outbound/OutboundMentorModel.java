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

package io.aiontechnology.mentorsuccess.api.model.outbound;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

/**
 * Model object representing a mentor to be returned to a client.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(setterPrefix = "with")
public class OutboundMentorModel extends RepresentationModel<OutboundMentorModel> {

    /** The first name of the mentor. */
    private final String firstName;

    /** The last name of the mentor. */
    private final String lastName;

    /** The mentor's email address. */
    private final String email;

    /** The mentor's work phone number. */
    private final String workPhone;

    /** The mentor's cell phone */
    private final String cellPhone;

    /** The mentor's availability */
    private final String availability;

}
