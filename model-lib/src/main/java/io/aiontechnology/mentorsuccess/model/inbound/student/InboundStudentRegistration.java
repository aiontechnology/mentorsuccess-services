/*
 * Copyright 2022 Aion Technology LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.aiontechnology.mentorsuccess.model.inbound.student;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

@Value
@Builder(setterPrefix = "with")
public class InboundStudentRegistration implements Serializable {

    String studentFirstName;
    String studentLastName;
    Integer grade;
    String parent1FirstName;
    String parent1LastName;
    String parent1PhoneNumber;
    String parent1EmailAddress;
    String parent1PreferredContactMethod;
    String parent2FirstName;
    String parent2LastName;
    String parent2PhoneNumber;
    String parent2EmailAddress;
    String parent2PreferredContactMethod;
    String teacher;
    String preferredSession;
    String emergencyContactFirstName;
    String emergencyContactLastName;
    String emergencyContactPhone;
    String parentSignature;

}
