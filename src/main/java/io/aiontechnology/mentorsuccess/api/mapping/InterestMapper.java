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

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Mapper between {@link Interest} and {@link InterestModel}.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@RequiredArgsConstructor
public class InterestMapper implements Mapper<Interest, InterestModel> {

    /** Function for retrieving {@link Interest} objects by name */
    private final Function<String, Optional<Interest>> interestGetter;

    /**
     * Map from the given {@link Interest} to a {@link InterestModel}.
     *
     * @param interest The {@link Interest} to map from.
     * @return The newly mapped {@link InterestModel}.
     */
    @Override
    public InterestModel mapEntityToModel(Interest interest) {
        return InterestModel.builder()
                .withName(interest.getName())
                .build();
    }

    /**
     * Map from the given {@link InterestModel} to a {@link Interest}.
     *
     * @param interestModel The {@link InterestModel} to map from.
     * @return The newly mapped {@link Interest}.
     */
    @Override
    public Interest mapModelToEntity(InterestModel interestModel) {
        Interest interest = new Interest();
        return mapModelToEntity(interestModel, interest);
    }

    /**
     * Map from a given {@link InterestModel} into the given {@link Interest}.
     *
     * @param interestModel The {@link InterestModel} to map from.
     * @param interest The {@link Interest} to map to.
     * @return The mapped {@link Interest}.
     */
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

    /**
     * Map a collection of {@link Interest} to a collection of {@link InterestModel}.
     *
     * @param interestSupplier A support that provides the collection of {@link Interest}.
     * @return The collection of mapped {@link InterestModel}.
     */
    public Collection<InterestModel> mapInterests(Supplier<Collection<Interest>> interestSupplier) {
        return interestSupplier.get().stream()
                .map(this::mapEntityToModel)
                .collect(Collectors.toSet());
    }

    /**
     * Map a collection of {@link InterestModel} to a collection of {@link Interest}.
     *
     * @param holder The holder containing the collection of {@link InterestModel} to map.
     * @return The collection of mapped {@link Interest}.
     */
    public Collection<Interest> mapInterests(InterestModelHolder holder) {
        if (holder.getInterests() != null) {
            return holder.getInterests().stream()
                    .map(this::mapModelToEntity)
                    .collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }

}
