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
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Mapper that converts a {@link LeadershipSkill} to a leadership skill string.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
@Component
public class LeadershipSkillEntityToModelMapper implements OneWayMapper<LeadershipSkill, String> {

    /**
     * Map the given {@link LeadershipSkill} to a leadership skill string.
     *
     * @param leadershipSkill The {@link LeadershipSkill} to map.
     * @return The mapped leadership skill string.
     */
    @Override
    public Optional<String> map(LeadershipSkill leadershipSkill) {
        return Optional.ofNullable(leadershipSkill)
                .map(LeadershipSkill::getName);
    }

}
