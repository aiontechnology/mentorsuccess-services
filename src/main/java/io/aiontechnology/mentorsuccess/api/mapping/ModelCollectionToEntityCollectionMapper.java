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

import io.aiontechnology.mentorsuccess.api.mapping.OneWayCollectionMapper;
import io.aiontechnology.mentorsuccess.api.mapping.OneWayMapper;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Whitney Hunter
 * @since 0.3.0
 */
@RequiredArgsConstructor
public class ModelCollectionToEntityCollectionMapper<FROM, TO> implements OneWayCollectionMapper<FROM, TO> {

    private final OneWayMapper<FROM, TO> mapper;

    @Override
    public Collection<TO> map(Collection<FROM> from) {
        if (from == null) {
            return Collections.EMPTY_LIST;
        }
        return from.stream()
                .map(mapper::map)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toCollection(ArrayList::new));
    }

}
