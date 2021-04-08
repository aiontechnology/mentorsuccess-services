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

package io.aiontechnology.mentorsuccess.api.mapping.toentity.reference;

import io.aiontechnology.atlas.mapping.OneWayMapper;
import io.aiontechnology.mentorsuccess.entity.reference.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Function;

/**
 * Mapper that converts a tag string to a {@link Tag}.
 *
 * @author Whitney Hunter
 * @since 1.13.0
 */
@Component
@RequiredArgsConstructor
public class TagModelToEntityMapper implements OneWayMapper<String, Tag> {

    /** Function that retrieves a {@link Tag} by its name */
    private final Function<String, Optional<Tag>> getter;

    /**
     * Map the given tag string.
     *
     * @param inboundTag The tag string to map.
     * @return The resulting {@link Tag}.
     */
    @Override
    public Optional<Tag> map(String inboundTag) {
        return Optional.ofNullable(inboundTag)
                .flatMap(getter::apply);
    }

}
