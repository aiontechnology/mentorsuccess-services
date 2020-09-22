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

package io.aiontechnology.mentorsuccess.api.mapping;

import io.aiontechnology.mentorsuccess.api.model.ActivityFocusModel;
import io.aiontechnology.mentorsuccess.api.model.GameModel;
import io.aiontechnology.mentorsuccess.api.model.LeadershipSkillModel;
import io.aiontechnology.mentorsuccess.entity.ActivityFocus;
import io.aiontechnology.mentorsuccess.entity.Game;
import io.aiontechnology.mentorsuccess.entity.LeadershipSkill;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static java.lang.Boolean.TRUE;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link GameMapper}.
 *
 * @author <a href="mailto:whitney@aiontechnology.io">Whitney Hunter</a>
 * @since 1.0.0
 */
public class GameMapperTest {

    private static final String NAME = "GAME";
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final Integer GRADE1 = 1;
    private static final Integer GRADE2 = 2;
    private static final Boolean IS_ACTIVE = TRUE;

    private static final String ACTIVITYFOCUS_NAME = "ACTIVITYFOCUS";
    private static final String LEADERSHIPSKILL_NAME = "LEADERSHIPSKILL";

    @Test
    void testMapEntityToModel() throws Exception {
        // setup the fixture
        UUID ID = UUID.randomUUID();

        UUID ACTIVITYFOCUS_ID = UUID.randomUUID();
        ActivityFocus activityFocus = new ActivityFocus();
        activityFocus.setId(ACTIVITYFOCUS_ID);
        activityFocus.setName(ACTIVITYFOCUS_NAME);

        UUID LEADERSHIPSKILL_ID = UUID.randomUUID();
        LeadershipSkill leadershipSkill = new LeadershipSkill();
        leadershipSkill.setId(LEADERSHIPSKILL_ID);
        leadershipSkill.setName(LEADERSHIPSKILL_NAME);

        Game game = new Game();
        game.setId(ID);
        game.setName(NAME);
        game.setDescription(DESCRIPTION);
        game.setGrade1(GRADE1);
        game.setIsActive(IS_ACTIVE);
        game.setActivityFocuses(Collections.singleton(activityFocus));
        game.setLeadershipSkills(Collections.singleton(leadershipSkill));

        ActivityFocusMapper activityFocusMapper = new ActivityFocusMapper(null);
        LeadershipSkillMapper leadershipSkillMapper = new LeadershipSkillMapper(null);
        GameMapper gameMapper = new GameMapper(activityFocusMapper, leadershipSkillMapper);

        // execute the SUT
        GameModel result = gameMapper.mapEntityToModel(game);

        // validation
        assertThat(result.getName()).isEqualTo(NAME);
        assertThat(result.getDescription()).isEqualTo(DESCRIPTION);
        assertThat(result.getGrade1()).isEqualTo(GRADE1);
        assertThat(result.getActivityFocuses()).containsExactly(activityFocusMapper.mapEntityToModel(activityFocus));
        assertThat(result.getLeadershipSkills()).containsExactly(leadershipSkillMapper.mapEntityToModel(leadershipSkill));
    }

    @Test
    void testMapModelToEntity_newEntity() throws Exception {

        // setup the fixture
        ActivityFocusModel activityFocusModel = ActivityFocusModel.builder().withName(ACTIVITYFOCUS_NAME).build();
        LeadershipSkillModel leadershipSkillModel = LeadershipSkillModel.builder().withName(LEADERSHIPSKILL_NAME).build();
        GameModel gameModel = GameModel.builder()
                .withName(NAME)
                .withDescription(DESCRIPTION)
                .withGrade1(GRADE1)
                .withGrade2(GRADE2)
                .withActivityFocuses(Arrays.asList(activityFocusModel))
                .withLeadershipSkills(Arrays.asList(leadershipSkillModel))
                .build();

        ActivityFocus activityFocus = new ActivityFocus();
        activityFocus.setName(ACTIVITYFOCUS_NAME);
        ActivityFocusMapper activityFocusMapper = new ActivityFocusMapper(name -> Optional.of(activityFocus));

        LeadershipSkill leadershipSkill = new LeadershipSkill();
        leadershipSkill.setName(LEADERSHIPSKILL_NAME);
        LeadershipSkillMapper leadershipSkillMapper = new LeadershipSkillMapper(name -> Optional.of(leadershipSkill));

        GameMapper gameMapper = new GameMapper(activityFocusMapper, leadershipSkillMapper);

        // execute the SUT
        Game result = gameMapper.mapModelToEntity(gameModel);

        // validation
        assertThat(result.getName()).isEqualTo(NAME);
        assertThat(result.getDescription()).isEqualTo(DESCRIPTION);
        assertThat(result.getGrade1()).isEqualTo(GRADE1);
        assertThat(result.getGrade2()).isEqualTo(GRADE2);
        assertThat(result.getActivityFocuses()).containsExactly(activityFocus);
        assertThat(result.getLeadershipSkills()).containsExactly(leadershipSkill);
    }

    @Test
    void testMapModelToEntity_providedEntity() throws Exception {

        // setup the fixture
        ActivityFocusModel activityFocusModel = ActivityFocusModel.builder().withName(ACTIVITYFOCUS_NAME).build();
        LeadershipSkillModel leadershipSkillModel = LeadershipSkillModel.builder().withName(LEADERSHIPSKILL_NAME).build();
        GameModel gameModel = GameModel.builder()
                .withName(NAME)
                .withDescription(DESCRIPTION)
                .withGrade1(GRADE1)
                .withGrade2(GRADE2)
                .withActivityFocuses(Arrays.asList(activityFocusModel))
                .withLeadershipSkills(Arrays.asList(leadershipSkillModel))
                .build();

        ActivityFocus activityFocus = new ActivityFocus();
        activityFocus.setName(ACTIVITYFOCUS_NAME);
        ActivityFocusMapper activityFocusMapper = new ActivityFocusMapper(name -> Optional.of(activityFocus));

        LeadershipSkill leadershipSkill = new LeadershipSkill();
        leadershipSkill.setName(LEADERSHIPSKILL_NAME);
        LeadershipSkillMapper leadershipSkillMapper = new LeadershipSkillMapper(name -> Optional.of(leadershipSkill));

        GameMapper gameMapper = new GameMapper(activityFocusMapper, leadershipSkillMapper);

        // execute the SUT
        Game providedGame = new Game();
        Game result = gameMapper.mapModelToEntity(gameModel, providedGame);

        // validation
        assertThat(result).isSameAs(providedGame);
        assertThat(result.getName()).isEqualTo(NAME);
        assertThat(result.getDescription()).isEqualTo(DESCRIPTION);
        assertThat(result.getGrade1()).isEqualTo(GRADE1);
        assertThat(result.getGrade2()).isEqualTo(GRADE2);
        assertThat(result.getActivityFocuses()).containsExactly(activityFocus);
        assertThat(result.getLeadershipSkills()).containsExactly(leadershipSkill);
    }

}
