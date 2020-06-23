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
import lombok.ToString;

import javax.validation.constraints.Size;

@Data
@Builder(setterPrefix = "with")
@ToString
public class AddressModel {

    @Size(max = 50, message = "{address.street1.size}")
    private final String street1;

    @Size(max = 50, message = "{address.street2.size}")
    private final String street2;

    @Size(max = 50,  message = "{address.city.size}")
    private final String city;

    @Size(min = 2, max = 2, message = "{address.state.size}")
    private final String state;

    @Size(min = 5, max = 9, message = "{address.zip.size}")
    private final String zip;

}
