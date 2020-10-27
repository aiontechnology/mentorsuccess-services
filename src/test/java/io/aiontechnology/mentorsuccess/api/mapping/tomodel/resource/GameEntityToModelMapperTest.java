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
import io.aiontechnology.mentorsuccess.api.model.inbound.ActivityFocusModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.GameModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.reference.LeadershipSkillModel;
import io.aiontechnology.mentorsuccess.entity.ActivityFocus;
import io.aiontechnology.mentorsuccess.entity.Game;
import io.aiontechnology.mentorsuccess.entity.ResourceLocation;
import io.aiontechnology.mentorsuccess.entity.reference.LeadershipSkill;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import static io.aiontechnology.mentorsuccess.entity.ResourceLocation.ONLINE;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link GameEntityToModelMapper}.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
public class GameEntityToModelMapperTest {

    @Test
    void testMapping() throws Exception {
        // setup the fixture
        UUID id = UUID.randomUUID();
        String name = "NAME";
        Integer gradeLevel1 = 1;
        Integer gradeLevel2 = 2;
        ResourceLocation location = ONLINE;
        Collection<ActivityFocus> activityFocuses = Arrays.asList(new ActivityFocus());
        Collection<LeadershipSkill> leadershipSkills = Arrays.asList(new LeadershipSkill());

        Game game = new Game();
        game.setId(id);
        game.setName(name);
        game.setGrade1(gradeLevel1);
        game.setGrade2(gradeLevel2);
        game.setLocation(location);
        game.setActivityFocuses(activityFocuses);
        game.setLeadershipSkills(leadershipSkills);

        OneWayCollectionMapper<ActivityFocus, ActivityFocusModel> activityFocusEntityToModelMapper =
                (b -> Arrays.asList(ActivityFocusModel.builder().build()));
        OneWayCollectionMapper<LeadershipSkill, LeadershipSkillModel> leadershipSkillEntityToModelMapper =
                (l -> Arrays.asList(LeadershipSkillModel.builder().build()));

        GameEntityToModelMapper gameEntityToModelMapper = new GameEntityToModelMapper(activityFocusEntityToModelMapper,
                leadershipSkillEntityToModelMapper);

        // execute the SUT
        Optional<GameModel> result = gameEntityToModelMapper.map(game);

        // validation
        assertThat(result).isNotEmpty();
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
        OneWayCollectionMapper<ActivityFocus, ActivityFocusModel> activityFocusEntityToModelMapper =
                (b -> Arrays.asList(ActivityFocusModel.builder().build()));
        OneWayCollectionMapper<LeadershipSkill, LeadershipSkillModel> leadershipSkillEntityToModelMapper =
                (l -> Arrays.asList(LeadershipSkillModel.builder().build()));

        GameEntityToModelMapper gameEntityToModelMapper = new GameEntityToModelMapper(activityFocusEntityToModelMapper,
                leadershipSkillEntityToModelMapper);

        // execute the SUT
        Optional<GameModel> result = gameEntityToModelMapper.map(null);

        // validation
        assertThat(result).isEmpty();
    }

}
