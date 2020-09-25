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

package io.aiontechnology.mentorsuccess.api.model.inbound.student;

import io.aiontechnology.mentorsuccess.api.model.inbound.reference.BehaviorModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.reference.LeadershipSkillModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.reference.LeadershipTraitModel;
import io.aiontechnology.mentorsuccess.entity.ResourceLocation;
import io.aiontechnology.mentorsuccess.util.EnumNamePattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.net.URI;
import java.util.Collection;

/**
 * A model object for students.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
@AllArgsConstructor
@Getter
public class InboundStudentModel implements StudentLeadershipSkillModelHolder, StudentLeadershipTraitModelHolder,
        StudentPersonModelHolder {

    @Size(max = 50, message = "{student.firstname.size}")
    private final String firstName;

    @Size(max = 50, message = "{student.lastname.size}")
    private final String lastName;

    @NotNull(message = "{student.grade.notNull}")
    @Min(value = 1, message = "{student.grade.invalid}")
    @Max(value = 6, message = "{student.grade.invalid}")
    private final Integer grade;

    @Size(max = 10, message = "{student.preferredTime.size}")
    private final String preferredTime;

    @NotNull(message = "{student.location.notNull}")
    @EnumNamePattern(regexp = "ONLINE|OFFLINE|BOTH", message = "{student.location.invalid}")
    private final ResourceLocation location;

    @NotNull(message = "{student.mediaRelease.notNull}")
    private Boolean mediaReleaseSigned;

    @NotNull
    private final URI teacher;

    private Collection<BehaviorModel> behaviors;

    private Collection<LeadershipSkillModel> leadershipSkills;

    private Collection<LeadershipTraitModel> leadershipTraits;

    private Collection<InboundEmergencyContactModel> emergencyContacts;

}
