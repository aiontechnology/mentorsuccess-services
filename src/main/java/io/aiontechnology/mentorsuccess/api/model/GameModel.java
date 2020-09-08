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

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.UUID;

/**
 * Model that represents a game in the API
 *
 * @author <a href="mailto:whitney@aiontechnology.io">Whitney Hunter</a>
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(setterPrefix = "with")
@ToString
public class GameModel extends ResourceModel<GameModel> implements LeadershipSkillModelHolder,
        LeadershipTraitModelHolder {

    private final UUID id;

    @NotNull(message = "{game.name.notNull}")
    @Size(max = 40, message = "{game.name.size}")
    private final String name;

    @Size(max = 50, message = "{game.description.size}")
    private final String description;

    @NotNull(message = "{game.gradeLevel.notNull}")
    @Min(value = 1, message = "{game.gradeLevel.invalid}")
    @Max(value = 6, message = "{game.gradeLevel.invalid}")
    private final Integer gradeLevel;

    private final Collection<LeadershipTraitModel> leadershipTraits;

    private final Collection<LeadershipSkillModel> leadershipSkills;

}
