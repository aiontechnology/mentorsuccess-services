/*
 * Copyright 2023 Aion Technology LLC
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

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Value
@Builder(setterPrefix = "with")
public class InboundStudentInformation implements Serializable {

    Set<String> leadershipSkills;

    Set<String> leadershipTraits;

    Set<String> behaviors;

    String teacherComment;

    @NotNull(message = "{studentInfo.question1.notNull}")
    Integer question1;

    @NotNull(message = "{studentInfo.question2.notNull}")
    Integer question2;

    @NotNull(message = "{studentInfo.question3.notNull}")
    Integer question3;

    @NotNull(message = "{studentInfo.question4.notNull}")
    Integer question4;

    @NotNull(message = "{studentInfo.question5.notNull}")
    Integer question5;

    @NotNull(message = "{studentInfo.question6.notNull}")
    Integer question6;

    @NotNull(message = "{studentInfo.question7.notNull}")
    Integer question7;

    @NotNull(message = "{studentInfo.question8.notNull}")
    Integer question8;

    @NotNull(message = "{studentInfo.question9.notNull}")
    Integer question9;

    @NotNull(message = "{studentInfo.question10.notNull}")
    int question10;

    @NotNull(message = "{studentInfo.question11.notNull}")
    Integer question11;

    @NotNull(message = "{studentInfo.question12.notNull}")
    Integer question12;

    @NotNull(message = "{studentInfo.question13.notNull}")
    int question13;

    @NotNull(message = "{studentInfo.question14.notNull}")
    Integer question14;

    @NotNull(message = "{studentInfo.question15.notNull}")
    Integer question15;

    @NotNull(message = "{studentInfo.question16.notNull}")
    Integer question16;

    @NotNull(message = "{studentInfo.question17.notNull}")
    Integer question17;

    @NotNull(message = "{studentInfo.question18.notNull}")
    Integer question18;

    @NotNull(message = "{studentInfo.question19.notNull}")
    Integer question19;

    @NotNull(message = "{studentInfo.question20.notNull}")
    Integer question20;

    @NotNull(message = "{studentInfo.question21.notNull}")
    Integer question21;

    @NotNull(message = "{studentInfo.question22.notNull}")
    Integer question22;

    @NotNull(message = "{studentInfo.question23.notNull}")
    Integer question23;

    @NotNull(message = "{studentInfo.question24.notNull}")
    Integer question24;

    @NotNull(message = "{studentInfo.question25.notNull}")
    Integer question25;

    @NotNull(message = "{studentInfo.question26.notNull}")
    Integer question26;

    @NotNull(message = "{studentInfo.question27.notNull}")
    Integer question27;

    @NotNull(message = "{studentInfo.question28.notNull}")
    Integer question28;

    @NotNull(message = "{studentInfo.question29.notNull}")
    Integer question29;

    @NotNull(message = "{studentInfo.question30.notNull}")
    Integer question30;

    @NotNull(message = "{studentInfo.question31.notNull}")
    Integer question31;

    @NotNull(message = "{studentInfo.question32.notNull}")
    Integer question32;

    @NotNull(message = "{studentInfo.question33.notNull}")
    Integer question33;

    @NotNull(message = "{studentInfo.question34.notNull}")
    Integer question34;

    @NotNull(message = "{studentInfo.question35.notNull}")
    Integer question35;

}
