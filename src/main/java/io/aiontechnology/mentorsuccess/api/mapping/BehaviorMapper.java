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

import io.aiontechnology.mentorsuccess.api.model.BehaviorModel;
import io.aiontechnology.mentorsuccess.api.model.BehaviorModelHolder;
import io.aiontechnology.mentorsuccess.entity.Behavior;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Mapper between {@link Behavior} and {@link BehaviorModel}.
 *
 * @author <a href="mailto:whitney@aiontechnology.io">Whitney Hunter</a>
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class BehaviorMapper implements Mapper<Behavior, BehaviorModel> {

    private final Function<String, Optional<Behavior>> behaviorGetter;

    @Override
    public BehaviorModel mapEntityToModel(Behavior behavior) {
        return BehaviorModel.builder()
                .withName(behavior.getName())
                .build();
    }

    @Override
    public Behavior mapModelToEntity(BehaviorModel behaviorModel) {
        Behavior behavior = new Behavior();
        return mapModelToEntity(behaviorModel, behavior);
    }

    @Override
    public Behavior mapModelToEntity(BehaviorModel behaviorModel, Behavior behavior) {
        return Optional.ofNullable(behaviorModel)
                .map(BehaviorModel::getName)
                .map(name -> this.behaviorGetter.apply(name))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(b -> {
                    behavior.setId(b.getId());
                    behavior.setName(b.getName());
                    return behavior;
                })
                .orElseThrow(() -> new IllegalArgumentException("Illegal interest specified"));
    }

    public Set<BehaviorModel> mapBehaviors(Supplier<Set<Behavior>> behaviorSupplier) {
        return behaviorSupplier.get().stream()
                .map(this::mapEntityToModel)
                .collect(Collectors.toSet());
    }

    public Set<Behavior> mapBehaviors(BehaviorModelHolder holder) {
        if (holder.getBehaviors() != null) {
            return holder.getBehaviors().stream()
                    .map(this::mapModelToEntity)
                    .collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }

}
