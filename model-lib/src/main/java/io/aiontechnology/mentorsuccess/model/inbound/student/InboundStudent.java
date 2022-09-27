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

package io.aiontechnology.mentorsuccess.model.inbound.student;

import io.aiontechnology.mentorsuccess.model.enumeration.ResourceLocation;
import io.aiontechnology.mentorsuccess.model.validation.EnumNamePattern;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.NonFinal;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

/**
 * A model object for students.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
@Value
@Builder(setterPrefix = "with")
@NonFinal
public class InboundStudent implements Serializable {

    @Size(max = 20, message = "{student.id.size}")
    String studentId;

    @NotNull(message = "{student.firstname.notNull}")
    @Size(max = 50, message = "{student.firstname.size}")
    String firstName;

    @NotNull(message = "{student.lastname.notNull}")
    @Size(max = 50, message = "{student.lastname.size}")
    String lastName;

    @NotNull(message = "{student.grade.notNull}")
    @Min(value = 0, message = "{student.grade.invalid}")
    @Max(value = 5, message = "{student.grade.invalid}")
    Integer grade;

    @Size(max = 30, message = "{student.preferredTime.size}")
    String preferredTime;

    @Size(max = 30, message = "{student.actualTime.size}")
    String actualTime;

    Date startDate;

    @NotNull(message = "{student.location.notNull}")
    @EnumNamePattern(regexp = "ONLINE|OFFLINE|BOTH", message = "{student.location.invalid}")
    ResourceLocation location;

    @NotNull(message = "{student.mediaRelease.notNull}")
    Boolean mediaReleaseSigned;

    Integer preBehavioralAssessment;

    Integer postBehavioralAssessment;

    @NotNull(message = "{student.teacher.notNull}")
    @Valid
    InboundStudentTeacher teacher;

    @Valid
    InboundStudentMentor mentor;

    Set<String> behaviors;

    Set<String> interests;

    Set<String> leadershipSkills;

    Set<String> leadershipTraits;

    @Valid
    Collection<InboundContact> contacts;

}
