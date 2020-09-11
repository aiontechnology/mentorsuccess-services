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

import io.aiontechnology.mentorsuccess.api.model.AddressModel;
import io.aiontechnology.mentorsuccess.api.model.SchoolModel;
import io.aiontechnology.mentorsuccess.entity.School;
import io.aiontechnology.mentorsuccess.util.PhoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Mapper between {@link School} and {@link SchoolModel}.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@Component
@RequiredArgsConstructor
public class SchoolMapper implements Mapper<School, SchoolModel> {

    /** Mapper to for {@link School} to {@link AddressModel}. */
    private final AddressMapper addressMapper;

    /** Service for dealing with phone numbers. */
    private final PhoneService phoneService;

    /**
     * Map a {@link School} to a new {@link SchoolModel}.
     *
     * @param school The {@link School} to map.
     * @return The resulting {@link SchoolModel}.
     */
    @Override
    public SchoolModel mapEntityToModel(School school) {
        return SchoolModel.builder()
                .withId(school.getId())
                .withName(school.getName())
                .withAddress(addressMapper.mapEntityToModel(school))
                .withPhone(phoneService.format(school.getPhone()))
                .withDistrict(school.getDistrict())
                .withIsPrivate(school.getIsPrivate())
                .build();
    }

    /**
     * Map a {@link SchoolModel} to a new {@link School}.
     *
     * @param schoolModel The {@link SchoolModel} to map.
     * @return The resulting {@link School}.
     */
    @Override
    public School mapModelToEntity(SchoolModel schoolModel) {
        School school = new School();
        return mapModelToEntity(schoolModel, school);
    }

    /**
     * Map a {@link SchoolModel} to the given {@link School}.
     *
     * @param schoolModel The {@link SchoolModel} to map.
     * @param school The {@link School} to map to.
     * @return The resulting {@link School}.
     */
    @Override
    public School mapModelToEntity(SchoolModel schoolModel, School school) {
        school.setName(schoolModel.getName());
        if (schoolModel.getAddress() != null) {
            school.setStreet1(schoolModel.getAddress().getStreet1());
            school.setStreet2(schoolModel.getAddress().getStreet2());
            school.setCity(schoolModel.getAddress().getCity());
            school.setState(schoolModel.getAddress().getState());
            school.setZip(schoolModel.getAddress().getZip());
        }
        school.setPhone(phoneService.normalize(schoolModel.getPhone()));
        school.setDistrict(schoolModel.getDistrict());
        school.setIsPrivate(schoolModel.getIsPrivate());
        school.setIsActive(true);
        return school;
    }

}
