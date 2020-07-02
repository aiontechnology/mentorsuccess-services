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

import io.aiontechnology.mentorsuccess.api.model.InterestModel;
import io.aiontechnology.mentorsuccess.entity.Interest;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link InterestMapper}
 *
 * @author <a href="mailto:whitney@aiontechnology.io">Whitney Hunter</a>
 * @since 1.0.0
 */
public class InterestMapperTest {

    private static final UUID ID = UUID.randomUUID();
    private static final String NAME = "NAME";

    @Test
    void testMapEntityToModel() throws Exception {
        // setup the fixture
        Interest interest = new Interest();
        interest.setId(ID);
        interest.setName(NAME);

        InterestMapper interestMapper = new InterestMapper(null);

        // execute the SUT
        InterestModel result = interestMapper.mapEntityToModel(interest);

        // validation
        assertThat(result.getName()).isEqualTo(NAME);
    }

    @Test
    void testMapModelToEntity_newEntity() throws Exception {
        // setup the fixture
        InterestModel interestModel = InterestModel.builder()
                .withName(NAME)
                .build();
        Interest interest = new Interest();
        interest.setName(NAME);

        Function<String, Optional<Interest>> interestGetter = name -> Optional.of(interest);
        InterestMapper interestMapper = new InterestMapper(interestGetter);

        // execute the SUT
        Interest result = interestMapper.mapModelToEntity(interestModel);

        // validation
        assertThat(result).isEqualTo(interest);
    }

    @Test
    void testMapModelToEntity_providedEntity() throws Exception {
        // setup the fixture
        InterestModel interestModel = InterestModel.builder()
                .withName(NAME)
                .build();
        Interest interest = new Interest();
        interest.setName(NAME);

        Function<String, Optional<Interest>> interestGetter = name -> Optional.of(interest);
        InterestMapper interestMapper = new InterestMapper(interestGetter);

        Interest providedInterest = new Interest();

        // execute the SUT
        Interest result = interestMapper.mapModelToEntity(interestModel, providedInterest);

        // validation
        assertThat(result).isEqualTo(interest);
        assertThat(result).isSameAs(providedInterest);
    }

}
