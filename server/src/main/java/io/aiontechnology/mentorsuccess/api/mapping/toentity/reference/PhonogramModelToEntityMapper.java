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

package io.aiontechnology.mentorsuccess.api.mapping.toentity.reference;

import io.aiontechnology.atlas.mapping.OneWayMapper;
import io.aiontechnology.mentorsuccess.entity.reference.Phonogram;
import io.aiontechnology.mentorsuccess.model.inbound.reference.InboundPhonogramModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Function;

/**
 * Mapper that converts a {@link InboundPhonogramModel} to a {@link Phonogram}.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
@Component
@RequiredArgsConstructor
public class PhonogramModelToEntityMapper implements OneWayMapper<InboundPhonogramModel, Phonogram> {

    /** Function that retrieves a {@link Phonogram} by its name */
    private final Function<String, Optional<Phonogram>> getter;

    /**
     * Map the given {@link InboundPhonogramModel}
     *
     * @param inboundPhonogramModel The {@link InboundPhonogramModel} to map.
     * @return The resulting {@link Phonogram}.
     */
    @Override
    public Optional<Phonogram> map(InboundPhonogramModel inboundPhonogramModel) {
        return Optional.ofNullable(inboundPhonogramModel)
                .map(InboundPhonogramModel::getName)
                .flatMap(getter::apply);
    }

}
