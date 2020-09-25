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

package io.aiontechnology.mentorsuccess.api.mapping.toentity.resource;

import io.aiontechnology.mentorsuccess.api.mapping.OneWayCollectionMapper;
import io.aiontechnology.mentorsuccess.api.mapping.OneWayUpdateMapper;
import io.aiontechnology.mentorsuccess.api.model.inbound.BookModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.PhonogramModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.reference.BehaviorModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.reference.InterestModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.reference.LeadershipSkillModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.reference.LeadershipTraitModel;
import io.aiontechnology.mentorsuccess.entity.Book;
import io.aiontechnology.mentorsuccess.entity.reference.Behavior;
import io.aiontechnology.mentorsuccess.entity.reference.Interest;
import io.aiontechnology.mentorsuccess.entity.reference.LeadershipSkill;
import io.aiontechnology.mentorsuccess.entity.reference.LeadershipTrait;
import io.aiontechnology.mentorsuccess.entity.reference.Phonogram;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

/**
 * Mapper that updates a {@link Book} from a {@link BookModel}.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
@Component
@RequiredArgsConstructor
public class BookModelToEntityUpdateMapper implements OneWayUpdateMapper<BookModel, Book> {

    /** Mapper between {@link BehaviorModel} and {@link Behavior}. */
    private final OneWayCollectionMapper<BehaviorModel, Behavior> behaviorModelToEntityMapper;

    /** Mapper between {@link InterestModel} and {@link Interest}. */
    private final OneWayCollectionMapper<InterestModel, Interest> interestModelToEntityMapper;

    /** Mapper between {@link LeadershipSkillModel} and {@link LeadershipSkill}. */
    private final OneWayCollectionMapper<LeadershipSkillModel, LeadershipSkill> leadershipSkillModelToEntityMapper;

    /** Mapper between {@link LeadershipTraitModel} and {@link LeadershipTrait}. */
    private final OneWayCollectionMapper<LeadershipTraitModel, LeadershipTrait> leadershipTraitModelToEntityMapper;

    /** Mapper between {@link PhonogramModel} and {@link Phonogram}. */
    private final OneWayCollectionMapper<PhonogramModel, Phonogram> phonogramModelToEntityMapper;

    /**
     * Update the given {@link Book} with the given {@link BookModel}.
     *
     * @param bookModel The {@link BookModel} to update from.
     * @param book The {@link Book} to update.
     * @return The updated {@link Book}.
     */
    @Override
    public Optional<Book> map(BookModel bookModel, Book book) {
        Objects.requireNonNull(book);
        return Optional.ofNullable(bookModel)
                .map(b -> {
                    book.setTitle(b.getTitle());
                    book.setAuthor(b.getAuthor());
                    book.setGradeLevel(b.getGradeLevel());
                    book.setLocation(b.getLocation());
                    book.setIsActive(true); // TODO Is this correct?
                    book.setBehaviors(behaviorModelToEntityMapper.map(b.getBehaviors()));
                    book.setInterests(interestModelToEntityMapper.map(b.getInterests()));
                    book.setLeadershipSkills(leadershipSkillModelToEntityMapper.map(b.getLeadershipSkills()));
                    book.setLeadershipTraits(leadershipTraitModelToEntityMapper.map(b.getLeadershipTraits()));
                    book.setPhonograms(phonogramModelToEntityMapper.map(b.getPhonograms()));
                    return book;
                });
    }

}
