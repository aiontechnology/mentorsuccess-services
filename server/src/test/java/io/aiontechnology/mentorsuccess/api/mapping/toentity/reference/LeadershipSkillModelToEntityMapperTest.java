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

package io.aiontechnology.mentorsuccess.api.mapping.toentity.reference;

import io.aiontechnology.mentorsuccess.entity.reference.LeadershipSkill;
import io.aiontechnology.mentorsuccess.model.inbound.reference.InboundLeadershipSkill;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link LeadershipSkillModelToEntityMapper}.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
public class LeadershipSkillModelToEntityMapperTest {

    @Test
    void testMapping() {
        // setup the fixture
        LeadershipSkill leadershipSkill = new LeadershipSkill();
        UUID leadershipSkillId = UUID.randomUUID();
        leadershipSkill.setId(leadershipSkillId);
        String leadershipSkillName = "LEADERSHIP_SKILL";
        leadershipSkill.setName(leadershipSkillName);

        InboundLeadershipSkill inboundLeadershipSkill = InboundLeadershipSkill.builder()
                .withName(leadershipSkillName)
                .build();

        Function<String, Optional<LeadershipSkill>> getterFunction = m -> Optional.of(leadershipSkill);
        LeadershipSkillModelToEntityMapper leadershipSkillModelToEntityMapper = new LeadershipSkillModelToEntityMapper(getterFunction);

        // execute the SUT
        Optional<LeadershipSkill> result = leadershipSkillModelToEntityMapper.map(inboundLeadershipSkill);

        // validation
        assertThat(result).isNotEmpty();
        assertThat(result.get()).isEqualTo(leadershipSkill);
    }

    @Test
    void testNotFound() {
        // setup the fixture
        String leadershipSkillName = "BEHAVIOR";
        InboundLeadershipSkill behaviorModel = InboundLeadershipSkill.builder()
                .withName(leadershipSkillName)
                .build();

        Function<String, Optional<LeadershipSkill>> getterFunction = m -> Optional.empty();
        LeadershipSkillModelToEntityMapper leadershipSkillModelToEntityMapper = new LeadershipSkillModelToEntityMapper(getterFunction);

        // execute the SUT
        Optional<LeadershipSkill> result = leadershipSkillModelToEntityMapper.map(behaviorModel);

        // validation
        assertThat(result).isEmpty();
    }

    @Test
    void testNull() {
        // setup the fixture
        Function<String, Optional<LeadershipSkill>> getterFunction = m -> Optional.empty();
        LeadershipSkillModelToEntityMapper leadershipSkillModelToEntityMapper = new LeadershipSkillModelToEntityMapper(getterFunction);

        // execute the SUT
        Optional<LeadershipSkill> result = leadershipSkillModelToEntityMapper.map(null);

        // validation
        assertThat(result).isEmpty();
    }

}
