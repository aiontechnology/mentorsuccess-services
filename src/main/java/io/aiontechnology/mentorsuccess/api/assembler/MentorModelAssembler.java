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

import io.aiontechnology.mentorsuccess.api.controller.MentorController;
import io.aiontechnology.mentorsuccess.api.mapping.OneWayMapper;
import io.aiontechnology.mentorsuccess.api.model.outbound.OutboundMentorModel;
import io.aiontechnology.mentorsuccess.entity.SchoolPersonRole;
import org.springframework.stereotype.Component;

/**
 * Assembles a {@link OutboundMentorModel} from a {@link SchoolPersonRole}.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
@Component
public class MentorModelAssembler extends BaseRoleModelAssembler<OutboundMentorModel> {

    public MentorModelAssembler(OneWayMapper<SchoolPersonRole, OutboundMentorModel> mapper,
            LinkHelper<OutboundMentorModel> linkHelper) {
        super(MentorController.class, OutboundMentorModel.class, mapper, linkHelper);
    }

}
