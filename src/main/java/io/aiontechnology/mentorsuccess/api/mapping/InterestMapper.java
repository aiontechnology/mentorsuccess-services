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
import io.aiontechnology.mentorsuccess.api.model.InterestModelHolder;
import io.aiontechnology.mentorsuccess.entity.Interest;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Mapper between {@link Interest} and {@link InterestModel}.
 *
 * @author <a href="mailto:whitney@aiontechnology.io">Whitney Hunter</a>
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class InterestMapper implements Mapper<Interest, InterestModel> {

    private final Function<String, Optional<Interest>> interestGetter;

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
        return Optional.ofNullable(interestModel)
                .map(InterestModel::getName)
                .map(name -> this.interestGetter.apply(name))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(i -> {
                    interest.setId(i.getId());
                    interest.setName(i.getName());
                    return interest;
                })
                .orElseThrow(() -> new IllegalArgumentException("Illegal interest specified"));
    }

    public Set<InterestModel> mapInterests(Supplier<Set<Interest>> interestSupplier) {
        return interestSupplier.get().stream()
                .map(this::mapEntityToModel)
                .collect(Collectors.toSet());
    }

    public Set<Interest> mapInterests(InterestModelHolder holder) {
        if (holder.getInterests() != null) {
            return holder.getInterests().stream()
                    .map(this::mapModelToEntity)
                    .collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }

}
