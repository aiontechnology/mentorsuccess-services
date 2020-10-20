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
import io.aiontechnology.mentorsuccess.api.mapping.OneWayMapper;
import io.aiontechnology.mentorsuccess.api.model.inbound.SchoolModel;
import io.aiontechnology.mentorsuccess.entity.School;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Assembles a {@link SchoolModel} from a {@link School}.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@Component
public class SchoolModelAssembler extends RepresentationModelAssemblerSupport<School, SchoolModel> {

    /** A utility class for adding links to a model object. */
    private final LinkHelper<SchoolModel> linkHelper;

    /** Mapper to map between {@link School} and {@link SchoolModel}. */
    private final OneWayMapper<School, SchoolModel> mapper;

    /**
     * Constructor
     *
     * @param mapper The mapper for mapping between {@link School} and {@link SchoolModel}.
     * @param linkHelper A utility class for adding links to a model object.
     */
    @Inject
    public SchoolModelAssembler(OneWayMapper<School, SchoolModel> mapper, LinkHelper<SchoolModel> linkHelper) {
        super(SchoolController.class, SchoolModel.class);
        this.mapper = mapper;
        this.linkHelper = linkHelper;
    }

    /**
     * Map a {@link School} to a {@link SchoolModel} without adding links.
     *
     * @param school The {@link School} to map.
     * @return The resulting {@link SchoolModel}.
     */
    @Override
    public SchoolModel toModel(School school) {
        return Optional.ofNullable(school)
                .flatMap(mapper::map)
                .orElse(null);
    }

    /**
     * Map a {@link School} to a {@link SchoolModel} and add links.
     *
     * @param school The {@link School} to map.
     * @param linkProvider An object that provides links.
     * @return The resulting {@link SchoolModel}.
     */
    public SchoolModel toModel(School school, LinkProvider<SchoolModel, School> linkProvider) {
        return Optional.ofNullable(school)
                .map(this::toModel)
                .map(model -> linkHelper.addLinks(model, linkProvider.apply(model, school)))
                .orElse(null);
    }

}
