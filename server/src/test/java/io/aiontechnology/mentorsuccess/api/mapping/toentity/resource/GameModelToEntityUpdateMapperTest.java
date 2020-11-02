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
import io.aiontechnology.mentorsuccess.entity.ActivityFocus;
import io.aiontechnology.mentorsuccess.entity.Game;
import io.aiontechnology.mentorsuccess.entity.reference.LeadershipSkill;
import io.aiontechnology.mentorsuccess.model.enumeration.ResourceLocation;
import io.aiontechnology.mentorsuccess.model.inbound.InboundActivityFocus;
import io.aiontechnology.mentorsuccess.model.inbound.InboundGame;
import io.aiontechnology.mentorsuccess.model.inbound.reference.InboundLeadershipSkill;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import static io.aiontechnology.mentorsuccess.model.enumeration.ResourceLocation.ONLINE;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link GameModelToEntityUpdateMapper}.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
public class GameModelToEntityUpdateMapperTest {

    @Test
    void testMapping() throws Exception {
        // setup the fixture
        UUID id = UUID.randomUUID();
        String name = "NAME";
        Integer gradeLevel1 = 1;
        Integer gradeLevel2 = 2;
        ResourceLocation location = ONLINE;
        Collection<InboundActivityFocus> inboundActivityFoci = Arrays.asList(InboundActivityFocus.builder().build());
        Collection<InboundLeadershipSkill> inboundLeadershipSkills = Arrays.asList(InboundLeadershipSkill.builder().build());

        InboundGame inboundGame = InboundGame.builder()
                .withId(id)
                .withName(name)
                .withGrade1(gradeLevel1)
                .withGrade2(gradeLevel2)
                .withLocation(location)
                .withActivityFocuses(inboundActivityFoci)
                .withLeadershipSkills(inboundLeadershipSkills)
                .build();

        Game game = new Game();

        OneWayCollectionMapper<InboundActivityFocus, ActivityFocus> activityFocusModelToEntityMapper =
                (b -> Arrays.asList(new ActivityFocus()));
        OneWayCollectionMapper<InboundLeadershipSkill, LeadershipSkill> leadershipSkillModelToEntityMapper =
                (l -> Arrays.asList(new LeadershipSkill()));

        GameModelToEntityUpdateMapper gameModelToEntityUpdateMapper = new GameModelToEntityUpdateMapper(
                activityFocusModelToEntityMapper, leadershipSkillModelToEntityMapper);

        // execute the SUT
        Optional<Game> result = gameModelToEntityUpdateMapper.map(inboundGame, game);

        // validation
        assertThat(result).isNotEmpty();
        assertThat(result.get()).isSameAs(game);
        assertThat(result.get().getName()).isEqualTo(name);
        assertThat(result.get().getGrade1()).isEqualTo(gradeLevel1);
        assertThat(result.get().getGrade2()).isEqualTo(gradeLevel2);
        assertThat(result.get().getLocation()).isEqualTo(location);
        assertThat(result.get().getActivityFocuses().size()).isEqualTo(1);
        assertThat(result.get().getLeadershipSkills().size()).isEqualTo(1);
    }

    @Test
    void testNull() throws Exception {
        // setup the fixture
        Game game = new Game();

        OneWayCollectionMapper<InboundActivityFocus, ActivityFocus> activityFocusModelToEntityMapper =
                (b -> Arrays.asList(new ActivityFocus()));
        OneWayCollectionMapper<InboundLeadershipSkill, LeadershipSkill> leadershipSkillModelToEntityMapper =
                (l -> Arrays.asList(new LeadershipSkill()));

        GameModelToEntityUpdateMapper gameModelToEntityUpdateMapper = new GameModelToEntityUpdateMapper(
                activityFocusModelToEntityMapper, leadershipSkillModelToEntityMapper);

        // execute the SUT
        Optional<Game> result = gameModelToEntityUpdateMapper.map(null, game);

        // validation
        assertThat(result).isEmpty();
    }

}
