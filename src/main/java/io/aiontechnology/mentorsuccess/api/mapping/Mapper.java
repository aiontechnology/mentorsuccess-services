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

package io.aiontechnology.mentorsuccess.api.mapping;

/**
 * Provides mapping services between entity and model objects.
 *
 * @param <Entity> The entity type.
 * @param <Model> The model type.
 *
 * @author <a href="mailto:whitney@aiontechnology.io">Whitney Hunter</a>
 * @since v1.0
 */
public interface Mapper<Entity, Model> {

    /**
     * Map from an entity object to a model object. A new model object is created.
     *
     * @param entity The entity to map from.
     * @return The model object that results from the mapping.
     */
    Model mapEntityToModel(Entity entity);

    /**
     * Map from a model object to an entity object. A new entity object is created.
     *
     * @param model The model to map from.
     * @return The entity object that results from the mapping.
     */
    Entity mapModelToEntity(Model model);

    /**
     * Map from a model object to an entity object. The entity object is provided.
     *
     * @param model The model to map from.
     * @param entity The entity to map to.
     * @return The entity object that results from the mapping.
     */
    Entity mapModelToEntity(Model model, Entity entity);

}
