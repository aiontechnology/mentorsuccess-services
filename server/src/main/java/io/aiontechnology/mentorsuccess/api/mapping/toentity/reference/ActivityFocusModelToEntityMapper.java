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

package io.aiontechnology.mentorsuccess.api.mapping.toentity.reference;

import io.aiontechnology.atlas.mapping.OneWayMapper;
import io.aiontechnology.mentorsuccess.entity.ActivityFocus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Function;

/**
 * Mapper that converts an activity focus string to a {@link ActivityFocus}.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
@Component
@RequiredArgsConstructor
public class ActivityFocusModelToEntityMapper implements OneWayMapper<String, ActivityFocus> {

    /** Function that retrieves a {@link ActivityFocus} by its name */
    private final Function<String, Optional<ActivityFocus>> getter;

    /**
     * Map the given activity focus string.
     *
     * @param inboundActivityFocus The activity focus string to map.
     * @return The resulting {@link ActivityFocus}.
     */
    @Override
    public Optional<ActivityFocus> map(String inboundActivityFocus) {
        return Optional.ofNullable(inboundActivityFocus)
                .flatMap(getter::apply);
    }

}
