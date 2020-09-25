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
import io.aiontechnology.mentorsuccess.entity.SchoolPersonRole;
import org.springframework.hateoas.RepresentationModel;

import java.util.Optional;

/**
 * An abstract class that provides common functionality for role based assemblers.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
public abstract class BaseRoleModelAssembler<T extends RepresentationModel<T>>
        extends LinkAddingRepresentationModelAssemblerSupport<SchoolPersonRole, T> {

    /** Mapper to convert from a {@link SchoolPersonRole} to a T */
    private final OneWayMapper<SchoolPersonRole, T> mapper;

    /**
     * Construct an instance.
     */
    public BaseRoleModelAssembler(Class<?> controllerClass, Class<T> resourceType, OneWayMapper<SchoolPersonRole, T> mapper,
            LinkHelper<T> linkHelper) {
        super(controllerClass, resourceType, linkHelper);
        this.mapper = mapper;
    }

    /**
     * Convert the given {@link SchoolPersonRole}.
     *
     * @param role The {@link SchoolPersonRole} to convert.
     * @return The model produced from the {@link SchoolPersonRole}.
     */
    @Override
    public T toModel(SchoolPersonRole role) {
        return Optional.ofNullable(role)
                .flatMap(mapper::map)
                .orElse(null);
    }

    /**
     * Convert the given {@link SchoolPersonRole} and add links using the given {@link LinkProvider}.
     *
     * @param role The {@link SchoolPersonRole} to convert.
     * @param linkProvider The {@link LinkProvider} to use to add links to the model.
     * @return The model produced from the {@link SchoolPersonRole}.
     */
    @Override
    public T toModel(SchoolPersonRole role, LinkProvider<T, SchoolPersonRole> linkProvider) {
        return Optional.ofNullable(role)
                .map(this::toModel)
                .map(model -> getLinkHelper().addLinks(model, linkProvider.apply(model, role)))
                .orElse(null);
    }

}
