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

package io.aiontechnology.mentorsuccess.api.assembler;

import io.aiontechnology.mentorsuccess.api.mapping.OneWayMapper;
import io.aiontechnology.mentorsuccess.api.model.inbound.ActivityFocusModel;
import io.aiontechnology.mentorsuccess.entity.ActivityFocus;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests fpr {@link ActivityFocusModelAssembler}.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
public class ActivityFocusModelAssemblerTest {

    @Test
    void testToModel() throws Exception {
        // setup the fixture
        ActivityFocus activityFocus = new ActivityFocus();

        OneWayMapper<ActivityFocus, ActivityFocusModel> activityFocusMapper = mock(OneWayMapper.class);
        ActivityFocusModel activityFocusModel = mock(ActivityFocusModel.class);
        when(activityFocusMapper.map(activityFocus)).thenReturn(Optional.of(activityFocusModel));

        ActivityFocusModelAssembler assembler = new ActivityFocusModelAssembler(activityFocusMapper);

        // execute the SUT
        ActivityFocusModel result = assembler.toModel(activityFocus);

        // validation
        assertThat(result).isSameAs(activityFocusModel);
    }

    @Test
    void testToModel_nullActivityFocus() throws Exception {
        // setup the fixture
        ActivityFocus activityFocus = null;

        OneWayMapper<ActivityFocus, ActivityFocusModel> activityFocusMapper = mock(OneWayMapper.class);
        ActivityFocusModel activityFocusModel = mock(ActivityFocusModel.class);
        when(activityFocusMapper.map(activityFocus)).thenReturn(Optional.of(activityFocusModel));

        ActivityFocusModelAssembler assembler = new ActivityFocusModelAssembler(activityFocusMapper);

        // execute the SUT
        ActivityFocusModel result = assembler.toModel(activityFocus);

        // validation
        assertThat(result).isNull();
    }

}
