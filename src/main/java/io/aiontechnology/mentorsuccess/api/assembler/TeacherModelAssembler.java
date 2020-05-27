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

import io.aiontechnology.mentorsuccess.api.controller.SchoolController;
import io.aiontechnology.mentorsuccess.api.controller.TeacherController;
import io.aiontechnology.mentorsuccess.api.mapping.ToTeacherModelMapper;
import io.aiontechnology.mentorsuccess.api.model.TeacherModel;
import io.aiontechnology.mentorsuccess.entity.Role;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Assembles {@link TeacherModel} instances from {@link Role} entity instances.
 *
 * @author Whitney Hunter
 */
@Component
public class TeacherModelAssembler extends RepresentationModelAssemblerSupport<Role, TeacherModel> {

    private final LinkHelper<TeacherModel> linkHelper;

    private final ToTeacherModelMapper toTeacherModelMapper;

    /**
     * Construct an instance.
     */
    @Inject
    public TeacherModelAssembler(ToTeacherModelMapper toTeacherModelMapper, LinkHelper<TeacherModel> linkHelper) {
        super(TeacherController.class, TeacherModel.class);
        this.toTeacherModelMapper = toTeacherModelMapper;
        this.linkHelper = linkHelper;
    }

    /**
     * Convert the given {@link Role}.
     *
     * @param role The {@link Role} to convert.
     * @return The {@link TeacherModel} produced from the {@link Role}.
     */
    @Override
    public TeacherModel toModel(Role role) {
        return Optional.ofNullable(role)
                .map(toTeacherModelMapper::map)
                .orElse(null);
    }

    public TeacherModel toModel(Role role, LinkProvider<TeacherModel, Role> linkProvider) {
        return Optional.ofNullable(role)
                .map(this::toModel)
                .map(model -> linkHelper.addLinks(model, linkProvider.apply(model, role)))
                .orElse(null);
    }

}
