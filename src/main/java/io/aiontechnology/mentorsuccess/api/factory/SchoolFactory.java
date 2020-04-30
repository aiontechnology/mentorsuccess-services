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

package io.aiontechnology.mentorsuccess.api.factory;

import io.aiontechnology.mentorsuccess.api.model.SchoolModel;
import io.aiontechnology.mentorsuccess.entity.School;
import org.springframework.stereotype.Component;

/**
 * Factory that can create {@link School} instances from {@link SchoolModel} instances.
 */
@Component
public class SchoolFactory {

    /**
     * Convert the given {@link SchoolModel} into a new {@link School} instance.
     *
     * @param schoolModel The {@link SchoolModel} to convert
     * @return The generated {@link School} instance.
     */
    public School fromModel(SchoolModel schoolModel) {
        School school = new School();
        return fromModel(schoolModel, school);
    }

    /**
     * Update the given {@link School} from the values in the given {@link SchoolModel}.
     *
     * @param schoolModel The {@link SchoolModel} to obtain values from.
     * @param school The {@link School} to update.
     * @return The updated {@link School}.
     */
    public School fromModel(SchoolModel schoolModel, School school) {
        school.setName(schoolModel.getName());
        if(schoolModel.getAddress() != null) {
            school.setStreet1(schoolModel.getAddress().getStreet1());
            school.setStreet2(schoolModel.getAddress().getStreet2());
            school.setCity(schoolModel.getAddress().getCity());
            school.setState(schoolModel.getAddress().getState());
            school.setZip(schoolModel.getAddress().getZip());
        }
        school.setPhone(schoolModel.getPhone());
        school.setDistrict(schoolModel.getDistrict());
        school.setIsPrivate(schoolModel.getIsPrivate());
        return school;
    }

}
