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
import io.aiontechnology.mentorsuccess.entity.Game;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Mapper between {@link Game} and {@link GameModel}.
 *
 * @author <a href="mailto:whitney@aiontechnology.io">Whitney Hunter</a>
 * @since 1.0.0
 */
@Component
@RequiredArgsConstructor
public class GameMapper implements Mapper<Game, GameModel> {

    private final InterestMapper interestMapper;

    private final LeadershipSkillMapper leadershipSkillMapper;

    private final LeadershipTraitMapper leadershipTraitMapper;

    @Override
    public GameModel mapEntityToModel(Game game) {
        return GameModel.builder()
                .withId(game.getId())
                .withName(game.getName())
                .withDescription(game.getDescription())
                .withGradeLevel(game.getGradeLevel())
                .withLeadershipSkills(leadershipSkillMapper.mapLeadershipSkills(() -> game.getLeadershipSkills()))
                .withLeadershipTraits(leadershipTraitMapper.mapLeadershipTraits(() -> game.getLeadershipTraits()))
                .build();
    }

    @Override
    public Game mapModelToEntity(GameModel gameModel) {
        Game game = new Game();
        return mapModelToEntity(gameModel, game);
    }

    @Override
    public Game mapModelToEntity(GameModel gameModel, Game game) {
        game.setName(gameModel.getName());
        game.setDescription(gameModel.getDescription());
        game.setGradeLevel(gameModel.getGradeLevel());
        game.setLeadershipSkills(leadershipSkillMapper.mapLeadershipSkills(gameModel));
        game.setLeadershipTraits(leadershipTraitMapper.mapLeadershipTraits(gameModel));
        game.setIsActive(true);
        return game;
    }

}
