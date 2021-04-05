/*
 * Copyright 2021 Aion Technology LLC
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.aiontechnology.mentorsuccess.api.assembler;

import io.aiontechnology.atlas.mapping.OneWayMapper;
import io.aiontechnology.mentorsuccess.entity.reference.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Assembles a tag string from a {@link Tag}.
 *
 * @author Whitney Hunter
 * @since 1.13.0
 */
@Component
@RequiredArgsConstructor
public class TagModelAssembler {

    /** Mapper to map between {@link Tag} and tag string. */
    private final OneWayMapper<Tag, String> mapper;

    /**
     * Map a {@link Tag} to a tag string.
     *
     * @param tag The {@link Tag} to map.
     * @return The resulting tag string.
     */
    public String toModel(Tag tag) {
        return Optional.ofNullable(tag)
                .flatMap(mapper::map)
                .orElse(null);
    }

}
