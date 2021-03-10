/*
 * Copyright 2021 Aion Technology LLC
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.aiontechnology.mentorsuccess.api.controller;

import io.aiontechnology.mentorsuccess.api.assembler.BookModelAssembler;
import io.aiontechnology.mentorsuccess.api.assembler.GameModelAssembler;
import io.aiontechnology.mentorsuccess.api.assembler.LinkProvider;
import io.aiontechnology.mentorsuccess.api.mapping.toentity.misc.UriModelToBookMapper;
import io.aiontechnology.mentorsuccess.api.mapping.toentity.misc.UriModelToGameMapper;
import io.aiontechnology.mentorsuccess.entity.Book;
import io.aiontechnology.mentorsuccess.entity.Game;
import io.aiontechnology.mentorsuccess.model.outbound.OutboundBook;
import io.aiontechnology.mentorsuccess.model.outbound.OutboundGame;
import io.aiontechnology.mentorsuccess.service.SchoolResourceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * @author Whitney Hunter
 * @since 1.1.0
 */
@RestController
@RequestMapping("/api/v1/schools/{schoolId}")
@RequiredArgsConstructor
@Slf4j
public class SchoolResourceController {

    private final BookModelAssembler bookModelAssembler;

    private final GameModelAssembler gameModelAssembler;

    private final UriModelToBookMapper uriModelToBookMapper;

    private final UriModelToGameMapper uriModelToGameMapper;

    /** Service with business logic for school resources */
    private final SchoolResourceService schoolResourceService;

    @GetMapping("/books")
    @PreAuthorize("hasAuthority('school:read')")
    public CollectionModel<OutboundBook> getSchoolBooks(@PathVariable("schoolId") UUID schoolId) {
        var books = StreamSupport.stream(schoolResourceService.getBooksForSchool(schoolId).spliterator(), false)
                .map(s -> bookModelAssembler.toModel(s, bookLinkProvider))
                .collect(Collectors.toList());
        return CollectionModel.of(books);
    }

    @PutMapping("/books")
    @PreAuthorize("hasAuthority('school:update')")
    public CollectionModel<OutboundBook> setSchoolBooks(@PathVariable("schoolId") UUID schoolId,
            @RequestBody Collection<URI> bookURIs) {
        List<Book> books = bookURIs.stream()
                .map(u -> uriModelToBookMapper.map(u))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        List<OutboundBook> outboundBooks =
                StreamSupport.stream(schoolResourceService.setBooksForSchool(schoolId, books).spliterator(), false)
                        .map(bookModelAssembler::toModel)
                        .collect(Collectors.toList());
        return CollectionModel.of(outboundBooks);
    }

    @GetMapping("/games")
    @PreAuthorize("hasAuthority('school:read')")
    public CollectionModel<OutboundGame> getSchoolGames(@PathVariable("schoolId") UUID schoolId) {
        var games = StreamSupport.stream(schoolResourceService.getGamesForSchool(schoolId).spliterator(), false)
                .map(s -> gameModelAssembler.toModel(s, gameLinkProvider))
                .collect(Collectors.toList());
        return CollectionModel.of(games);
    }

    @PutMapping("/games")
    @PreAuthorize("hasAuthority('school:update')")
    public CollectionModel<OutboundGame> setSchoolGames(@PathVariable("schoolId") UUID schoolId,
            @RequestBody Collection<URI> gameURIs) {
        List<Game> games = gameURIs.stream()
                .map(uriModelToGameMapper::map)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        List<OutboundGame> outboundGames =
                StreamSupport.stream(schoolResourceService.setGamessForSchool(schoolId, games).spliterator(), false)
                        .map(gameModelAssembler::toModel)
                        .collect(Collectors.toList());
        return CollectionModel.of(outboundGames);
    }

    /** {@link LinkProvider} implementation for books. */
    private final LinkProvider<OutboundBook, Book> bookLinkProvider = (bookModel, book) ->
            Arrays.asList(
                    linkTo(BookController.class).slash(book.getId()).withSelfRel()
            );

    /** {@link LinkProvider} implementation for games. */
    private final LinkProvider<OutboundGame, Game> gameLinkProvider = (gameModel, game) ->
            Arrays.asList(
                    linkTo(GameController.class).slash(game.getId()).withSelfRel()
            );

}
