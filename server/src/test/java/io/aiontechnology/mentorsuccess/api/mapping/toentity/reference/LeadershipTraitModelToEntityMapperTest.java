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

import io.aiontechnology.mentorsuccess.entity.reference.LeadershipTrait;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link LeadershipTraitModelToEntityMapper}.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
public class LeadershipTraitModelToEntityMapperTest {

    @Test
    void testMapping() {
        // setup the fixture
        LeadershipTrait leadershipTrait = new LeadershipTrait();
        UUID leadershipTraitId = UUID.randomUUID();
        leadershipTrait.setId(leadershipTraitId);
        String leadershipTraitName = "LEADERSHIP_TRAIT";
        leadershipTrait.setName(leadershipTraitName);

        Function<String, Optional<LeadershipTrait>> getterFunction = m -> Optional.of(leadershipTrait);
        LeadershipTraitModelToEntityMapper leadershipTraitModelToEntityMapper = new LeadershipTraitModelToEntityMapper(getterFunction);

        // execute the SUT
        Optional<LeadershipTrait> result = leadershipTraitModelToEntityMapper.map(leadershipTraitName);

        // validation
        assertThat(result).isNotEmpty();
        assertThat(result.get()).isEqualTo(leadershipTrait);
    }

    @Test
    void testNotFound() {
        // setup the fixture
        String leadershipTraitName = "LEADERSHIP_TRAIT";

        Function<String, Optional<LeadershipTrait>> getterFunction = m -> Optional.empty();
        LeadershipTraitModelToEntityMapper leadershipTraitModelToEntityMapper = new LeadershipTraitModelToEntityMapper(getterFunction);

        // execute the SUT
        Optional<LeadershipTrait> result = leadershipTraitModelToEntityMapper.map(leadershipTraitName);

        // validation
        assertThat(result).isEmpty();
    }

    @Test
    void testNull() {
        // setup the fixture
        Function<String, Optional<LeadershipTrait>> getterFunction = m -> Optional.empty();
        LeadershipTraitModelToEntityMapper leadershipTraitModelToEntityMapper = new LeadershipTraitModelToEntityMapper(getterFunction);

        // execute the SUT
        Optional<LeadershipTrait> result = leadershipTraitModelToEntityMapper.map(null);

        // validation
        assertThat(result).isEmpty();
    }

}
