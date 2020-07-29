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

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:whitney@aiontechnology.io">Whitney Hunter</a>
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class LeadershipTraitMapper implements Mapper<LeadershipTrait, LeadershipTraitModel> {

    private final Function<String, Optional<LeadershipTrait>> leadershipTraitGetter;

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

    public Set<LeadershipTraitModel> mapLeadershipTraits(Supplier<Set<LeadershipTrait>> leadershipTraitSuppler) {
        return leadershipTraitSuppler.get().stream()
                .map(this::mapEntityToModel)
                .collect(Collectors.toSet());
    }

    public Set<LeadershipTrait> mapLeadershipTraits(LeadershipTraitModelHolder holder) {
        if (holder.getLeadershipTraits() != null) {
            return holder.getLeadershipTraits().stream()
                    .map(this::mapModelToEntity)
                    .collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }

}
