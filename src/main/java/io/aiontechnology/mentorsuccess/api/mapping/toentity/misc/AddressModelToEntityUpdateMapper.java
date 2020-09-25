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

package io.aiontechnology.mentorsuccess.api.mapping.toentity.misc;

import io.aiontechnology.mentorsuccess.api.mapping.OneWayUpdateMapper;
import io.aiontechnology.mentorsuccess.api.model.inbound.AddressModel;
import io.aiontechnology.mentorsuccess.entity.School;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * An update mapper that converts a {@link AddressModel} to a {@link School}.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
@Component
public class AddressModelToEntityUpdateMapper implements OneWayUpdateMapper<AddressModel, School> {

    /**
     * Map the given {@link AddressModel}
     *
     * @param addressModel The {@link AddressModel} to map.
     * @param school The {@link School} to map to.
     * @return The resulting {@link School}.
     */
    @Override
    public Optional<School> map(AddressModel addressModel, School school) {
        return Optional.ofNullable(addressModel)
                .map(a -> {
                    school.setStreet1(a.getStreet1());
                    school.setStreet2(a.getStreet2());
                    school.setCity(a.getCity());
                    school.setState(a.getState());
                    school.setZip(a.getZip());
                    return school;
                });
    }

}
