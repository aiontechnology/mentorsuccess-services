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

package io.aiontechnology.mentorsuccess.api.assembler;

import io.aiontechnology.mentorsuccess.api.controller.ActivityFocusController;
import io.aiontechnology.mentorsuccess.api.mapping.OneWayMapper;
import io.aiontechnology.mentorsuccess.api.model.inbound.ActivityFocusModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.reference.InterestModel;
import io.aiontechnology.mentorsuccess.entity.ActivityFocus;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Assembles a {@link ActivityFocusModel} from an {@link ActivityFocus}.
 *
 * @author Whitney Hunter
 * @since 0.2.0
 */
@Component
public class ActivityFocusModelAssembler extends RepresentationModelAssemblerSupport<ActivityFocus, ActivityFocusModel> {

    /** Mapper to map between {@link ActivityFocus} and {@link InterestModel}. */
    private final OneWayMapper<ActivityFocus, ActivityFocusModel> mapper;

    /**
     * Constructor
     *
     * @param mapper The mapper for mapping between {@link ActivityFocus} and {@link ActivityFocusModel}.
     */
    @Inject
    public ActivityFocusModelAssembler(OneWayMapper<ActivityFocus, ActivityFocusModel> mapper) {
        super(ActivityFocusController.class, ActivityFocusModel.class);
        this.mapper = mapper;
    }

    /**
     * Map an {@link ActivityFocus} to a {@link ActivityFocusModel} without adding links.
     *
     * @param activityFocus The {@link ActivityFocus} to map.
     * @return The resulting {@link ActivityFocusModel}.
     */
    @Override
    public ActivityFocusModel toModel(ActivityFocus activityFocus) {
        return Optional.ofNullable(activityFocus)
                .flatMap(mapper::map)
                .orElse(null);
    }

}
