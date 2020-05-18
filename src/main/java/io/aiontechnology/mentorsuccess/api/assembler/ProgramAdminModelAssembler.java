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

import io.aiontechnology.mentorsuccess.api.controller.ProgramAdminController;
import io.aiontechnology.mentorsuccess.api.mapping.ToProgramAdminModelMapper;
import io.aiontechnology.mentorsuccess.api.model.ProgramAdminModel;
import io.aiontechnology.mentorsuccess.entity.Role;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Whitney Hunter
 */
@Component
public class ProgramAdminModelAssembler extends RepresentationModelAssemblerSupport<Role, ProgramAdminModel> {

    private final LinkHelper<ProgramAdminModel> linkHelper;

    private final ToProgramAdminModelMapper toProgramAdminModelMapper;

    ProgramAdminModelAssembler(ToProgramAdminModelMapper toProgramAdminModelMapper, LinkHelper<ProgramAdminModel> linkHelper) {
        super(ProgramAdminController.class, ProgramAdminModel.class);
        this.toProgramAdminModelMapper = toProgramAdminModelMapper;
        this.linkHelper = linkHelper;
    }

    @Override
    public ProgramAdminModel toModel(Role role) {
        return Optional.ofNullable(role)
                .map(toProgramAdminModelMapper::map)
                .orElse(null);
    }

    public ProgramAdminModel toModel(Role role, LinkProvider<ProgramAdminModel, Role> linkProvider) {
        return Optional.ofNullable(role)
                .map(this::toModel)
                .map(model -> linkHelper.addLinks(model, linkProvider.apply(model, role)))
                .orElse(null);
    }

}
