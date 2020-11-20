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
import io.aiontechnology.mentorsuccess.entity.Book;
import io.aiontechnology.mentorsuccess.entity.reference.Behavior;
import io.aiontechnology.mentorsuccess.entity.reference.Interest;
import io.aiontechnology.mentorsuccess.entity.reference.LeadershipSkill;
import io.aiontechnology.mentorsuccess.entity.reference.LeadershipTrait;
import io.aiontechnology.mentorsuccess.entity.reference.Phonogram;
import io.aiontechnology.mentorsuccess.model.enumeration.ResourceLocation;
import io.aiontechnology.mentorsuccess.model.outbound.OutboundBook;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import static io.aiontechnology.mentorsuccess.model.enumeration.ResourceLocation.ONLINE;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link BookEntityToModelMapper}.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
public class BookEntityToModelMapperTest {

    @Test
    void testMapping() throws Exception {
        // setup the fixture
        UUID id = UUID.randomUUID();
        String title = "TITLE";
        String author = "AUTHOR";
        Integer gradeLevel = 1;
        ResourceLocation location = ONLINE;
        Collection<Behavior> behaviors = Arrays.asList(new Behavior());
        Collection<Interest> interests = Arrays.asList(new Interest());
        Collection<LeadershipSkill> leadershipSkills = Arrays.asList(new LeadershipSkill());
        Collection<LeadershipTrait> leadershipTraits = Arrays.asList(new LeadershipTrait());
        Collection<Phonogram> phonograms = Arrays.asList(new Phonogram());

        Book book = new Book();
        book.setId(id);
        book.setTitle(title);
        book.setAuthor(author);
        book.setGradeLevel(gradeLevel);
        book.setLocation(location);
        book.setBehaviors(behaviors);
        book.setInterests(interests);
        book.setLeadershipSkills(leadershipSkills);
        book.setLeadershipTraits(leadershipTraits);
        book.setPhonograms(phonograms);

        OneWayCollectionMapper<Behavior, String> behaviorEntityToModelMapper =
                (b -> Arrays.asList("BEHAVIOR"));
        OneWayCollectionMapper<Interest, String> interestEntityToModelMapper =
                (i -> Arrays.asList("INTEREST"));
        OneWayCollectionMapper<LeadershipSkill, String> leadershipSkillEntityToModelMapper =
                (l -> Arrays.asList("LEADERSHIP_SKILL"));
        LeadershipTrait leadershipTrait = new LeadershipTrait();
        OneWayCollectionMapper<LeadershipTrait, String> leadershipTraitEntityToModelMapper =
                (l -> Arrays.asList("LEADERSHIP_TRAIT"));
        OneWayCollectionMapper<Phonogram, String> phonogramEntityToModelMapper =
                (p -> Arrays.asList("PHONOGRAM"));

        BookEntityToModelMapper bookEntityToModelMapper = new BookEntityToModelMapper(behaviorEntityToModelMapper,
                interestEntityToModelMapper, leadershipSkillEntityToModelMapper, leadershipTraitEntityToModelMapper,
                phonogramEntityToModelMapper);

        // execute the SUT
        Optional<OutboundBook> result = bookEntityToModelMapper.map(book);

        // validation
        assertThat(result).isNotEmpty();
        assertThat(result.get().getTitle()).isEqualTo(title);
        assertThat(result.get().getAuthor()).isEqualTo(author);
        assertThat(result.get().getGradeLevel()).isEqualTo(gradeLevel);
        assertThat(result.get().getLocation()).isEqualTo(location);
        assertThat(result.get().getBehaviors().size()).isEqualTo(1);
        assertThat(result.get().getInterests().size()).isEqualTo(1);
        assertThat(result.get().getLeadershipSkills().size()).isEqualTo(1);
        assertThat(result.get().getLeadershipTraits().size()).isEqualTo(1);
        assertThat(result.get().getPhonograms().size()).isEqualTo(1);
    }

    @Test
    void testNull() throws Exception {
        // setup the fixture
        OneWayCollectionMapper<Behavior, String> behaviorEntityToModelMapper =
                (b -> Arrays.asList("BEHAVIOR"));
        OneWayCollectionMapper<Interest, String> interestEntityToModelMapper =
                (i -> Arrays.asList("INTEREST"));
        OneWayCollectionMapper<LeadershipSkill, String> leadershipSkillEntityToModelMapper =
                (l -> Arrays.asList("LEADERSHIP_SKILL"));
        OneWayCollectionMapper<LeadershipTrait, String> leadershipTraitEntityToModelMapper =
                (l -> Arrays.asList("LEADERSHIP_TRAIT"));
        OneWayCollectionMapper<Phonogram, String> phonogramEntityToModelMapper =
                (p -> Arrays.asList("PHONOGRAM"));

        BookEntityToModelMapper bookEntityToModelMapper = new BookEntityToModelMapper(behaviorEntityToModelMapper,
                interestEntityToModelMapper, leadershipSkillEntityToModelMapper, leadershipTraitEntityToModelMapper,
                phonogramEntityToModelMapper);

        // execute the SUT
        Optional<OutboundBook> result = bookEntityToModelMapper.map(null);

        // validation
        assertThat(result).isEmpty();
    }

}
