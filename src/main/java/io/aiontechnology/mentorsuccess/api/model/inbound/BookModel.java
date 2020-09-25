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

package io.aiontechnology.mentorsuccess.api.model.inbound;

import io.aiontechnology.mentorsuccess.api.model.inbound.reference.BehaviorModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.reference.BehaviorModelHolder;
import io.aiontechnology.mentorsuccess.api.model.inbound.reference.InterestModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.reference.InterestModelHolder;
import io.aiontechnology.mentorsuccess.api.model.inbound.reference.LeadershipSkillModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.reference.LeadershipSkillModelHolder;
import io.aiontechnology.mentorsuccess.api.model.inbound.reference.LeadershipTraitModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.reference.LeadershipTraitModelHolder;
import io.aiontechnology.mentorsuccess.api.model.inbound.reference.PhonogramModelHolder;
import io.aiontechnology.mentorsuccess.entity.ResourceLocation;
import io.aiontechnology.mentorsuccess.util.EnumNamePattern;
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
 * A model object for books.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(setterPrefix = "with")
@ToString
public class BookModel extends ResourceModel<BookModel> implements InterestModelHolder, LeadershipSkillModelHolder,
        LeadershipTraitModelHolder, PhonogramModelHolder, BehaviorModelHolder {

    /** The book's id. */
    private final UUID id;

    /** The book's title. */
    @NotNull(message = "{book.title.notNull}")
    @Size(max = 100, message = "{book.title.size}")
    private final String title;

    /** The book's author. */
    @Size(max = 30, message = "{book.author.size}")
    private final String author;

    /** The book's grade level. */
    @NotNull(message = "{book.gradeLevel.notNull}")
    @Min(value = 1, message = "{book.gradeLevel.invalid}")
    @Max(value = 6, message = "{book.gradeLevel.invalid}")
    private final Integer gradeLevel;

    @EnumNamePattern(regexp = "ONLINE|OFFLINE|BOTH", message = "{book.location.invalid}")
    private final ResourceLocation location;

    /** The interests associated with the book. */
    private final Collection<InterestModel> interests;

    /** The leadership traits associated with the book. */
    private final Collection<LeadershipTraitModel> leadershipTraits;

    /** The leadership skills associated with the book. */
    private final Collection<LeadershipSkillModel> leadershipSkills;

    /** The phonograms associated with the book. */
    private final Collection<PhonogramModel> phonograms;

    /** The behaviors associated with the book. */
    private final Collection<BehaviorModel> behaviors;

}
