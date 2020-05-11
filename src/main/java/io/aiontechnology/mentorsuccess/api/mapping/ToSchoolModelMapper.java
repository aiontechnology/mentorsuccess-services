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

import io.aiontechnology.mentorsuccess.api.model.SchoolModel;
import io.aiontechnology.mentorsuccess.entity.School;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Mapper to create a {@link SchoolModel} from a {@link School} instance.
 *
 * @author Whitney Hunter
 */
@Component
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class ToSchoolModelMapper implements ImmutableMapper<School, SchoolModel> {

    /** Mapper to map the {@link School} instances */
    private final ToAddressModelMapper toAddressModelMapper;

    /**
     * Map the given {@link School} to a new {@link SchoolModel}.
     *
     * @param school The {@link School} to map.
     * @return The mapped {@link SchoolModel}.
     */
    @Override
    public SchoolModel map(School school) {
        return SchoolModel.builder()
                .withId(school.getId())
                .withName(school.getName())
                .withAddress(toAddressModelMapper.map(school))
                .withPhone(school.getPhone())
                .withDistrict(school.getDistrict())
                .withIsPrivate(school.getIsPrivate())
                .build();
    }

}
