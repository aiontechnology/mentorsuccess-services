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

import io.aiontechnology.mentorsuccess.api.model.PhonogramModel;
import io.aiontechnology.mentorsuccess.api.model.PhonogramModelHolder;
import io.aiontechnology.mentorsuccess.entity.Phonogram;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Mapper between {@link Phonogram} and {@link PhonogramModel}.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@RequiredArgsConstructor
public class PhonogramMapper implements Mapper<Phonogram, PhonogramModel> {

    /** Function for retrieving {@link Phonogram} objects by name */
    private final Function<String, Optional<Phonogram>> phonogramGetter;

    /**
     * Map from the given {@link Phonogram} to a {@link PhonogramModel}.
     *
     * @param phonogram The {@link Phonogram} to map from.
     * @return The newly mapped {@link PhonogramModel}.
     */
    @Override
    public PhonogramModel mapEntityToModel(Phonogram phonogram) {
        return PhonogramModel.builder()
                .withName(phonogram.getName())
                .build();
    }

    /**
     * Map from the given {@link PhonogramModel} to a {@link Phonogram}.
     *
     * @param phonogramModel The {@link PhonogramModel} to map from.
     * @return The newly mapped {@link Phonogram}.
     */
    @Override
    public Phonogram mapModelToEntity(PhonogramModel phonogramModel) {
        Phonogram phonogram = new Phonogram();
        return mapModelToEntity(phonogramModel, phonogram);
    }

    /**
     * Map from a given {@link PhonogramModel} into the given {@link Phonogram}.
     *
     * @param phonogramModel The {@link PhonogramModel} to map from.
     * @param phonogram The {@link Phonogram} to map to.
     * @return The mapped {@link Phonogram}.
     */
    @Override
    public Phonogram mapModelToEntity(PhonogramModel phonogramModel, Phonogram phonogram) {
        return Optional.ofNullable(phonogramModel)
                .map(PhonogramModel::getName)
                .map(name -> this.phonogramGetter.apply(name))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(p -> {
                    phonogram.setId(p.getId());
                    phonogram.setName(p.getName());
                    return phonogram;
                })
                .orElseThrow(() -> new IllegalArgumentException("Illegal phonogram specified"));
    }

    /**
     * Map a collection of {@link Phonogram} to a collection of {@link PhonogramModel}.
     *
     * @param phonogramSupplier A support that provides the collection of {@link Phonogram}.
     * @return The collection of mapped {@link PhonogramModel}.
     */
    public Collection<PhonogramModel> mapPhonograms(Supplier<Collection<Phonogram>> phonogramSupplier) {
        return phonogramSupplier.get().stream()
                .map(this::mapEntityToModel)
                .collect(Collectors.toSet());
    }

    /**
     * Map a collection of {@link PhonogramModel} to a collection of {@link Phonogram}.
     *
     * @param holder The holder containing the collection of {@link PhonogramModel} to map.
     * @return The collection of mapped {@link Phonogram}.
     */
    public Collection<Phonogram> mapPhonograms(PhonogramModelHolder holder) {
        if (holder.getPhonograms() != null) {
            return holder.getPhonograms().stream()
                    .map(this::mapModelToEntity)
                    .collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }

}
