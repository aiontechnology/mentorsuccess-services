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

import io.aiontechnology.mentorsuccess.api.controller.PersonController;
import io.aiontechnology.mentorsuccess.api.mapping.ToPersonModelMapper;
import io.aiontechnology.mentorsuccess.api.model.PersonModel;
import io.aiontechnology.mentorsuccess.entity.Person;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Assembles {@link PersonModel} instances from {@link Person} entity instances.
 *
 * @author Whitney Hunter
 */
@Component
public class PersonModelAssembler extends RepresentationModelAssemblerSupport<Person, PersonModel> {

    private final LinkHelper<PersonModel> linkHelper;

    private final ToPersonModelMapper toPersonModelMapper;

    /**
     * Constructor
     */
    @Inject
    public PersonModelAssembler(ToPersonModelMapper toPersonModelMapper, LinkHelper<PersonModel> linkHelper) {
        super(PersonController.class, PersonModel.class);
        this.toPersonModelMapper = toPersonModelMapper;
        this.linkHelper = linkHelper;
    }

    /**
     * Create a {@link PersonModel} instance from the given {@link Person} entity.
     *
     * @param person The {@link Person} entity to assemble.
     * @return The assembled {@link PersonModel}.
     */
    @Override
    public PersonModel toModel(Person person) {
        return Optional.ofNullable(person)
                .map(toPersonModelMapper::map)
                .orElse(null);
    }

    /**
     * Create a {@link PersonModel} instance from the given {@link Person} entity. Add the links provided by the given
     * link provider.
     *
     * @param person
     * @param linkProvider
     * @return
     */
    public PersonModel toModel(Person person, LinkProvider<PersonModel, Person> linkProvider) {
        return Optional.ofNullable(person)
                .map(this::toModel)
                .map(model -> linkHelper.addLinks(model, linkProvider.apply(model, person)))
                .orElse(null);
    }

}
