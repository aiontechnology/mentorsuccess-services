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

import io.aiontechnology.mentorsuccess.api.model.ActivityFocusModel;
import io.aiontechnology.mentorsuccess.api.model.ActivityFocusModelHolder;
import io.aiontechnology.mentorsuccess.entity.ActivityFocus;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Mapper between {@link ActivityFocus} and {@link ActivityFocusModel}.
 *
 * @author Whitney Hunter
 * @since 0.2.0
 */
@RequiredArgsConstructor
public class ActivityFocusMapper implements Mapper<ActivityFocus, ActivityFocusModel> {

    /** Function for retrieving {@link ActivityFocus} objects by name */
    private final Function<String, Optional<ActivityFocus>> activityFocusGetter;

    /**
     * Map from the given {@link ActivityFocus} to a {@link ActivityFocusModel}.
     *
     * @param activityFocus The {@link ActivityFocus} to map from.
     * @return The newly mapped {@link ActivityFocusModel}.
     */
    @Override
    public ActivityFocusModel mapEntityToModel(ActivityFocus activityFocus) {
        return ActivityFocusModel.builder()
                .withName(activityFocus.getName())
                .build();
    }

    /**
     * Map from the given {@link ActivityFocusModel} to a {@link ActivityFocus}.
     *
     * @param activityFocusModel The {@link ActivityFocusModel} to map from.
     * @return The newly mapped {@link ActivityFocus}.
     */
    @Override
    public ActivityFocus mapModelToEntity(ActivityFocusModel activityFocusModel) {
        ActivityFocus activityFocus = new ActivityFocus();
        return mapModelToEntity(activityFocusModel, activityFocus);
    }

    /**
     * Map from a given {@link ActivityFocusModel} into the given {@link ActivityFocus}.
     *
     * @param activityFocusModel The {@link ActivityFocusModel} to map from.
     * @param activityFocus The {@link ActivityFocus} to map to.
     * @return The mapped {@link ActivityFocus}.
     */
    @Override
    public ActivityFocus mapModelToEntity(ActivityFocusModel activityFocusModel, ActivityFocus activityFocus) {
        return Optional.ofNullable(activityFocusModel)
                .map(ActivityFocusModel::getName)
                .map(name -> this.activityFocusGetter.apply(name))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(i -> {
                    activityFocus.setId(i.getId());
                    activityFocus.setName(i.getName());
                    return activityFocus;
                })
                .orElseThrow(() -> new IllegalArgumentException("Illegal activity focus specified"));
    }

    /**
     * Map a collection of {@link ActivityFocus} to a collection of {@link ActivityFocusModel}.
     *
     * @param activityFocusSupplier A support that provides the collection of {@link ActivityFocus}.
     * @return The collection of mapped {@link ActivityFocusModel}.
     */
    public Collection<ActivityFocusModel> mapActivityFocuses(Supplier<Collection<ActivityFocus>> activityFocusSupplier) {
        return activityFocusSupplier.get().stream()
                .map(this::mapEntityToModel)
                .collect(Collectors.toSet());
    }

    /**
     * Map a collection of {@link ActivityFocusModel} to a collection of {@link ActivityFocus}.
     *
     * @param holder The holder containing the collection of {@link ActivityFocusModel} to map.
     * @return The collection of mapped {@link ActivityFocus}.
     */
    public Collection<ActivityFocus> mapActivityFocuses(ActivityFocusModelHolder holder) {
        if (holder.getActivityFocuses() != null) {
            return holder.getActivityFocuses().stream()
                    .map(this::mapModelToEntity)
                    .collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }

}
