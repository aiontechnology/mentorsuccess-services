/*
 * Copyright 2021 Aion Technology LLC
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.aiontechnology.mentorsuccess.model.inbound.student;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author Whitney Hunter
 * @since 0.12.0
 */
@Value
@Builder(setterPrefix = "with")
public class InboundStudentRegistration {

    @NotNull(message = "{registration.firstname.notNull}")
    @Size(max = 50, message = "{student.firstname.size}")
    String firstName;

    @NotNull(message = "{registration.lastname.notNull}")
    @Size(max = 50, message = "{student.lastname.size}")
    String lastName;

    @NotNull(message = "{registration.email.notNull}")
    @Pattern(regexp = "(^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$){1,50}", message = "{registration.email.invalid}")
    String parentEmailAddress;

}
