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

import io.aiontechnology.mentorsuccess.model.enumeration.ResourceLocation;
import io.aiontechnology.mentorsuccess.model.inbound.reference.InboundBehavior;
import io.aiontechnology.mentorsuccess.model.inbound.reference.InboundInterest;
import io.aiontechnology.mentorsuccess.model.inbound.reference.InboundLeadershipSkill;
import io.aiontechnology.mentorsuccess.model.inbound.reference.InboundLeadershipTrait;
import io.aiontechnology.mentorsuccess.model.validation.EnumNamePattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

/**
 * A model object for students.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
@AllArgsConstructor
@Builder(setterPrefix = "with")
@Getter
public class InboundStudent {

    @NotNull(message = "{student.firstname.notNull}")
    @Size(max = 50, message = "{student.firstname.size}")
    private final String firstName;

    @NotNull(message = "{student.lastname.notNull}")
    @Size(max = 50, message = "{student.lastname.size}")
    private final String lastName;

    @NotNull(message = "{student.grade.notNull}")
    @Min(value = 0, message = "{student.grade.invalid}")
    @Max(value = 5, message = "{student.grade.invalid}")
    private final Integer grade;

    @Size(max = 255, message = "{student.alergyInfo.size}")
    private final String allergyInfo;

    @Size(max = 10, message = "{student.preferredTime.size}")
    private final String preferredTime;

    @NotNull(message = "{student.location.notNull}")
    @EnumNamePattern(regexp = "ONLINE|OFFLINE|BOTH", message = "{student.location.invalid}")
    private final ResourceLocation location;
    @NotNull(message = "{student.teacher.notNull}")
    @Valid
    private final InboundStudentTeacher teacher;
    @NotNull(message = "{student.mediaRelease.notNull}")
    private final Boolean mediaReleaseSigned;
    private final Set<InboundBehavior> behaviors;

    private final Set<InboundInterest> interests;

    private final Set<InboundLeadershipSkill> leadershipSkills;

    private final Set<InboundLeadershipTrait> leadershipTraits;

    @Valid
    private final Collection<InboundContact> contacts;

}
