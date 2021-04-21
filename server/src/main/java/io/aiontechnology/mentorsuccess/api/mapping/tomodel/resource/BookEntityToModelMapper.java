/*
 * Copyright 2020-2021 Aion Technology LLC
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
import io.aiontechnology.mentorsuccess.entity.reference.Tag;
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

    private final OneWayCollectionMapper<Tag, String> tagEntityToModelMapper;

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
                        .withId(b.getId())
                        .withTitle(b.getTitle())
                        .withAuthor(b.getAuthor())
                        .withGradeLevel(b.getGradeLevel())
                        .withLocation(b.getLocation())
                        .withBehaviors(toBehaviorModelMapper.map(b.getBehaviors()))
                        .withInterests(interestEntityToModelMapper.map(b.getInterests()))
                        .withLeadershipSkills(leadershipSkillEntityToModelMapper.map(b.getLeadershipSkills()))
                        .withLeadershipTraits(leadershipTraitEntityToModelMapper.map(b.getLeadershipTraits()))
                        .withPhonograms(phonogramEntityToModelMapper.map(b.getPhonograms()))
                        .withTag(tagEntityToModelMapper.map(b.getTags()).stream().findFirst().orElse(null))
                        .build());
    }

}
