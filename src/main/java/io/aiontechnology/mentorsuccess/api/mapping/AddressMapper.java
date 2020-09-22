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

import io.aiontechnology.mentorsuccess.api.error.NotImplementedException;
import io.aiontechnology.mentorsuccess.api.model.AddressModel;
import io.aiontechnology.mentorsuccess.entity.School;
import org.springframework.stereotype.Component;

/**
 * Mapper between {@link School} and {@link AddressModel}.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@Component
public class AddressMapper implements Mapper<School, AddressModel> {

    /**
     * Map from the given {@link School} to an {@link AddressModel}.
     *
     * @param school The {@link School} to map from.
     * @return The newly mapped {@link AddressModel}.
     */
    @Override
    public AddressModel mapEntityToModel(School school) {
        return AddressModel.builder()
                .withStreet1(school.getStreet1())
                .withStreet2(school.getStreet2())
                .withCity(school.getCity())
                .withState(school.getState())
                .withZip(school.getZip())
                .build();
    }

    /**
     * Map from a given {@link AddressModel} to a {@link School}.
     *
     * @param addressModel The {@link AddressModel} to map from.
     * @return The newly mapped {@link School}.
     */
    @Override
    public School mapModelToEntity(AddressModel addressModel) {
        School school = new School();
        return mapModelToEntity(addressModel, school);
    }

    /**
     * Map from a given {@link AddressModel} into the given {@link School}.
     *
     * @param addressModel The {@link AddressModel} to map from.
     * @param school The {@link School} to map to.
     * @return The mapped {@link School}.
     */
    @Override
    public School mapModelToEntity(AddressModel addressModel, School school) {
        throw new NotImplementedException();
    }

}
