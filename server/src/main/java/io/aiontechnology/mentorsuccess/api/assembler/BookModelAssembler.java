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
import io.aiontechnology.mentorsuccess.api.controller.BookController;
import io.aiontechnology.mentorsuccess.entity.Book;
import io.aiontechnology.mentorsuccess.model.inbound.InboundBook;
import io.aiontechnology.mentorsuccess.model.outbound.OutboundBook;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Assembles a {@link OutboundBook} from a {@link Book}.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@Component
public class BookModelAssembler extends RepresentationModelAssemblerSupport<Book, OutboundBook> {

    /** Mapper to map between {@link Book} and {@link OutboundBook}. */
    private final OneWayMapper<Book, OutboundBook> bookMapper;

    /** A utility class for adding links to a model object. */
    private final LinkHelper<OutboundBook> linkHelper;

    /**
     * Constructor
     *
     * @param bookMapper The mapper for mapping between {@link Book} and {@link InboundBook}.
     * @param linkHelper A utility class for adding links to a model object.
     */
    @Inject
    public BookModelAssembler(OneWayMapper<Book, OutboundBook> bookMapper, LinkHelper<OutboundBook> linkHelper) {
        super(BookController.class, OutboundBook.class);
        this.bookMapper = bookMapper;
        this.linkHelper = linkHelper;
    }

    /**
     * Map a {@link Book} to a {@link OutboundBook} without adding links.
     *
     * @param book The {@link Book} to map.
     * @return The resulting {@link OutboundBook}.
     */
    @Override
    public OutboundBook toModel(Book book) {
        return Optional.ofNullable(book)
                .flatMap(bookMapper::map)
                .orElse(null);
    }

    /**
     * Map a {@link Book} to a {@link InboundBook} and add links.
     *
     * @param book The {@link Book} to map.
     * @param linkProvider An object that provides links.
     * @return The resulting {@link InboundBook}.
     */
    public OutboundBook toModel(Book book, LinkProvider<OutboundBook, Book> linkProvider) {
        return Optional.ofNullable(book)
                .map(this::toModel)
                .map(model -> linkHelper.addLinks(model, linkProvider.apply(model, book)))
                .orElse(null);
    }

}
