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

import io.aiontechnology.mentorsuccess.api.model.LeadershipTraitModel;
import io.aiontechnology.mentorsuccess.entity.LeadershipTrait;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link LeadershipTraitMapper}.
 *
 * @author <a href="mailto:whitney@aiontechnology.io">Whitney Hunter</a>
 * @since 1.0.0
 */
public class LeadershipTraitMapperTest {

    private static final UUID ID = UUID.randomUUID();
    private static final String NAME = "NAME";

    @Test
    void testMapEntityToModel() throws Exception {
        // setup the fixture
        LeadershipTrait leadershipTrait = new LeadershipTrait();
        leadershipTrait.setId(ID);
        leadershipTrait.setName(NAME);

        LeadershipTraitMapper leadershipTraitMapper = new LeadershipTraitMapper(null);

        // execute the SUT
        LeadershipTraitModel result = leadershipTraitMapper.mapEntityToModel(leadershipTrait);

        // validation
        assertThat(result.getName()).isEqualTo(NAME);
    }

    @Test
    void testMapModelToEntity_newEntity() throws Exception {
        // setup the fixture
        LeadershipTraitModel leadershipTraitModel = LeadershipTraitModel.builder()
                .withName(NAME)
                .build();
        LeadershipTrait leadershipTrait = new LeadershipTrait();
        leadershipTrait.setName(NAME);

        Function<String, Optional<LeadershipTrait>> leadershipTraitGetter = name -> Optional.of(leadershipTrait);
        LeadershipTraitMapper leadershipTraitMapper = new LeadershipTraitMapper(leadershipTraitGetter);

        // execute the SUT
        LeadershipTrait result = leadershipTraitMapper.mapModelToEntity(leadershipTraitModel);

        // validation
        assertThat(result).isEqualTo(leadershipTrait);
    }

    @Test
    void testMapModelToEntity_providedEntity() throws Exception {
        // setup the fixture
        LeadershipTraitModel leadershipTraitModel = LeadershipTraitModel.builder()
                .withName(NAME)
                .build();
        LeadershipTrait leadershipTrait = new LeadershipTrait();
        leadershipTrait.setName(NAME);

        Function<String, Optional<LeadershipTrait>> leadershipTraitGetter = name -> Optional.of(leadershipTrait);
        LeadershipTraitMapper leadershipTraitMapper = new LeadershipTraitMapper(leadershipTraitGetter);

        LeadershipTrait providedLeadershipTrait = new LeadershipTrait();

        // execute the SUT
        LeadershipTrait result = leadershipTraitMapper.mapModelToEntity(leadershipTraitModel, providedLeadershipTrait);

        // validation
        assertThat(result).isEqualTo(leadershipTrait);
        assertThat(result).isSameAs(providedLeadershipTrait);
    }

}
