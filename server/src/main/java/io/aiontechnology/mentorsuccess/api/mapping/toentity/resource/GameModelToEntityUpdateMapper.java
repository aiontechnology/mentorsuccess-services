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
import io.aiontechnology.atlas.mapping.OneWayUpdateMapper;
import io.aiontechnology.mentorsuccess.entity.ActivityFocus;
import io.aiontechnology.mentorsuccess.entity.Game;
import io.aiontechnology.mentorsuccess.entity.reference.LeadershipSkill;
import io.aiontechnology.mentorsuccess.model.inbound.InboundActivityFocus;
import io.aiontechnology.mentorsuccess.model.inbound.InboundGame;
import io.aiontechnology.mentorsuccess.model.inbound.reference.InboundLeadershipSkill;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

/**
 * Mapper that updates a {@link Game} from a {@link InboundGame}.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
@Component
@RequiredArgsConstructor
public class GameModelToEntityUpdateMapper implements OneWayUpdateMapper<InboundGame, Game> {

    /** Mapper between {@link InboundActivityFocus} and {@link ActivityFocus}. */
    private final OneWayCollectionMapper<InboundActivityFocus, ActivityFocus> activityFocusModelToEntityMapper;

    /** Mapper between {@link InboundLeadershipSkill} and {@link LeadershipSkill}. */
    private final OneWayCollectionMapper<InboundLeadershipSkill, LeadershipSkill> leadershipSkillModelToEntityMapper;

    /**
     * Update the given {@link Game} with the given {@link InboundGame}.
     *
     * @param inboundGame The {@link InboundGame} to update from.
     * @param game The {@link Game} to update.
     * @return The updated {@link Game}.
     */
    @Override
    public Optional<Game> map(InboundGame inboundGame, Game game) {
        Objects.requireNonNull(game);
        return Optional.ofNullable(inboundGame)
                .map(g -> {
                    game.setName(g.getName());
                    game.setGrade1(g.getGrade1());
                    game.setGrade2(g.getGrade2());
                    game.setLocation(g.getLocation());
                    game.setIsActive(true); // TODO Is this correct?
                    game.setActivityFocuses(activityFocusModelToEntityMapper.map(g.getActivityFocuses()));
                    game.setLeadershipSkills(leadershipSkillModelToEntityMapper.map(g.getLeadershipSkills()));
                    return game;
                });
    }

}
