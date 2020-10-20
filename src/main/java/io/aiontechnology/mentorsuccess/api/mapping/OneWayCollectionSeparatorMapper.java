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

import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Separate the given collection of elements into a map of lists using the given function.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
@RequiredArgsConstructor
public class OneWayCollectionSeparatorMapper<KEY_TYPE, VALUE_TYPE> {

    /** A function that is used to obtain a KEY_TYPE from the given VALUE_TYPE */
    private final Function<VALUE_TYPE, KEY_TYPE> keyFunction;

    /**
     * Split the given collection into a map of lists using the {@link #keyFunction}.
     *
     * @param collection The collection to split
     * @return The separated map.
     */
    public Map<KEY_TYPE, List<VALUE_TYPE>> map(Collection<VALUE_TYPE> collection) {
        return collection.stream().collect(Collectors.groupingBy(keyFunction::apply));

    }

}
