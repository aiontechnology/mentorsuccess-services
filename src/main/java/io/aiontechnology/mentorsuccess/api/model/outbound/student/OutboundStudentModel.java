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

package io.aiontechnology.mentorsuccess.api.model.outbound.student;

import io.aiontechnology.mentorsuccess.api.model.inbound.TeacherModel;
import io.aiontechnology.mentorsuccess.entity.ResourceLocation;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.util.Collection;

/**
 * @author Whitney Hunter
 * @since 0.3.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(setterPrefix = "with")
public class OutboundStudentModel extends RepresentationModel<OutboundStudentModel> {

    private final String firstName;

    private final String lastName;

    private final Integer grade;

    private final String allergyInfo;

    private final String preferredTime;

    private final ResourceLocation location;

    private Boolean mediaReleaseSigned;

    private final OutboundStudentTeacherModel teacher;

    private Collection<String> interests;

    private Collection<String> behaviors;

    private Collection<String> leadershipSkills;

    private Collection<String> leadershipTraits;

    private Collection<OutboundContactModel> contacts;

}
