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

@Component
public class SchoolFactory {

    public School fromModel(SchoolModel schoolModel) {
        School school = new School();
        return fromModel(schoolModel, school);
    }

    public School fromModel(SchoolModel schoolModel, School school) {
        school.setName(schoolModel.getName());
        school.setStreet1(schoolModel.getAddress().getStreet1());
        school.setStreet2(schoolModel.getAddress().getStreet2());
        school.setCity(schoolModel.getAddress().getCity());
        school.setState(schoolModel.getAddress().getState());
        school.setZip(schoolModel.getAddress().getZip());
        school.setPhone(schoolModel.getPhone());
        return school;
    }

}
