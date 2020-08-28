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

import io.aiontechnology.mentorsuccess.api.controller.LeadershipSkillController;
import io.aiontechnology.mentorsuccess.api.mapping.LeadershipSkillMapper;
import io.aiontechnology.mentorsuccess.api.model.LeadershipSkillModel;
import io.aiontechnology.mentorsuccess.entity.LeadershipSkill;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Optional;

/**
 * @author <a href="mailto:whitney@aiontechnology.io">Whitney Hunter</a>
 * @since 1.0.0
 */
@Component
public class LeadershipSkillModelAssembler extends RepresentationModelAssemblerSupport<LeadershipSkill, LeadershipSkillModel> {

    private final LeadershipSkillMapper leadershipSkillMapper;

    /**
     * Construct an instance.
     */
    @Inject
    public LeadershipSkillModelAssembler(LeadershipSkillMapper leadershipSkillMapper) {
        super(LeadershipSkillController.class, LeadershipSkillModel.class);
        this.leadershipSkillMapper = leadershipSkillMapper;
    }

    @Override
    public LeadershipSkillModel toModel(LeadershipSkill leadershipSkill) {
        return Optional.ofNullable(leadershipSkill)
                .map(leadershipSkillMapper::mapEntityToModel)
                .orElse(null);
    }

}
