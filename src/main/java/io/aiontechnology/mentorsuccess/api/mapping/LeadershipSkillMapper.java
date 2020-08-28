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
public class LeadershipSkillMapper implements Mapper<LeadershipSkill, LeadershipSkillModel> {

    private final Function<String, Optional<LeadershipSkill>> leadershipSkillGetter;

    @Override
    public LeadershipSkillModel mapEntityToModel(LeadershipSkill interest) {
        return LeadershipSkillModel.builder()
                .withName(interest.getName())
                .build();
    }

    @Override
    public LeadershipSkill mapModelToEntity(LeadershipSkillModel leadershipSkillModel) {
        LeadershipSkill leadershipSkill = new LeadershipSkill();
        return mapModelToEntity(leadershipSkillModel, leadershipSkill);
    }

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

    public Set<LeadershipSkillModel> mapLeadershipSkills(Supplier<Set<LeadershipSkill>> leadershipSkillsSupplier) {
        return leadershipSkillsSupplier.get().stream()
                .map(this::mapEntityToModel)
                .collect(Collectors.toSet());
    }

    public Set<LeadershipSkill> mapLeadershipSkills(LeadershipSkillModelHolder holder) {
        if (holder.getLeadershipSkills() != null) {
            return holder.getLeadershipSkills().stream()
                    .map(this::mapModelToEntity)
                    .collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }

}
