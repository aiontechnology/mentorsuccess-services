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
import io.aiontechnology.mentorsuccess.api.model.InterestModel;
import io.aiontechnology.mentorsuccess.entity.Interest;
import org.springframework.stereotype.Component;

/**
 * Mapper between {@link Interest} and {@link InterestModel}.
 *
 * @author <a href="mailto:whitney@aiontechnology.io">Whitney Hunter</a>
 * @since 1.0.0
 */
@Component
public class InterestMapper implements Mapper<Interest, InterestModel> {

    @Override
    public InterestModel mapEntityToModel(Interest interest) {
        return InterestModel.builder()
                .withName(interest.getName())
                .build();
    }

    @Override
    public Interest mapModelToEntity(InterestModel interestModel) {
        throw new NotImplementedException();
    }

    @Override
    public Interest mapModelToEntity(InterestModel interestModel, Interest interest) {
        throw new NotImplementedException();
    }

}
