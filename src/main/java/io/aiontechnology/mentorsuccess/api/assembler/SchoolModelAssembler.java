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
import io.aiontechnology.mentorsuccess.api.mapping.ToSchoolModelMapper;
import io.aiontechnology.mentorsuccess.api.model.SchoolModel;
import io.aiontechnology.mentorsuccess.entity.School;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Assembles {@link SchoolModel} instances from {@link School} entity instances.
 *
 * @author Whitney Hunter
 */
@Component
public class SchoolModelAssembler extends RepresentationModelAssemblerSupport<School, SchoolModel> {

    private final LinkHelper<SchoolModel> linkHelper;

    /** Mapper to map from {@link School} to {@link SchoolModel} */
    private final ToSchoolModelMapper toSchoolModelMapper;

    /**
     * Constructor
     */
    @Inject
    public SchoolModelAssembler(ToSchoolModelMapper toSchoolModelMapper, LinkHelper<SchoolModel> linkHelper) {
        super(SchoolController.class, SchoolModel.class);
        this.toSchoolModelMapper = toSchoolModelMapper;
        this.linkHelper = linkHelper;
    }

    /**
     * Create a {@link SchoolModel} instance from the given {@link School} entity instance.
     *
     * @param school The {@link School} entity to assemble.
     * @return The assembled {@link SchoolModel}.
     */
    @Override
    public SchoolModel toModel(School school) {
        return Optional.ofNullable(school)
                .map(toSchoolModelMapper::map)
                .orElse(null);
    }

    public SchoolModel toModel(School school, LinkProvider<SchoolModel, School> linkProvider) {
        return Optional.ofNullable(school)
                .map(this::toModel)
                .map(model -> linkHelper.addLinks(model, linkProvider.apply(model, school)))
                .orElse(null);
    }

}
