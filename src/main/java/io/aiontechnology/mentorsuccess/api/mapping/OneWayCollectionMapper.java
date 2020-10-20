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

import java.util.Collection;

/**
 * Mapper interface that converts a collection of objects of type 'FROM' to objects of type 'TO'.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
@FunctionalInterface
public interface OneWayCollectionMapper<FROM, TO> {

    /**
     * Map the collection of 'from' objects to a collection of 'TO' objects.
     *
     * @param from The object to map from.
     * @return The resulting collection of mapped object.
     */
    Collection<TO> map(Collection<FROM> from);

}
