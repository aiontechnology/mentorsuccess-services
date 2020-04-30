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
 * Interface for all mapper classes.
 *
 * @param <F> The class of the object to map from.
 * @param <T> The class of the object to map from.
 * @author Whitney Hunter
 */
public interface MutableMapper<F, T> {

    /**
     * Create new object and map the given object to it.
     *
     * @param from The object from which to map.
     * @return The created object which was mapped to.
     */
    T map(F from);

    /**
     * Update the given 'to' object from the given 'from' object.
     *
     * @param from The object from which to map.
     * @param to The updated object which is to be mapped to.
     * @return The updated object.
     */
    T map(F from, T to);

}
