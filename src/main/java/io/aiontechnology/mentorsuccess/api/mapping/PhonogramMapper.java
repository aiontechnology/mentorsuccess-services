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

import io.aiontechnology.mentorsuccess.api.model.InterestModel;
import io.aiontechnology.mentorsuccess.api.model.PhonogramModel;
import io.aiontechnology.mentorsuccess.api.model.PhonogramModelHolder;
import io.aiontechnology.mentorsuccess.entity.Interest;
import io.aiontechnology.mentorsuccess.entity.Phonogram;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Mapper between {@link Phonogram} and {@link PhonogramModel}.
 *
 * @author <a href="mailto:whitney@aiontechnology.io">Whitney Hunter</a>
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class PhonogramMapper implements Mapper<Phonogram, PhonogramModel> {

    private final Function<String, Optional<Phonogram>> phonogramGetter;

    @Override
    public PhonogramModel mapEntityToModel(Phonogram phonogram) {
        return PhonogramModel.builder()
                .withName(phonogram.getName())
                .build();
    }

    @Override
    public Phonogram mapModelToEntity(PhonogramModel phonogramModel) {
        Phonogram phonogram = new Phonogram();
        return mapModelToEntity(phonogramModel, phonogram);
    }

    @Override
    public Phonogram mapModelToEntity(PhonogramModel phonogramModel, Phonogram phonogram) {
        Phonogram phonogram1 = Optional.ofNullable(phonogramModel)
                .map(PhonogramModel::getName)
                .map(name -> this.phonogramGetter.apply(name))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .orElse(null);
        phonogram.setId(phonogram1.getId());
        phonogram.setName(phonogram1.getName());
        return phonogram;
    }

    public Set<PhonogramModel> mapPhonograms(Supplier<Set<Phonogram>> phonogramSupplier) {
        return phonogramSupplier.get().stream()
                .map(this::mapEntityToModel)
                .collect(Collectors.toSet());
    }

    public Set<Phonogram> mapPhonograms(PhonogramModelHolder holder) {
        if (holder.getPhonograms() != null) {
            return holder.getPhonograms().stream()
                    .map(this::mapModelToEntity)
                    .collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }

}
