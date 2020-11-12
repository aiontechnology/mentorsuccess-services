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
import io.aiontechnology.mentorsuccess.api.controller.BehaviorController;
import io.aiontechnology.mentorsuccess.api.mapping.tomodel.reference.BehaviorEntityToModelMapper;
import io.aiontechnology.mentorsuccess.entity.reference.Behavior;
import io.aiontechnology.mentorsuccess.model.outbound.reference.OutboundBehavior;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Assembles a {@link OutboundBehavior} from a {@link Behavior}.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@Component
public class BehaviorModelAssembler extends RepresentationModelAssemblerSupport<Behavior, OutboundBehavior> {

    /** Mapper to map between {@link Behavior} and {@link OutboundBehavior}. */
    private final OneWayMapper<Behavior, OutboundBehavior> mapper;

    /**
     * Construct an instance.
     *
     * @param mapper Mapper to map between {@link Behavior} and {@link OutboundBehavior}
     */
    @Inject
    public BehaviorModelAssembler(BehaviorEntityToModelMapper mapper) {
        super(BehaviorController.class, OutboundBehavior.class);
        this.mapper = mapper;
    }

    @Override
    public OutboundBehavior toModel(Behavior behavior) {
        return Optional.ofNullable(behavior)
                .flatMap(mapper::map)
                .orElse(null);
    }

}
