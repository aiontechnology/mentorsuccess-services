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

import io.aiontechnology.mentorsuccess.api.model.inbound.ActivityFocusModel;
import io.aiontechnology.mentorsuccess.entity.ActivityFocus;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link ActivityFocusModelToEntityMapper}.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
public class ActivityFocusModelToEntityMapperTest {

    @Test
    void testMapping() {
        // setup the fixture
        ActivityFocus activityFocus = new ActivityFocus();
        UUID activityFocusId = UUID.randomUUID();
        activityFocus.setId(activityFocusId);
        String activityFocusName = "ACTIVITY_FOCUS";
        activityFocus.setName(activityFocusName);

        ActivityFocusModel activityFocusModel = ActivityFocusModel.builder()
                .withName(activityFocusName)
                .build();

        Function<String, Optional<ActivityFocus>> getterFunction = m -> Optional.of(activityFocus);
        ActivityFocusModelToEntityMapper activityFocusModelToEntityMapper = new ActivityFocusModelToEntityMapper(getterFunction);

        // execute the SUT
        Optional<ActivityFocus> result = activityFocusModelToEntityMapper.map(activityFocusModel);

        // validation
        assertThat(result).isNotEmpty();
        assertThat(result.get()).isEqualTo(activityFocus);
    }

    @Test
    void testNotFound() {
        // setup the fixture
        String activityFocusName = "ACTIVITY_FOCUS";
        ActivityFocusModel activityFocusModel = ActivityFocusModel.builder()
                .withName(activityFocusName)
                .build();

        Function<String, Optional<ActivityFocus>> getterFunction = m -> Optional.empty();
        ActivityFocusModelToEntityMapper activityFocusModelToEntityMapper = new ActivityFocusModelToEntityMapper(getterFunction);

        // execute the SUT
        Optional<ActivityFocus> result = activityFocusModelToEntityMapper.map(activityFocusModel);

        // validation
        assertThat(result).isEmpty();
    }

    @Test
    void testNull() {
        // setup the fixture
        Function<String, Optional<ActivityFocus>> getterFunction = m -> Optional.empty();
        ActivityFocusModelToEntityMapper activityFocusModelToEntityMapper = new ActivityFocusModelToEntityMapper(getterFunction);

        // execute the SUT
        Optional<ActivityFocus> result = activityFocusModelToEntityMapper.map(null);

        // validation
        assertThat(result).isEmpty();
    }

}
