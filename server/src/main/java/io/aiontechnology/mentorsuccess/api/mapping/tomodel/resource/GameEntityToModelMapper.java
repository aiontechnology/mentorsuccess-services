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
import io.aiontechnology.mentorsuccess.entity.ActivityFocus;
import io.aiontechnology.mentorsuccess.entity.Game;
import io.aiontechnology.mentorsuccess.entity.reference.LeadershipSkill;
import io.aiontechnology.mentorsuccess.entity.reference.LeadershipTrait;
import io.aiontechnology.mentorsuccess.model.inbound.InboundGame;
import io.aiontechnology.mentorsuccess.model.outbound.OutboundGame;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Mapper that converts a {@link Game} to a {@link InboundGame}.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
@Component
@RequiredArgsConstructor
public class GameEntityToModelMapper implements OneWayMapper<Game, OutboundGame> {

    /** Mapper between {@link LeadershipSkill} and an activity focus string. */
    private final OneWayCollectionMapper<ActivityFocus, String> activityFocusEntityToModelMapper;

    /** Mapper between {@link LeadershipSkill} and a leadership skill string. */
    private final OneWayCollectionMapper<LeadershipSkill, String> leadershipSkillEntityToModelMapper;

    /** Mapper between {@link LeadershipTrait} and leadership trait string. */
    private final OneWayCollectionMapper<LeadershipTrait, String> leadershipTraitEntityToModelMapper;

    /**
     * Map the given {@link Game} to a {@link OutboundGame}.
     *
     * @param game The {@link Game} to map.
     * @return The mapped {@link OutboundGame}.
     */
    @Override
    public Optional<OutboundGame> map(Game game) {
        return Optional.ofNullable(game)
                .map(g -> OutboundGame.builder()
                        .withId(g.getId())
                        .withName(g.getName())
                        .withGrade1(g.getGrade1())
                        .withGrade2(g.getGrade2())
                        .withLocation(g.getLocation())
                        .withActivityFocuses(activityFocusEntityToModelMapper.map(g.getActivityFocuses()))
                        .withLeadershipSkills(leadershipSkillEntityToModelMapper.map(g.getLeadershipSkills()))
                        .withLeadershipTraits(leadershipTraitEntityToModelMapper.map(g.getLeadershipTraits()))
                        .build());
    }

}
