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

package io.aiontechnology.mentorsuccess.api.controller;

import io.aiontechnology.mentorsuccess.api.assembler.LinkProvider;
import io.aiontechnology.mentorsuccess.api.assembler.PersonModelAssembler;
import io.aiontechnology.mentorsuccess.api.error.NotFoundException;
import io.aiontechnology.mentorsuccess.api.mapping.PersonMapper;
import io.aiontechnology.mentorsuccess.api.model.PersonModel;
import io.aiontechnology.mentorsuccess.entity.Person;
import io.aiontechnology.mentorsuccess.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * Controller that vends a REST interface for dealing with people.
 *
 * @author <a href="mailto:whitney@aiontechnology.io">Whitney Hunter</a>
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/v1/people")
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
@Slf4j
public class PersonController {

    /** Assembler for creating {@link PersonModel} instances */
    private final PersonModelAssembler personModelAssembler;

    /** Factory for converting {@link PersonModel} instances to {@link Person Persons} */
    private final PersonMapper personMapper;

    /** Service with business logic for people */
    private final PersonService personService;

    /**
     * A REST endpoint for creating new people.
     *
     * @param personModel The model that represents the desired new person.
     * @return A model that represents the created person.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonModel createPerson(@RequestBody @Valid PersonModel personModel) {
        log.debug("Creating person: {}", personModel);
        return Optional.ofNullable(personModel)
                .map(personMapper::mapModelToEntity)
                .map(personService::createPerson)
                .map(p -> personModelAssembler.toModel(p, linkProvider))
                .orElseThrow(() -> new IllegalArgumentException("Unable to create person"));
    }

    /**
     * A REST endpoint for getting a specific person
     *
     * @param personId The id of the desired person
     * @return The {@link PersonModel} for the desired person if it could be found.
     */
    @GetMapping("/{personId}")
    public PersonModel getPerson(@PathVariable("personId") UUID personId) {
        log.debug("Getting person with id: {}", personId);
        return personService.getPerson(personId)
                .map(s -> personModelAssembler.toModel(s, linkProvider))
                .orElseThrow(() -> new NotFoundException("Person was not found"));
    }

    private LinkProvider<PersonModel, Person> linkProvider = (personModel, person) ->
            Arrays.asList(
                    linkTo(PersonController.class).slash(person.getId()).withSelfRel()
            );

}
