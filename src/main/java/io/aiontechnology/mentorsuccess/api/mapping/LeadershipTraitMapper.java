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

import io.aiontechnology.mentorsuccess.api.error.NotImplementedException;
import io.aiontechnology.mentorsuccess.api.model.LeadershipTraitModel;
import io.aiontechnology.mentorsuccess.entity.LeadershipTrait;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:whitney@aiontechnology.io">Whitney Hunter</a>
 * @since 1.0.0
 */
@Component
public class LeadershipTraitMapper implements Mapper<LeadershipTrait, LeadershipTraitModel> {

    @Override
    public LeadershipTraitModel mapEntityToModel(LeadershipTrait leadershipTrait) {
        return LeadershipTraitModel.builder()
                .withName(leadershipTrait.getName())
                .build();
    }

    @Override
    public LeadershipTrait mapModelToEntity(LeadershipTraitModel leadershipTraitModel) {
        throw new NotImplementedException();
    }

    @Override
    public LeadershipTrait mapModelToEntity(LeadershipTraitModel leadershipTraitModel, LeadershipTrait leadershipTrait) {
        throw new NotImplementedException();
    }

}
