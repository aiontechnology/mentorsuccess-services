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

package io.aiontechnology.mentorsuccess.api.mapping.tomodel.reference;

import io.aiontechnology.mentorsuccess.api.model.inbound.reference.BehaviorModel;
import io.aiontechnology.mentorsuccess.entity.reference.Behavior;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link BehaviorEntityToModelMapper}.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
public class BehaviorEntityToModelMapperTest {

    @Test
    void testMapping() throws Exception {
        // setup the fixture
        UUID id = UUID.randomUUID();
        String name = "TEST";
        Behavior behavior = new Behavior();
        behavior.setId(id);
        behavior.setName(name);

        BehaviorEntityToModelMapper behaviorEntityToModelMapper = new BehaviorEntityToModelMapper();

        // execute the SUT
        Optional<BehaviorModel> result = behaviorEntityToModelMapper.map(behavior);

        // validation
        assertThat(result).isNotEmpty();
        assertThat(result.get().getName()).isEqualTo(name);
    }

    @Test
    void testNull() throws Exception {
        // setup the fixture
        BehaviorEntityToModelMapper behaviorEntityToModelMapper = new BehaviorEntityToModelMapper();

        // execute the SUT
        Optional<BehaviorModel> result = behaviorEntityToModelMapper.map(null);

        // validation
        assertThat(result).isEmpty();
    }

}
