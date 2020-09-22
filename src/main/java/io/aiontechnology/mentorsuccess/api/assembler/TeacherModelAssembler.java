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

import io.aiontechnology.mentorsuccess.api.controller.TeacherController;
import io.aiontechnology.mentorsuccess.api.mapping.TeacherMapper;
import io.aiontechnology.mentorsuccess.api.model.TeacherModel;
import io.aiontechnology.mentorsuccess.entity.Role;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Assembles a {@link TeacherModel} from a {@link Role}.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@Component
public class TeacherModelAssembler extends BaseRoleModelAssembler<TeacherModel> {

    /**
     * Constructor
     *
     * @param teacherMapper The mapper for mapping between {@link Role} and {@link TeacherModel}.
     * @param linkHelper A utility class for adding links to a model object.
     */
    @Inject
    public TeacherModelAssembler(TeacherMapper teacherMapper, LinkHelper<TeacherModel> linkHelper) {
        super(TeacherController.class, TeacherModel.class, teacherMapper, linkHelper);
    }

}
