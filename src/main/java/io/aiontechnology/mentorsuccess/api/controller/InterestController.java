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

import io.aiontechnology.mentorsuccess.api.assembler.InterestModelAssembler;
import io.aiontechnology.mentorsuccess.api.model.BookModel;
import io.aiontechnology.mentorsuccess.api.model.InterestModel;
import io.aiontechnology.mentorsuccess.entity.Book;
import io.aiontechnology.mentorsuccess.entity.Interest;
import io.aiontechnology.mentorsuccess.service.InterestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Controller that vends a REST interface for dealing with interests.
 *
 * @author <a href="mailto:whitney@aiontechnology.io">Whitney Hunter</a>
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/v1/interests")
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
@Slf4j
public class InterestController {

    /** A HATEOAS assembler for {@link InterestModel InterestModels}. */
    private final InterestModelAssembler interestModelAssembler;

    /** Service for interacting with {@link Interest Interests}. */
    private final InterestService interestService;

    /**
     * A REST endpoint for getting all interests.
     *
     * @return A collection of models that represents the interests in the system.
     */
    @GetMapping
    public CollectionModel<InterestModel> getInterests() {
        List<InterestModel> interestModels = StreamSupport.stream(interestService.getAllInterests().spliterator(), false)
                .map(interestModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(interestModels);
    }

}
