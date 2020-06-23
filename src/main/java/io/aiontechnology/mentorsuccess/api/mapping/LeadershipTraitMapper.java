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

package io.aiontechnology.mentorsuccess.api.mapping;

import io.aiontechnology.mentorsuccess.api.model.LeadershipTraitModel;
import io.aiontechnology.mentorsuccess.entity.LeadershipTrait;
import io.aiontechnology.mentorsuccess.service.LeadershipTraitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author <a href="mailto:whitney@aiontechnology.io">Whitney Hunter</a>
 * @since 1.0.0
 */
@Component
@RequiredArgsConstructor
public class LeadershipTraitMapper implements Mapper<LeadershipTrait, LeadershipTraitModel> {

    private final LeadershipTraitService leadershipTraitService;

    @Override
    public LeadershipTraitModel mapEntityToModel(LeadershipTrait leadershipTrait) {
        return LeadershipTraitModel.builder()
                .withName(leadershipTrait.getName())
                .build();
    }

    @Override
    public LeadershipTrait mapModelToEntity(LeadershipTraitModel leadershipTraitModel) {
        LeadershipTrait leadershipTrait = new LeadershipTrait();
        return mapModelToEntity(leadershipTraitModel, leadershipTrait);
    }

    @Override
    public LeadershipTrait mapModelToEntity(LeadershipTraitModel leadershipTraitModel, LeadershipTrait leadershipTrait) {
        LeadershipTrait leadershipTrait1 = Optional.ofNullable(leadershipTraitModel)
                .map(LeadershipTraitModel::getName)
                .map(leadershipTraitService::findCharacterTraitByName)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .orElse(null);
        leadershipTrait.setId(leadershipTrait1.getId());
        leadershipTrait.setName(leadershipTrait1.getName());
        return leadershipTrait;
    }

}
