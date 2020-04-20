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

package io.aiontechnology.mentorsuccess.api.assembler;

import io.aiontechnology.mentorsuccess.api.controller.SchoolController;
import io.aiontechnology.mentorsuccess.api.model.AddressModel;
import io.aiontechnology.mentorsuccess.api.model.SchoolModel;
import io.aiontechnology.mentorsuccess.entity.School;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * Assembles {@link SchoolModel} instances from {@link School} entity instances.
 *
 * @author Whitney Hunter
 */
@Component
public class SchoolModelAssembler extends RepresentationModelAssemblerSupport<School, SchoolModel> {

    /**
     * Construct an instance
     */
    public SchoolModelAssembler() {
        super(SchoolController.class, SchoolModel.class);
    }

    /**
     * Create a {@link SchoolModel} instance from the given {@link School} entity instance.
     *
     * @param school The {@link School} entity to assemble.
     * @return The assembled {@link SchoolModel}.
     */
    @Override
    public SchoolModel toModel(School school) {
        AddressModel addressModel = new AddressModel(
                school.getStreet1(),
                school.getStreet2(),
                school.getCity(),
                school.getState(),
                school.getZip()
        );
        SchoolModel schoolModel = new SchoolModel(
                school.getId(),
                school.getName(),
                addressModel,
                school.getPhone());
        schoolModel.add(linkTo(SchoolController.class).slash(school.getId()).withSelfRel());
        return schoolModel;
    }

}
