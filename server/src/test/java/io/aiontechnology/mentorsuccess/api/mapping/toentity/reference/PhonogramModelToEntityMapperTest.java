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

import io.aiontechnology.mentorsuccess.entity.reference.Phonogram;
import io.aiontechnology.mentorsuccess.model.inbound.reference.InboundPhonogramModel;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link PhonogramModelToEntityMapper}.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
public class PhonogramModelToEntityMapperTest {

    @Test
    void testMapping() {
        // setup the fixture
        Phonogram phonogram = new Phonogram();
        UUID phonogramId = UUID.randomUUID();
        phonogram.setId(phonogramId);
        String phonogramName = "PHONOGRAM";
        phonogram.setName(phonogramName);

        InboundPhonogramModel inboundPhonogramModel = InboundPhonogramModel.builder()
                .withName(phonogramName)
                .build();

        Function<String, Optional<Phonogram>> getterFunction = m -> Optional.of(phonogram);
        PhonogramModelToEntityMapper phonogramModelToEntityMapper = new PhonogramModelToEntityMapper(getterFunction);

        // execute the SUT
        Optional<Phonogram> result = phonogramModelToEntityMapper.map(inboundPhonogramModel);

        // validation
        assertThat(result).isNotEmpty();
        assertThat(result.get()).isEqualTo(phonogram);
    }

    @Test
    void testNotFound() {
        // setup the fixture
        String phonogramName = "PHONOGRAM";
        InboundPhonogramModel inboundPhonogramModel = InboundPhonogramModel.builder()
                .withName(phonogramName)
                .build();

        Function<String, Optional<Phonogram>> getterFunction = m -> Optional.empty();
        PhonogramModelToEntityMapper phonogramModelToEntityMapper = new PhonogramModelToEntityMapper(getterFunction);

        // execute the SUT
        Optional<Phonogram> result = phonogramModelToEntityMapper.map(inboundPhonogramModel);

        // validation
        assertThat(result).isEmpty();
    }

    @Test
    void testNull() {
        // setup the fixture
        Function<String, Optional<Phonogram>> getterFunction = m -> Optional.empty();
        PhonogramModelToEntityMapper phonogramModelToEntityMapper = new PhonogramModelToEntityMapper(getterFunction);

        // execute the SUT
        Optional<Phonogram> result = phonogramModelToEntityMapper.map(null);

        // validation
        assertThat(result).isEmpty();
    }

}
