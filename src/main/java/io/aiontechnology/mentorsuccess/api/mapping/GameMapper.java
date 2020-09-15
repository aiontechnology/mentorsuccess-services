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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Mapper between {@link Game} and {@link GameModel}.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@Component
@RequiredArgsConstructor
public class GameMapper implements Mapper<Game, GameModel> {

    /** Mapper between {@link ActivityFocus} and {@link ActivityFocusModel} */
    private final ActivityFocusMapper activityFocusMapper;

    /** Mapper between {@link LeadershipSkill} and {@link LeadershipSkillModel} */
    private final LeadershipSkillMapper leadershipSkillMapper;

    /**
     * Map a {@link Game} to a new {@link GameModel}.
     *
     * @param game The {@link Game} to map.
     * @return The resulting {@link GameModel}.
     */
    @Override
    public GameModel mapEntityToModel(Game game) {
        return GameModel.builder()
                .withId(game.getId())
                .withName(game.getName())
                .withDescription(game.getDescription())
                .withGradeLevel(game.getGradeLevel())
                .withLocation(game.getLocation())
                .withActivityFocuses(activityFocusMapper.mapActivityFocuses(() -> game.getActivityFocuses()))
                .withLeadershipSkills(leadershipSkillMapper.mapLeadershipSkills(() -> game.getLeadershipSkills()))
                .build();
    }

    /**
     * Map a {@link GameModel} to a new {@link Game}.
     *
     * @param gameModel The {@link GameModel} to map.
     * @return The resulting {@link Game}.
     */
    @Override
    public Game mapModelToEntity(GameModel gameModel) {
        Game game = new Game();
        return mapModelToEntity(gameModel, game);
    }

    /**
     * Map a {@link GameModel} to the given {@link Game}.
     *
     * @param gameModel The {@link GameModel} to map.
     * @param game The {@link Game} to map to.
     * @return The resulting {@link Game}.
     */
    @Override
    public Game mapModelToEntity(GameModel gameModel, Game game) {
        game.setName(gameModel.getName());
        game.setDescription(gameModel.getDescription());
        game.setGradeLevel(gameModel.getGradeLevel());
        game.setLocation(gameModel.getLocation());
        game.setActivityFocuses(activityFocusMapper.mapActivityFocuses(gameModel));
        game.setLeadershipSkills(leadershipSkillMapper.mapLeadershipSkills(gameModel));
        game.setIsActive(true);
        return game;
    }

}
