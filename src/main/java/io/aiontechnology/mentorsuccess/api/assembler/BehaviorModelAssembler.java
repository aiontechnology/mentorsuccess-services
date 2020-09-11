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

import io.aiontechnology.mentorsuccess.api.controller.BehaviorController;
import io.aiontechnology.mentorsuccess.api.mapping.BehaviorMapper;
import io.aiontechnology.mentorsuccess.api.model.BehaviorModel;
import io.aiontechnology.mentorsuccess.entity.Behavior;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Assembles a {@link BehaviorModel} from a {@link Behavior}.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@Component
public class BehaviorModelAssembler extends RepresentationModelAssemblerSupport<Behavior, BehaviorModel> {

    /** Mapper to map between {@link Behavior} and {@link BehaviorModel}. */
    private final BehaviorMapper behaviorMapper;

    /**
     * Construct an instance.
     */
    @Inject
    public BehaviorModelAssembler(BehaviorMapper behaviorMapper) {
        super(BehaviorController.class, BehaviorModel.class);
        this.behaviorMapper = behaviorMapper;
    }

    @Override
    public BehaviorModel toModel(Behavior behavior) {
        return Optional.ofNullable(behavior)
                .map(behaviorMapper::mapEntityToModel)
                .orElse(null);
    }

}
