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

import io.aiontechnology.mentorsuccess.api.model.InterestModel;
import io.aiontechnology.mentorsuccess.entity.Interest;
import io.aiontechnology.mentorsuccess.service.InterestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Mapper between {@link Interest} and {@link InterestModel}.
 *
 * @author <a href="mailto:whitney@aiontechnology.io">Whitney Hunter</a>
 * @since 1.0.0
 */
@Component
@RequiredArgsConstructor
public class InterestMapper implements Mapper<Interest, InterestModel> {

    private final InterestService interestService;

    @Override
    public InterestModel mapEntityToModel(Interest interest) {
        return InterestModel.builder()
                .withName(interest.getName())
                .build();
    }

    @Override
    public Interest mapModelToEntity(InterestModel interestModel) {
        Interest interest = new Interest();
        return mapModelToEntity(interestModel, interest);
    }

    @Override
    public Interest mapModelToEntity(InterestModel interestModel, Interest interest) {
        Interest interest1 = Optional.ofNullable(interestModel)
                .map(InterestModel::getName)
                .map(interestService::findInterestByName)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .orElse(null);
        interest.setId(interest1.getId());
        interest.setName(interest1.getName());
        return interest;
    }

}
