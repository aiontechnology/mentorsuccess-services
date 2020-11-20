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

import io.aiontechnology.mentorsuccess.api.mapping.tomodel.reference.InterestEntityToModelMapper;
import io.aiontechnology.mentorsuccess.entity.reference.Interest;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link InterestEntityToModelMapper}.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
public class InterestModelToEntityMapperTest {

    @Test
    void testMapping() {
        // setup the fixture
        Interest interest = new Interest();
        UUID interestId = UUID.randomUUID();
        interest.setId(interestId);
        String interestName = "INTEREST";
        interest.setName(interestName);

        Function<String, Optional<Interest>> getterFunction = m -> Optional.of(interest);
        InterestModelToEntityMapper interestModelToEntityMapper = new InterestModelToEntityMapper(getterFunction);

        // execute the SUT
        Optional<Interest> result = interestModelToEntityMapper.map(interestName);

        // validation
        assertThat(result).isNotEmpty();
        assertThat(result.get()).isEqualTo(interest);
    }

    @Test
    void testNotFound() {
        // setup the fixture
        String interestName = "INTEREST";

        Function<String, Optional<Interest>> getterFunction = m -> Optional.empty();
        InterestModelToEntityMapper interestModelToEntityMapper = new InterestModelToEntityMapper(getterFunction);

        // execute the SUT
        Optional<Interest> result = interestModelToEntityMapper.map(interestName);

        // validation
        assertThat(result).isEmpty();
    }

    @Test
    void testNull() {
        // setup the fixture
        Function<String, Optional<Interest>> getterFunction = m -> Optional.empty();
        InterestModelToEntityMapper interestModelToEntityMapper = new InterestModelToEntityMapper(getterFunction);

        // execute the SUT
        Optional<Interest> result = interestModelToEntityMapper.map(null);

        // validation
        assertThat(result).isEmpty();
    }

}
