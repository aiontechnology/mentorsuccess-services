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

import io.aiontechnology.mentorsuccess.entity.reference.Behavior;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link BehaviorModelToEntityMapper}.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
public class BehaviorModelToEntityMapperTest {

    @Test
    void testMapping() {
        // setup the fixture
        Behavior behavior = new Behavior();
        UUID behaviorId = UUID.randomUUID();
        behavior.setId(behaviorId);
        String behaviorName = "BEHAVIOR";
        behavior.setName(behaviorName);

        Function<String, Optional<Behavior>> getterFunction = m -> Optional.of(behavior);
        BehaviorModelToEntityMapper behaviorModelToEntityMapper = new BehaviorModelToEntityMapper(getterFunction);

        // execute the SUT
        Optional<Behavior> result = behaviorModelToEntityMapper.map(behaviorName);

        // validation
        assertThat(result).isNotEmpty();
        assertThat(result.get()).isEqualTo(behavior);
    }

    @Test
    void testNotFound() {
        // setup the fixture
        String behaviorName = "BEHAVIOR";

        Function<String, Optional<Behavior>> getterFunction = m -> Optional.empty();
        BehaviorModelToEntityMapper behaviorModelToEntityMapper = new BehaviorModelToEntityMapper(getterFunction);

        // execute the SUT
        Optional<Behavior> result = behaviorModelToEntityMapper.map(behaviorName);

        // validation
        assertThat(result).isEmpty();
    }

    @Test
    void testNull() {
        // setup the fixture
        Function<String, Optional<Behavior>> getterFunction = m -> Optional.empty();
        BehaviorModelToEntityMapper behaviorModelToEntityMapper = new BehaviorModelToEntityMapper(getterFunction);

        // execute the SUT
        Optional<Behavior> result = behaviorModelToEntityMapper.map(null);

        // validation
        assertThat(result).isEmpty();
    }

}
