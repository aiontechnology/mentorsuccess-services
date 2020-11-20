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

package io.aiontechnology.mentorsuccess.model.inbound.student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import org.springframework.context.annotation.Bean;

import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.Set;

/**
 * A model object for the connection between schools, behaviors and people.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
@Value
@Builder(setterPrefix = "with")
public class InboundStudentBehavior {

    /** The associated leadership skill */
    @NotNull(message = "{studentbehavior.behavior.notNull")
    Set<String> behaviors;

    /** The URI of the associated person */
    @NotNull(message = "{studentbehavior.person.notNull}")
    URI teacher;

}
