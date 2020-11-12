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

import io.aiontechnology.atlas.mapping.OneWayMapper;
import io.aiontechnology.mentorsuccess.api.controller.PersonController;
import io.aiontechnology.mentorsuccess.entity.Person;
import io.aiontechnology.mentorsuccess.model.inbound.InboundPerson;
import io.aiontechnology.mentorsuccess.model.outbound.OutboundPerson;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Assembles a {@link InboundPerson} from a {@link Person}.
 *
 * @author Whitney Hunter
 * @since 1.0.0
 */
@Component
public class PersonModelAssembler extends LinkAddingRepresentationModelAssemblerSupport<Person, OutboundPerson> {

    /** Mapper to map between {@link Person} and {@link OutboundPerson}. */
    private final OneWayMapper<Person, OutboundPerson> mapper;

    /**
     * Constructor
     *
     * @param mapper The mapper for mapping between {@link Person} and {@link InboundPerson}.
     * @param linkHelper A utility class for adding links to a model object.
     */
    @Inject
    public PersonModelAssembler(OneWayMapper<Person, OutboundPerson> mapper, LinkHelper<OutboundPerson> linkHelper) {
        super(PersonController.class, OutboundPerson.class, linkHelper);
        this.mapper = mapper;
    }

    /**
     * Map a {@link Person} to a {@link OutboundPerson} without adding links.
     *
     * @param person The {@link Person} to map.
     * @return The resulting {@link OutboundPerson}.
     */
    @Override
    public OutboundPerson toModel(Person person) {
        return Optional.ofNullable(person)
                .flatMap(mapper::map)
                .orElse(null);
    }

    /**
     * Map a {@link Person} to a {@link InboundPerson} and add links.
     *
     * @param person The {@link Person} to map.
     * @param linkProvider An object that provides links.
     * @return The resulting {@link InboundPerson}.
     */
    public OutboundPerson toModel(Person person, LinkProvider<OutboundPerson, Person> linkProvider) {
        return Optional.ofNullable(person)
                .map(this::toModel)
                .map(model -> getLinkHelper().addLinks(model, linkProvider.apply(model, person)))
                .orElse(null);
    }

}
