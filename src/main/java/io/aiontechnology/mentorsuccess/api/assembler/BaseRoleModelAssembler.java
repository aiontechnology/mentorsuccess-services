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

import io.aiontechnology.mentorsuccess.api.mapping.Mapper;
import io.aiontechnology.mentorsuccess.api.model.TeacherModel;
import io.aiontechnology.mentorsuccess.entity.Role;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import java.util.Optional;

/**
 * An abstract class that provides common functionality for role based assemblers.
 *
 * @author <a href="mailto:whitney@aiontechnology.io">Whitney Hunter</a>
 * @since 1.0.0
 */
public abstract class BaseRoleModelAssembler<T extends RepresentationModel<T>>
        extends RepresentationModelAssemblerSupport<Role, T> {

    private final LinkHelper<T> linkHelper;

    private final Mapper<Role, T> mapper;

    /**
     * Construct an instance.
     */
    public BaseRoleModelAssembler(Class<?> controllerClass, Class<T> resourceType, Mapper<Role, T> mapper,
                                  LinkHelper<T> linkHelper) {
        super(controllerClass, resourceType);
        this.mapper = mapper;
        this.linkHelper = linkHelper;
    }

    /**
     * Convert the given {@link Role}.
     *
     * @param role The {@link Role} to convert.
     * @return The {@link TeacherModel} produced from the {@link Role}.
     */
    @Override
    public T toModel(Role role) {
        return Optional.ofNullable(role)
                .map(mapper::mapEntityToModel)
                .orElse(null);
    }

    public T toModel(Role role, LinkProvider<T, Role> linkProvider) {
        return Optional.ofNullable(role)
                .map(this::toModel)
                .map(model -> linkHelper.addLinks(model, linkProvider.apply(model, role)))
                .orElse(null);
    }

}
