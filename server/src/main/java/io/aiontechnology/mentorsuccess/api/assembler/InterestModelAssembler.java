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

import io.aiontechnology.mentorsuccess.api.mapping.tomodel.reference.InterestEntityToModelMapper;
import io.aiontechnology.mentorsuccess.entity.reference.Interest;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Assembles an interest string from an {@link Interest}.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@Component
public class InterestModelAssembler {

    /** Mapper to map between {@link Interest} and an interest string. */
    private final InterestEntityToModelMapper mapper;

    /**
     * Constructor
     *
     * @param mapper The mapper for mapping between {@link Interest} and an interest string.
     */
    @Inject
    public InterestModelAssembler(InterestEntityToModelMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Map an {@link Interest} to an interest string.
     *
     * @param interest The {@link Interest} to map.
     * @return The resulting interest string.
     */
    public String toModel(Interest interest) {
        return Optional.ofNullable(interest)
                .flatMap(mapper::map)
                .orElse(null);
    }

}
