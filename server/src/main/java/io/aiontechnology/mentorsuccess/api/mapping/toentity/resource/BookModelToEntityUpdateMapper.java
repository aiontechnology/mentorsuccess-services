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

import io.aiontechnology.atlas.mapping.OneWayCollectionMapper;
import io.aiontechnology.atlas.mapping.OneWayUpdateMapper;
import io.aiontechnology.mentorsuccess.entity.Book;
import io.aiontechnology.mentorsuccess.entity.reference.Behavior;
import io.aiontechnology.mentorsuccess.entity.reference.Interest;
import io.aiontechnology.mentorsuccess.entity.reference.LeadershipSkill;
import io.aiontechnology.mentorsuccess.entity.reference.LeadershipTrait;
import io.aiontechnology.mentorsuccess.entity.reference.Phonogram;
import io.aiontechnology.mentorsuccess.model.inbound.InboundBook;
import io.aiontechnology.mentorsuccess.model.inbound.reference.InboundBehavior;
import io.aiontechnology.mentorsuccess.model.inbound.reference.InboundInterest;
import io.aiontechnology.mentorsuccess.model.inbound.reference.InboundLeadershipSkill;
import io.aiontechnology.mentorsuccess.model.inbound.reference.InboundLeadershipTrait;
import io.aiontechnology.mentorsuccess.model.inbound.reference.InboundPhonogramModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

/**
 * Mapper that updates a {@link Book} from a {@link InboundBook}.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
@Component
@RequiredArgsConstructor
public class BookModelToEntityUpdateMapper implements OneWayUpdateMapper<InboundBook, Book> {

    /** Mapper between {@link InboundBehavior} and {@link Behavior}. */
    private final OneWayCollectionMapper<InboundBehavior, Behavior> behaviorModelToEntityMapper;

    /** Mapper between {@link InboundInterest} and {@link Interest}. */
    private final OneWayCollectionMapper<InboundInterest, Interest> interestModelToEntityMapper;

    /** Mapper between {@link InboundLeadershipSkill} and {@link LeadershipSkill}. */
    private final OneWayCollectionMapper<InboundLeadershipSkill, LeadershipSkill> leadershipSkillModelToEntityMapper;

    /** Mapper between {@link InboundLeadershipTrait} and {@link LeadershipTrait}. */
    private final OneWayCollectionMapper<InboundLeadershipTrait, LeadershipTrait> leadershipTraitModelToEntityMapper;

    /** Mapper between {@link InboundPhonogramModel} and {@link Phonogram}. */
    private final OneWayCollectionMapper<InboundPhonogramModel, Phonogram> phonogramModelToEntityMapper;

    /**
     * Update the given {@link Book} with the given {@link InboundBook}.
     *
     * @param inboundBook The {@link InboundBook} to update from.
     * @param book The {@link Book} to update.
     * @return The updated {@link Book}.
     */
    @Override
    public Optional<Book> map(InboundBook inboundBook, Book book) {
        Objects.requireNonNull(book);
        return Optional.ofNullable(inboundBook)
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
