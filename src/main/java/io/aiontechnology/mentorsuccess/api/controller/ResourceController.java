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

import io.aiontechnology.mentorsuccess.api.assembler.BookModelAssembler;
import io.aiontechnology.mentorsuccess.api.assembler.GameModelAssembler;
import io.aiontechnology.mentorsuccess.api.assembler.LinkProvider;
import io.aiontechnology.mentorsuccess.api.model.BookModel;
import io.aiontechnology.mentorsuccess.api.model.GameModel;
import io.aiontechnology.mentorsuccess.api.model.ResourceModel;
import io.aiontechnology.mentorsuccess.entity.Book;
import io.aiontechnology.mentorsuccess.entity.Game;
import io.aiontechnology.mentorsuccess.service.BookService;
import io.aiontechnology.mentorsuccess.service.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * Controller that vends a REST interface for dealing with resources.
 *
 * @author <a href="mailto:whitney@aiontechnology.io">Whitney Hunter</a>
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/v1/resources")
@RequiredArgsConstructor
@Slf4j
public class ResourceController {

    /** A HATEOAS assembler for {@link BookModel BookModels}. */
    private final BookModelAssembler bookModelAssembler;

    /** Service for interacting with {@link Book Books}. */
    private final BookService bookService;

    private final GameModelAssembler gameModelAssembler;

    private final GameService gameService;

    /**
     * A REST endpoint for retrieving all resources.
     *
     * @return A collection of {@link ResourceModel} instances.
     */
    @GetMapping
    public CollectionModel<ResourceModel<?>> getAllResources() {
        List<ResourceModel<?>> books = StreamSupport.stream(bookService.getAllBooks().spliterator(), false)
                .map(b -> bookModelAssembler.toModel(b, bookLinkProvider))
                .collect(Collectors.toList());
        List<ResourceModel<?>> games = StreamSupport.stream(gameService.getAllGames().spliterator(), false)
                .map(g -> gameModelAssembler.toModel(g, gameLinkProvider))
                .collect(Collectors.toList());
        List<ResourceModel<?>> combined = new ArrayList<>();
        combined.addAll(books);
        combined.addAll(games);
        return CollectionModel.of(combined);
    }

    private LinkProvider<BookModel, Book> bookLinkProvider = (bookModel, book) ->
            Arrays.asList(
                    linkTo(BookController.class).slash(book.getId()).withSelfRel()
            );

    private LinkProvider<GameModel, Game> gameLinkProvider = (gameModel, game) ->
            Arrays.asList(
                    linkTo(GameController.class).slash(game.getId()).withSelfRel()
            );

}
