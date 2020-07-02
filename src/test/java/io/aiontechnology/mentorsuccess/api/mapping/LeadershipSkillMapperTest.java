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
import io.aiontechnology.mentorsuccess.entity.LeadershipSkill;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link LeadershipSkillMapper}.
 *
 * @author <a href="mailto:whitney@aiontechnology.io">Whitney Hunter</a>
 * @since 1.0.0
 */
public class LeadershipSkillMapperTest {

    private static final UUID ID = UUID.randomUUID();
    private static final String NAME = "NAME";

    @Test
    void testMapEntityToModel() throws Exception {
        // setup the fixture
        LeadershipSkill leadershipSkill = new LeadershipSkill();
        leadershipSkill.setId(ID);
        leadershipSkill.setName(NAME);

        LeadershipSkillMapper leadershipSkillMapper = new LeadershipSkillMapper(null);

        // execute the SUT
        LeadershipSkillModel result = leadershipSkillMapper.mapEntityToModel(leadershipSkill);

        // validation
        assertThat(result.getName()).isEqualTo(NAME);
    }

    @Test
    void testMapModelToEntity_newEntity() throws Exception {
        // setup the fixture
        LeadershipSkillModel leadershipSkillModel = LeadershipSkillModel.builder()
                .withName(NAME)
                .build();
        LeadershipSkill leadershipSkill = new LeadershipSkill();
        leadershipSkill.setName(NAME);

        Function<String, Optional<LeadershipSkill>> leadershipSkillGetter = name -> Optional.of(leadershipSkill);
        LeadershipSkillMapper leadershipSkillMapper = new LeadershipSkillMapper(leadershipSkillGetter);

        // execute the SUT
        LeadershipSkill result = leadershipSkillMapper.mapModelToEntity(leadershipSkillModel);

        // validation
        assertThat(result).isEqualTo(leadershipSkill);
    }

    @Test
    void testMapModelToEntity_providedEntity() throws Exception {
        // setup the fixture
        LeadershipSkillModel leadershipSkillModel = LeadershipSkillModel.builder()
                .withName(NAME)
                .build();
        LeadershipSkill leadershipSkill = new LeadershipSkill();
        leadershipSkill.setName(NAME);

        Function<String, Optional<LeadershipSkill>> leadershipSkillGetter = name -> Optional.of(leadershipSkill);
        LeadershipSkillMapper leadershipSkillMapper = new LeadershipSkillMapper(leadershipSkillGetter);

        LeadershipSkill providedLeadershipSkill = new LeadershipSkill();

        // execute the SUT
        LeadershipSkill result = leadershipSkillMapper.mapModelToEntity(leadershipSkillModel, providedLeadershipSkill);

        // validation
        assertThat(result).isEqualTo(leadershipSkill);
        assertThat(result).isSameAs(providedLeadershipSkill);
    }

}
