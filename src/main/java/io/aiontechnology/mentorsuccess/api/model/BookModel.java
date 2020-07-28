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
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.UUID;

/**
 * Model that represents a book in the API.
 *
 * @author <a href="mailto:whitney@aiontechnology.io">Whitney Hunter</a>
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(setterPrefix = "with")
@ToString
public class BookModel extends ResourceModel<BookModel> implements InterestModelHolder, LeadershipSkillModelHolder,
        LeadershipTraitModelHolder, PhonogramModelHolder {

    private final UUID id;

    @NotNull(message = "{book.title.notNull}")
    @Size(max = 40, message = "{book.title.size}")
    private final String title;

    @Size(max = 30, message = "{book.author.size}")
    private final String author;

    @NotNull(message = "{book.gradeLevel.notNull}")
    @Min(value = 1, message = "{book.gradeLevel.invalid}")
    @Max(value = 6, message = "{book.gradeLevel.invalid}")
    private final Integer gradeLevel;

    private final Collection<InterestModel> interests;

    private final Collection<LeadershipTraitModel> leadershipTraits;

    private final Collection<LeadershipSkillModel> leadershipSkills;

    private final Collection<PhonogramModel> phonograms;

}
