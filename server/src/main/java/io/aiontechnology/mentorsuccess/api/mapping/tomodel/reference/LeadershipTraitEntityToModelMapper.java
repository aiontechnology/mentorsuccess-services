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

package io.aiontechnology.mentorsuccess.api.mapping.tomodel.reference;

import io.aiontechnology.atlas.mapping.OneWayMapper;
import io.aiontechnology.mentorsuccess.entity.reference.LeadershipSkill;
import io.aiontechnology.mentorsuccess.entity.reference.LeadershipTrait;
import io.aiontechnology.mentorsuccess.model.inbound.reference.InboundLeadershipSkill;
import io.aiontechnology.mentorsuccess.model.outbound.reference.OutboundLeadershipTrait;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Mapper that converts a {@link LeadershipSkill} to a {@link InboundLeadershipSkill}.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
@Component
public class LeadershipTraitEntityToModelMapper implements OneWayMapper<LeadershipTrait, OutboundLeadershipTrait> {

    /**
     * Map the given {@link LeadershipTrait} to a {@link OutboundLeadershipTrait}.
     *
     * @param leadershipTrait The {@link LeadershipTrait} to map.
     * @return The mapped {@link OutboundLeadershipTrait}.
     */
    @Override
    public Optional<OutboundLeadershipTrait> map(LeadershipTrait leadershipTrait) {
        return Optional.ofNullable(leadershipTrait)
                .map(l -> OutboundLeadershipTrait.builder()
                        .withName(leadershipTrait.getName())
                        .build());
    }

}
