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

import io.aiontechnology.atlas.mapping.OneWayCollectionMapper;
import io.aiontechnology.atlas.mapping.OneWayMapper;
import io.aiontechnology.mentorsuccess.entity.Book;
import io.aiontechnology.mentorsuccess.entity.reference.Behavior;
import io.aiontechnology.mentorsuccess.entity.reference.Interest;
import io.aiontechnology.mentorsuccess.entity.reference.LeadershipSkill;
import io.aiontechnology.mentorsuccess.entity.reference.LeadershipTrait;
import io.aiontechnology.mentorsuccess.entity.reference.Phonogram;
import io.aiontechnology.mentorsuccess.model.inbound.InboundBook;
import io.aiontechnology.mentorsuccess.model.outbound.OutboundBook;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Mapper that converts a {@link Book} to a {@link OutboundBook}.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
@Component
@RequiredArgsConstructor
public class BookEntityToModelMapper implements OneWayMapper<Book, OutboundBook> {

    /** Mapper between {@link Behavior} and a behavior string. */
    private final OneWayCollectionMapper<Behavior, String> toBehaviorModelMapper;

    /** Mapper between {@link Interest} and an interest string. */
    private final OneWayCollectionMapper<Interest, String> interestEntityToModelMapper;

    /** Mapper between {@link LeadershipSkill} and a leadership skill string. */
    private final OneWayCollectionMapper<LeadershipSkill, String> leadershipSkillEntityToModelMapper;

    /** Mapper between {@link LeadershipTrait} and leadership trait string. */
    private final OneWayCollectionMapper<LeadershipTrait, String> leadershipTraitEntityToModelMapper;

    /** Mapper between {@link Phonogram} and a phonogram string. */
    private final OneWayCollectionMapper<Phonogram, String> phonogramEntityToModelMapper;

    /**
     * Map the given {@link Book} to a {@link InboundBook}.
     *
     * @param book The {@link Book} to map.
     * @return The mapped {@link InboundBook}.
     */
    @Override
    public Optional<OutboundBook> map(Book book) {
        return Optional.ofNullable(book)
                .map(b -> OutboundBook.builder()
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
