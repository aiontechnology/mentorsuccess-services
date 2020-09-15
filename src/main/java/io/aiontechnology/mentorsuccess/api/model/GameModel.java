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

package io.aiontechnology.mentorsuccess.api.model;

import io.aiontechnology.mentorsuccess.entity.ResourceLocation;
import io.aiontechnology.mentorsuccess.util.EnumNamePattern;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.UUID;

/**
 * A model object for games.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(setterPrefix = "with")
@ToString
public class GameModel extends ResourceModel<GameModel> implements ActivityFocusModelHolder, LeadershipSkillModelHolder {

    /** The game's id. */
    private final UUID id;

    /** The game's name. */
    @NotNull(message = "{game.name.notNull}")
    @Size(max = 40, message = "{game.name.size}")
    private final String name;

    /** The game's description. */
    @Size(max = 50, message = "{game.description.size}")
    private final String description;

    /** The game's grade level. */
    @NotNull(message = "{game.gradeLevel.notNull}")
    @Min(value = 1, message = "{game.gradeLevel.invalid}")
    @Max(value = 6, message = "{game.gradeLevel.invalid}")
    private final Integer gradeLevel;

    @EnumNamePattern(regexp = "ONLINE|OFFLINE|BOTH", message = "{book.location.invalid}")
    private final ResourceLocation location;

    /** The activity focus associated with the game. */
    private final Collection<ActivityFocusModel> activityFocuses;

    /** The leadership skills associated with the book. */
    private final Collection<LeadershipSkillModel> leadershipSkills;

}
