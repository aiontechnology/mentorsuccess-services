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

import io.aiontechnology.mentorsuccess.api.model.GameModel;
import io.aiontechnology.mentorsuccess.api.model.InterestModel;
import io.aiontechnology.mentorsuccess.api.model.LeadershipSkillModel;
import io.aiontechnology.mentorsuccess.api.model.LeadershipTraitModel;
import io.aiontechnology.mentorsuccess.entity.Game;
import io.aiontechnology.mentorsuccess.entity.Interest;
import io.aiontechnology.mentorsuccess.entity.LeadershipSkill;
import io.aiontechnology.mentorsuccess.entity.LeadershipTrait;
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
    private static final Integer GRADE_LEVEL = 1;
    private static final Boolean IS_ACTIVE = TRUE;

    private static final String INTEREST_NAME = "INTEREST";
    private static final String LEADERSHIPSKILL_NAME = "LEADERSHIPSKILL";
    private static final String LEADERSHIPTRAIT_NAME = "LEADERSHIPTRAIT";

    @Test
    void testMapEntityToModel() throws Exception {
        // setup the fixture
        UUID ID = UUID.randomUUID();

        UUID LEADERSHIPSKILL_ID = UUID.randomUUID();
        LeadershipSkill leadershipSkill = new LeadershipSkill();
        leadershipSkill.setId(LEADERSHIPSKILL_ID);
        leadershipSkill.setName(LEADERSHIPSKILL_NAME);

        UUID LEADERSHIPTRAIT_ID = UUID.randomUUID();
        LeadershipTrait leadershipTrait = new LeadershipTrait();
        leadershipTrait.setId(LEADERSHIPTRAIT_ID);
        leadershipTrait.setName(LEADERSHIPTRAIT_NAME);

        Game game = new Game();
        game.setId(ID);
        game.setName(NAME);
        game.setDescription(DESCRIPTION);
        game.setGradeLevel(GRADE_LEVEL);
        game.setIsActive(IS_ACTIVE);
        game.setLeadershipSkills(Collections.singleton(leadershipSkill));
        game.setLeadershipTraits(Collections.singleton(leadershipTrait));

        InterestMapper interestMapper = new InterestMapper(null);
        LeadershipSkillMapper leadershipSkillMapper = new LeadershipSkillMapper(null);
        LeadershipTraitMapper leadershipTraitMapper = new LeadershipTraitMapper(null);
        GameMapper gameMapper = new GameMapper(interestMapper, leadershipSkillMapper, leadershipTraitMapper);

        // execute the SUT
        GameModel result = gameMapper.mapEntityToModel(game);

        // validation
        assertThat(result.getName()).isEqualTo(NAME);
        assertThat(result.getDescription()).isEqualTo(DESCRIPTION);
        assertThat(result.getGradeLevel()).isEqualTo(GRADE_LEVEL);
        assertThat(result.getLeadershipSkills()).containsExactly(leadershipSkillMapper.mapEntityToModel(leadershipSkill));
        assertThat(result.getLeadershipTraits()).containsExactly(leadershipTraitMapper.mapEntityToModel(leadershipTrait));
    }

    @Test
    void testMapModelToEntity_newEntity() throws Exception {

        // setup the fixture
        InterestModel interestModel = InterestModel.builder().withName(INTEREST_NAME).build();
        LeadershipSkillModel leadershipSkillModel = LeadershipSkillModel.builder().withName(LEADERSHIPSKILL_NAME).build();
        LeadershipTraitModel leadershipTraitModel = LeadershipTraitModel.builder().withName(LEADERSHIPTRAIT_NAME).build();
        GameModel gameModel = GameModel.builder()
                .withName(NAME)
                .withDescription(DESCRIPTION)
                .withGradeLevel(GRADE_LEVEL)
                .withLeadershipSkills(Arrays.asList(leadershipSkillModel))
                .withLeadershipTraits(Arrays.asList(leadershipTraitModel))
                .build();

        Interest interest = new Interest();
        interest.setName(INTEREST_NAME);
        InterestMapper interestMapper = new InterestMapper(name -> Optional.of(interest));
        LeadershipSkill leadershipSkill = new LeadershipSkill();
        leadershipSkill.setName(LEADERSHIPSKILL_NAME);
        LeadershipSkillMapper leadershipSkillMapper = new LeadershipSkillMapper(name -> Optional.of(leadershipSkill));
        LeadershipTrait leadershipTrait = new LeadershipTrait();
        leadershipTrait.setName(LEADERSHIPTRAIT_NAME);
        LeadershipTraitMapper leadershipTraitMapper = new LeadershipTraitMapper(name -> Optional.of(leadershipTrait));

        GameMapper gameMapper = new GameMapper(interestMapper, leadershipSkillMapper, leadershipTraitMapper);

        // execute the SUT
        Game result = gameMapper.mapModelToEntity(gameModel);

        // validation
        assertThat(result.getName()).isEqualTo(NAME);
        assertThat(result.getDescription()).isEqualTo(DESCRIPTION);
        assertThat(result.getGradeLevel()).isEqualTo(GRADE_LEVEL);
        assertThat(result.getLeadershipSkills()).containsExactly(leadershipSkill);
        assertThat(result.getLeadershipTraits()).containsExactly(leadershipTrait);
    }

    @Test
    void testMapModelToEntity_providedEntity() throws Exception {

        // setup the fixture
        LeadershipSkillModel leadershipSkillModel = LeadershipSkillModel.builder().withName(LEADERSHIPSKILL_NAME).build();
        LeadershipTraitModel leadershipTraitModel = LeadershipTraitModel.builder().withName(LEADERSHIPTRAIT_NAME).build();
        GameModel gameModel = GameModel.builder()
                .withName(NAME)
                .withDescription(DESCRIPTION)
                .withGradeLevel(GRADE_LEVEL)
                .withLeadershipSkills(Arrays.asList(leadershipSkillModel))
                .withLeadershipTraits(Arrays.asList(leadershipTraitModel))
                .build();

        Interest interest = new Interest();
        interest.setName(INTEREST_NAME);
        InterestMapper interestMapper = new InterestMapper(name -> Optional.of(interest));
        LeadershipSkill leadershipSkill = new LeadershipSkill();
        leadershipSkill.setName(LEADERSHIPSKILL_NAME);
        LeadershipSkillMapper leadershipSkillMapper = new LeadershipSkillMapper(name -> Optional.of(leadershipSkill));
        LeadershipTrait leadershipTrait = new LeadershipTrait();
        leadershipTrait.setName(LEADERSHIPTRAIT_NAME);
        LeadershipTraitMapper leadershipTraitMapper = new LeadershipTraitMapper(name -> Optional.of(leadershipTrait));

        GameMapper gameMapper = new GameMapper(interestMapper, leadershipSkillMapper, leadershipTraitMapper);

        // execute the SUT
        Game providedGame = new Game();
        Game result = gameMapper.mapModelToEntity(gameModel, providedGame);

        // validation
        assertThat(result).isSameAs(providedGame);
        assertThat(result.getName()).isEqualTo(NAME);
        assertThat(result.getDescription()).isEqualTo(DESCRIPTION);
        assertThat(result.getGradeLevel()).isEqualTo(GRADE_LEVEL);
        assertThat(result.getLeadershipSkills()).containsExactly(leadershipSkill);
        assertThat(result.getLeadershipTraits()).containsExactly(leadershipTrait);
    }

}
