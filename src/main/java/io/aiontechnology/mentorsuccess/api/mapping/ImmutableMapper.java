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
 * Interface for mapping objects of two different classes. The input value should be treated as immutable.
 *
 * @param <F> The class of the object to map from.
 * @param <T> The class of the object to map from.
 * @author Whitney Hunter
 */
public interface ImmutableMapper<F, T> {

    /**
     * Create new object and map the given object to it.
     *
     * @param from The object from which to map.
     * @return The created object which was mapped to.
     */
    T map(F from);

}
