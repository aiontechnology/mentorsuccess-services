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

import io.aiontechnology.mentorsuccess.api.controller.LeadershipTraitController;
import io.aiontechnology.mentorsuccess.api.mapping.OneWayMapper;
import io.aiontechnology.mentorsuccess.api.model.inbound.reference.LeadershipTraitModel;
import io.aiontechnology.mentorsuccess.entity.reference.LeadershipTrait;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Assembles a {@link LeadershipTraitModel} from a {@link LeadershipTrait}.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@Component
public class LeadershipTraitModelAssembler extends RepresentationModelAssemblerSupport<LeadershipTrait, LeadershipTraitModel> {

    private final OneWayMapper<LeadershipTrait, LeadershipTraitModel> mapper;

    /**
     * Constructor
     *
     * @param mapper The mapper for mapping between {@link LeadershipTrait} and {@link LeadershipTraitModel}.
     */
    @Inject
    public LeadershipTraitModelAssembler(OneWayMapper<LeadershipTrait, LeadershipTraitModel> mapper) {
        super(LeadershipTraitController.class, LeadershipTraitModel.class);
        this.mapper = mapper;
    }

    /**
     * Map a {@link LeadershipTrait} to a {@link LeadershipTraitModel} without adding links.
     *
     * @param leadershipTrait The {@link LeadershipTrait} to map.
     * @return The resulting {@link LeadershipTraitModel}.
     */
    @Override
    public LeadershipTraitModel toModel(LeadershipTrait leadershipTrait) {
        return Optional.ofNullable(leadershipTrait)
                .flatMap(mapper::map)
                .orElse(null);
    }

}
