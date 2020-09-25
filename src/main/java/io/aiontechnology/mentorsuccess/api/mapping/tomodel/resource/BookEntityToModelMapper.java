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

package io.aiontechnology.mentorsuccess.api.mapping.tomodel.resource;

import io.aiontechnology.mentorsuccess.api.mapping.OneWayCollectionMapper;
import io.aiontechnology.mentorsuccess.api.mapping.OneWayMapper;
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

import java.util.Optional;

/**
 * Mapper that converts a {@link Book} to a {@link BookModel}.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
@Component
@RequiredArgsConstructor
public class BookEntityToModelMapper implements OneWayMapper<Book, BookModel> {

    /** Mapper between {@link Behavior} and {@link BehaviorModel}. */
    private final OneWayCollectionMapper<Behavior, BehaviorModel> toBehaviorModelMapper;

    /** Mapper between {@link Interest} and {@link InterestModel}. */
    private final OneWayCollectionMapper<Interest, InterestModel> interestEntityToModelMapper;

    /** Mapper between {@link LeadershipSkill} and {@link LeadershipSkillModel}. */
    private final OneWayCollectionMapper<LeadershipSkill, LeadershipSkillModel> leadershipSkillEntityToModelMapper;

    /** Mapper between {@link LeadershipTrait} and {@link LeadershipTraitModel}. */
    private final OneWayCollectionMapper<LeadershipTrait, LeadershipTraitModel> leadershipTraitEntityToModelMapper;

    /** Mapper between {@link Phonogram} and {@link PhonogramModel}. */
    private final OneWayCollectionMapper<Phonogram, PhonogramModel> phonogramEntityToModelMapper;

    /**
     * Map the given {@link Book} to a {@link BookModel}.
     *
     * @param book The {@link Book} to map.
     * @return The mapped {@link BookModel}.
     */
    @Override
    public Optional<BookModel> map(Book book) {
        return Optional.ofNullable(book)
                .map(b -> BookModel.builder()
                        .withId(book.getId())
                        .withTitle(book.getTitle())
                        .withAuthor(book.getAuthor())
                        .withGradeLevel(book.getGradeLevel())
                        .withLocation(book.getLocation())
                        .withBehaviors(toBehaviorModelMapper.map(book.getBehaviors()))
                        .withInterests(interestEntityToModelMapper.map(book.getInterests()))
                        .withLeadershipSkills(leadershipSkillEntityToModelMapper.map(book.getLeadershipSkills()))
                        .withLeadershipTraits(leadershipTraitEntityToModelMapper.map(book.getLeadershipTraits()))
                        .withPhonograms(phonogramEntityToModelMapper.map(book.getPhonograms()))
                        .build());
    }

}
