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
import io.aiontechnology.mentorsuccess.api.mapping.PersonMapper;
import io.aiontechnology.mentorsuccess.api.model.PersonModel;
import io.aiontechnology.mentorsuccess.entity.Person;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Assembles a {@link PersonModel} from a {@link Person}.
 *
 * @author Whitney Hunter
 * @since 1.0.0
 */
@Component
public class PersonModelAssembler extends RepresentationModelAssemblerSupport<Person, PersonModel> {

    /** A utility class for adding links to a model object. */
    private final LinkHelper<PersonModel> linkHelper;

    /** Mapper to map between {@link Person} and {@link PersonModel}. */
    private final PersonMapper personMapper;

    /**
     * Constructor
     *
     * @param personMapper The mapper for mapping between {@link Person} and {@link PersonModel}.
     * @param linkHelper A utility class for adding links to a model object.
     */
    @Inject
    public PersonModelAssembler(PersonMapper personMapper, LinkHelper<PersonModel> linkHelper) {
        super(PersonController.class, PersonModel.class);
        this.personMapper = personMapper;
        this.linkHelper = linkHelper;
    }

    /**
     * Map a {@link Person} to a {@link PersonModel} without adding links.
     *
     * @param person The {@link Person} to map.
     * @return The resulting {@link PersonModel}.
     */
    @Override
    public PersonModel toModel(Person person) {
        return Optional.ofNullable(person)
                .map(personMapper::mapEntityToModel)
                .orElse(null);
    }

    /**
     * Map a {@link Person} to a {@link PersonModel} and add links.
     *
     * @param person The {@link Person} to map.
     * @param linkProvider An object that provides links.
     * @return The resulting {@link PersonModel}.
     */
    public PersonModel toModel(Person person, LinkProvider<PersonModel, Person> linkProvider) {
        return Optional.ofNullable(person)
                .map(this::toModel)
                .map(model -> linkHelper.addLinks(model, linkProvider.apply(model, person)))
                .orElse(null);
    }

}
