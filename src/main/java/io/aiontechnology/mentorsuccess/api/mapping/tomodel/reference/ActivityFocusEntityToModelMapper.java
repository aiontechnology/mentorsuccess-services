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

import io.aiontechnology.mentorsuccess.api.mapping.OneWayMapper;
import io.aiontechnology.mentorsuccess.api.model.inbound.ActivityFocusModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.reference.BehaviorModel;
import io.aiontechnology.mentorsuccess.entity.ActivityFocus;
import io.aiontechnology.mentorsuccess.entity.reference.Behavior;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Mapper that converts a {@link Behavior} to a {@link BehaviorModel}.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
@Component
public class ActivityFocusEntityToModelMapper implements OneWayMapper<ActivityFocus, ActivityFocusModel> {

    /**
     * Map the given {@link ActivityFocus} to a {@link ActivityFocusModel}.
     *
     * @param activityFocus The {@link ActivityFocus} to map.
     * @return The mapped {@link ActivityFocusModel}.
     */
    @Override
    public Optional<ActivityFocusModel> map(ActivityFocus activityFocus) {
        return Optional.ofNullable(activityFocus)
                .map(a -> ActivityFocusModel.builder()
                        .withName(activityFocus.getName())
                        .build());
    }

}
