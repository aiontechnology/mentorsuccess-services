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

import io.aiontechnology.atlas.mapping.OneWayMapper;
import io.aiontechnology.mentorsuccess.api.controller.ActivityFocusController;
import io.aiontechnology.mentorsuccess.entity.ActivityFocus;
import io.aiontechnology.mentorsuccess.model.inbound.InboundActivityFocus;
import io.aiontechnology.mentorsuccess.model.inbound.reference.InboundInterest;
import io.aiontechnology.mentorsuccess.model.outbound.OutboundActivityFocus;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Assembles a {@link InboundActivityFocus} from an {@link ActivityFocus}.
 *
 * @author Whitney Hunter
 * @since 0.2.0
 */
@Component
public class ActivityFocusModelAssembler extends RepresentationModelAssemblerSupport<ActivityFocus, OutboundActivityFocus> {

    /** Mapper to map between {@link ActivityFocus} and {@link InboundInterest}. */
    private final OneWayMapper<ActivityFocus, OutboundActivityFocus> mapper;

    /**
     * Constructor
     *
     * @param mapper The mapper for mapping between {@link ActivityFocus} and {@link InboundActivityFocus}.
     */
    @Inject
    public ActivityFocusModelAssembler(OneWayMapper<ActivityFocus, OutboundActivityFocus> mapper) {
        super(ActivityFocusController.class, OutboundActivityFocus.class);
        this.mapper = mapper;
    }

    /**
     * Map an {@link ActivityFocus} to a {@link InboundActivityFocus} without adding links.
     *
     * @param activityFocus The {@link ActivityFocus} to map.
     * @return The resulting {@link InboundActivityFocus}.
     */
    @Override
    public OutboundActivityFocus toModel(ActivityFocus activityFocus) {
        return Optional.ofNullable(activityFocus)
                .flatMap(mapper::map)
                .orElse(null);
    }

}
