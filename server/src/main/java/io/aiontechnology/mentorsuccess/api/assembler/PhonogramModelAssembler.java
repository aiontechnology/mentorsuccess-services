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

import io.aiontechnology.atlas.mapping.OneWayMapper;
import io.aiontechnology.mentorsuccess.entity.reference.Phonogram;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Assembles a phonogram string from a {@link Phonogram}.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@Component
public class PhonogramModelAssembler {

    /** Mapper to map between {@link Phonogram} and a phonogram string. */
    private final OneWayMapper<Phonogram, String> mapper;

    /**
     * Constructor
     *
     * @param mapper The mapper for mapping between {@link Phonogram} and a phonogram string.
     */
    @Inject
    public PhonogramModelAssembler(OneWayMapper<Phonogram, String> mapper) {
        this.mapper = mapper;
    }

    /**
     * Map a {@link Phonogram} to a phonogram string.
     *
     * @param phonogram The {@link Phonogram} to map.
     * @return The resulting phonogram string.
     */
    public String toModel(Phonogram phonogram) {
        return Optional.ofNullable(phonogram)
                .flatMap(mapper::map)
                .orElse(null);
    }

}
