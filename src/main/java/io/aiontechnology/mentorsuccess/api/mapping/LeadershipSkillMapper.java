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

import io.aiontechnology.mentorsuccess.api.model.LeadershipSkillModel;
import io.aiontechnology.mentorsuccess.api.model.LeadershipSkillModelHolder;
import io.aiontechnology.mentorsuccess.entity.LeadershipSkill;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Mapper between {@link LeadershipSkill} and {@link LeadershipSkillModel}.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@RequiredArgsConstructor
public class LeadershipSkillMapper implements Mapper<LeadershipSkill, LeadershipSkillModel> {

    /** Function for retrieving {@link LeadershipSkill} objects by name */
    private final Function<String, Optional<LeadershipSkill>> leadershipSkillGetter;

    /**
     * Map from the given {@link LeadershipSkill} to a {@link LeadershipSkillModel}.
     *
     * @param leadershipSkill The {@link LeadershipSkill} to map from.
     * @return The newly mapped {@link LeadershipSkillModel}.
     */
    @Override
    public LeadershipSkillModel mapEntityToModel(LeadershipSkill leadershipSkill) {
        return LeadershipSkillModel.builder()
                .withName(leadershipSkill.getName())
                .build();
    }

    /**
     * Map from the given {@link LeadershipSkillModel} to a {@link LeadershipSkill}.
     *
     * @param leadershipSkillModel The {@link LeadershipSkillModel} to map from.
     * @return The newly mapped {@link LeadershipSkill}.
     */
    @Override
    public LeadershipSkill mapModelToEntity(LeadershipSkillModel leadershipSkillModel) {
        LeadershipSkill leadershipSkill = new LeadershipSkill();
        return mapModelToEntity(leadershipSkillModel, leadershipSkill);
    }

    /**
     * Map from a given {@link LeadershipSkillModel} into the given {@link LeadershipSkill}.
     *
     * @param leadershipSkillModel The {@link LeadershipSkillModel} to map from.
     * @param leadershipSkill The {@link LeadershipSkill} to map to.
     * @return The mapped {@link LeadershipSkill}.
     */
    @Override
    public LeadershipSkill mapModelToEntity(LeadershipSkillModel leadershipSkillModel, LeadershipSkill leadershipSkill) {
        return Optional.ofNullable(leadershipSkillModel)
                .map(LeadershipSkillModel::getName)
                .map(name -> leadershipSkillGetter.apply(name))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(ls -> {
                    leadershipSkill.setId(ls.getId());
                    leadershipSkill.setName(ls.getName());
                    return leadershipSkill;
                })
                .orElseThrow(() -> new IllegalArgumentException("Illegal leadership trait specified"));
    }

    /**
     * Map a collection of {@link LeadershipSkill} to a collection of {@link LeadershipSkillModel}.
     *
     * @param leadershipSkillsSupplier A support that provides the collection of {@link LeadershipSkill}.
     * @return The collection of mapped {@link LeadershipSkillModel}.
     */
    public Collection<LeadershipSkillModel> mapLeadershipSkills(Supplier<Collection<LeadershipSkill>> leadershipSkillsSupplier) {
        return leadershipSkillsSupplier.get().stream()
                .map(this::mapEntityToModel)
                .collect(Collectors.toSet());
    }

    /**
     * Map a collection of {@link LeadershipSkillModel} to a collection of {@link LeadershipSkill}.
     *
     * @param holder The holder containing the collection of {@link LeadershipSkillModel} to map.
     * @return The collection of mapped {@link LeadershipSkill}.
     */
    public Collection<LeadershipSkill> mapLeadershipSkills(LeadershipSkillModelHolder holder) {
        if (holder.getLeadershipSkills() != null) {
            return holder.getLeadershipSkills().stream()
                    .map(this::mapModelToEntity)
                    .collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }

}
