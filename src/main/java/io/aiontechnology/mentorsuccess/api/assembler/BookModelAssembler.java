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

import io.aiontechnology.mentorsuccess.api.controller.BookController;
import io.aiontechnology.mentorsuccess.api.mapping.BookMapper;
import io.aiontechnology.mentorsuccess.api.model.BookModel;
import io.aiontechnology.mentorsuccess.entity.Book;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Optional;

/**
 * @author <a href="mailto:whitney@aiontechnology.io">Whitney Hunter</a>
 * @since 1.0.0
 */
@Component
public class BookModelAssembler extends RepresentationModelAssemblerSupport<Book, BookModel> {

    private final BookMapper bookMapper;

    private final LinkHelper<BookModel> linkHelper;

    @Inject
    public BookModelAssembler(BookMapper bookMapper, LinkHelper<BookModel> linkHelper) {
        super(BookController.class, BookModel.class);
        this.bookMapper = bookMapper;
        this.linkHelper = linkHelper;
    }

    @Override
    public BookModel toModel(Book book) {
        return Optional.ofNullable(book)
                .map(bookMapper::mapEntityToModel)
                .orElse(null);
    }

    public BookModel toModel(Book book, LinkProvider<BookModel, Book> linkProvider) {
        return Optional.ofNullable(book)
                .map(this::toModel)
                .map(model -> linkHelper.addLinks(model, linkProvider.apply(model, book)))
                .orElse(null);
    }

}
