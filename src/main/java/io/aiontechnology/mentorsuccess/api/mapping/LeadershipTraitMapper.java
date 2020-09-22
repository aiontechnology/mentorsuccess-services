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
import io.aiontechnology.mentorsuccess.api.model.LeadershipTraitModelHolder;
import io.aiontechnology.mentorsuccess.entity.LeadershipTrait;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author Whitney Hunter
 * @since 0.1.0
 */
@RequiredArgsConstructor
public class LeadershipTraitMapper implements Mapper<LeadershipTrait, LeadershipTraitModel> {

    /** Function for retrieving {@link LeadershipTrait} objects by name */
    private final Function<String, Optional<LeadershipTrait>> leadershipTraitGetter;

    /**
     * Map from the given {@link LeadershipTrait} to a {@link LeadershipTraitModel}.
     *
     * @param leadershipTrait The {@link LeadershipTrait} to map from.
     * @return The newly mapped {@link LeadershipTraitModel}.
     */
    @Override
    public LeadershipTraitModel mapEntityToModel(LeadershipTrait leadershipTrait) {
        return LeadershipTraitModel.builder()
                .withName(leadershipTrait.getName())
                .build();
    }

    /**
     * Map from the given {@link LeadershipTraitModel} to a {@link LeadershipTrait}.
     *
     * @param leadershipTraitModel The {@link LeadershipTraitModel} to map from.
     * @return The newly mapped {@link LeadershipTrait}.
     */
    @Override
    public LeadershipTrait mapModelToEntity(LeadershipTraitModel leadershipTraitModel) {
        LeadershipTrait leadershipTrait = new LeadershipTrait();
        return mapModelToEntity(leadershipTraitModel, leadershipTrait);
    }

    /**
     * Map from a given {@link LeadershipTraitModel} into the given {@link LeadershipTrait}.
     *
     * @param leadershipTraitModel The {@link LeadershipTraitModel} to map from.
     * @param leadershipTrait The {@link LeadershipTrait} to map to.
     * @return The mapped {@link LeadershipTrait}.
     */
    @Override
    public LeadershipTrait mapModelToEntity(LeadershipTraitModel leadershipTraitModel, LeadershipTrait leadershipTrait) {
        return Optional.ofNullable(leadershipTraitModel)
                .map(LeadershipTraitModel::getName)
                .map(name -> leadershipTraitGetter.apply(name))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(lt -> {
                    leadershipTrait.setId(lt.getId());
                    leadershipTrait.setName(lt.getName());
                    return leadershipTrait;
                })
                .orElseThrow(() -> new IllegalArgumentException("Illegal leadership trait specified"));
    }

    /**
     * Map a collection of {@link LeadershipTrait} to a collection of {@link LeadershipTraitModel}.
     *
     * @param leadershipTraitSuppler A support that provides the collection of {@link LeadershipTrait}.
     * @return The collection of mapped {@link LeadershipTraitModel}.
     */
    public Collection<LeadershipTraitModel> mapLeadershipTraits(Supplier<Collection<LeadershipTrait>> leadershipTraitSuppler) {
        return leadershipTraitSuppler.get().stream()
                .map(this::mapEntityToModel)
                .collect(Collectors.toSet());
    }

    /**
     * Map a collection of {@link LeadershipTraitModel} to a collection of {@link LeadershipTrait}.
     *
     * @param holder The holder containing the collection of {@link LeadershipTraitModel} to map.
     * @return The collection of mapped {@link LeadershipTrait}.
     */
    public Collection<LeadershipTrait> mapLeadershipTraits(LeadershipTraitModelHolder holder) {
        if (holder.getLeadershipTraits() != null) {
            return holder.getLeadershipTraits().stream()
                    .map(this::mapModelToEntity)
                    .collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }

}
