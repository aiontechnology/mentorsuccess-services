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

package io.aiontechnology.mentorsuccess.api.mapping.toentity.school;

import io.aiontechnology.mentorsuccess.api.mapping.OneWayUpdateMapper;
import io.aiontechnology.mentorsuccess.api.model.inbound.AddressModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.SchoolModel;
import io.aiontechnology.mentorsuccess.entity.School;
import io.aiontechnology.mentorsuccess.util.PhoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

/**
 * Mapper that updates a {@link School} from a {@link SchoolModel}.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
@Component
@RequiredArgsConstructor
public class SchoolModelToEntityUpdateMapper implements OneWayUpdateMapper<SchoolModel, School> {

    /** An update mapper between {@link AddressModel} and {@link School}. */
    private final OneWayUpdateMapper<AddressModel, School> addressMapper;

    /** A service for formatting phone numbers */
    private final PhoneService phoneService;

    /**
     * Update the given {@link School} with the given {@link SchoolModel}.
     *
     * @param schoolModel The {@link SchoolModel} to update from.
     * @param school The {@link School} to update.
     * @return The updated {@link School}.
     */
    @Override
    public Optional<School> map(SchoolModel schoolModel, School school) {
        Objects.requireNonNull(school);
        return Optional.ofNullable(schoolModel)
                .map(s -> {
                    school.setName(schoolModel.getName());
                    school.setPhone(phoneService.normalize(schoolModel.getPhone()));
                    school.setDistrict(schoolModel.getDistrict());
                    school.setIsPrivate(schoolModel.getIsPrivate());
                    school.setIsActive(true);
                    addressMapper.map(schoolModel.getAddress(), school);
                    return school;
                });
    }

}
