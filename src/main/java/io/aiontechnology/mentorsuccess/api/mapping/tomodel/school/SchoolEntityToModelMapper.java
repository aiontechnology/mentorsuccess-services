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

package io.aiontechnology.mentorsuccess.api.mapping.tomodel.school;

import io.aiontechnology.mentorsuccess.api.mapping.OneWayMapper;
import io.aiontechnology.mentorsuccess.api.model.inbound.AddressModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.SchoolModel;
import io.aiontechnology.mentorsuccess.entity.School;
import io.aiontechnology.mentorsuccess.util.PhoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Mapper that converts a {@link School} to a {@link SchoolModel}.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
@Component
@RequiredArgsConstructor
public class SchoolEntityToModelMapper implements OneWayMapper<School, SchoolModel> {

    /** An update mapper from {@link School} to {@link AddressModel}. */
    private final OneWayMapper<School, AddressModel> mapper;

    /** A service for formatting phone numbers */
    private final PhoneService phoneService;

    /**
     * Map the given {@link School} to a {@link SchoolModel}.
     *
     * @param school The {@link School} to map.
     * @return The mapped {@link SchoolModel}.
     */
    @Override
    public Optional<SchoolModel> map(School school) {
        return Optional.ofNullable(school)
                .map(s -> SchoolModel.builder()
                        .withId(s.getId())
                        .withName(s.getName())
                        .withAddress(mapper.map(s).orElse(null))
                        .withPhone(phoneService.format(s.getPhone()))
                        .withDistrict(s.getDistrict())
                        .withIsPrivate(s.getIsPrivate())
                        .build());
    }

}
