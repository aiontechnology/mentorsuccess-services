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

import io.aiontechnology.mentorsuccess.entity.School;

import java.util.Optional;

/**
 * @author Whitney Hunter
 * @since 0.3.0
 */
@FunctionalInterface
public interface OneWayBiMapper<FROM1, FROM2, TO> {

    /**
     * Map the 'from' object to the 'TO' type.
     *
     * @param from1 The first object to map from.
     * @param from2 The second object to map from.
     * @return The resulting mapped object.
     */
    Optional<TO> map(FROM1 from1, FROM2 from2);

}
