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

import io.aiontechnology.mentorsuccess.api.mapping.BookMapper;
import io.aiontechnology.mentorsuccess.api.model.BookModel;
import io.aiontechnology.mentorsuccess.entity.Book;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static java.lang.Boolean.TRUE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link BookModelAssembler}.
 *
 * @author <a href="mailto:whitney@aiontechnology.io">Whitney Hunter</a>
 * @since 1.0.0
 */
public class BookModelAssemblerTest {

    private static final String TITLE = "TITLE";
    private static final String AUTHOR = "AUTHOR";
    private static final Integer GRADE_LEVEL = 1;
    private static final Boolean IS_ACTIVE = TRUE;

    @Test
    void testToModel() throws Exception {
        // setup the fixture
        Book book = new Book();

        BookMapper bookMapper = mock(BookMapper.class);
        BookModel bookModel = mock(BookModel.class);
        when(bookMapper.mapEntityToModel(book)).thenReturn(bookModel);

        BookModelAssembler assembler = new BookModelAssembler(bookMapper, null);

        // execute the SUT
        BookModel result = assembler.toModel(book);

        // validation
        assertThat(result).isSameAs(bookModel);
    }

    @Test
    void testToModel_nullBook() throws Exception {
        // setup the fixture
        Book book = null;

        BookMapper bookMapper = mock(BookMapper.class);
        BookModel bookModel = mock(BookModel.class);
        when(bookMapper.mapEntityToModel(book)).thenReturn(bookModel);

        BookModelAssembler assembler = new BookModelAssembler(bookMapper, null);

        // execute the SUT
        BookModel result = assembler.toModel(book);

        // validation
        assertThat(result).isNull();
    }

    @Test
    void testToModel_withLinkProvider() throws Exception {
        // setup the fixture
        Book book = new Book();

        BookMapper bookMapper = mock(BookMapper.class);
        BookModel bookModel = mock(BookModel.class);
        when(bookMapper.mapEntityToModel(book)).thenReturn(bookModel);

        LinkProvider<BookModel, Book> linkProvider = mock(LinkProvider.class);
        when(linkProvider.apply(any(), any())).thenReturn(Collections.emptyList());
        BookModelAssembler assembler = new BookModelAssembler(bookMapper, new LinkHelper<>());

        // execute the SUT
        BookModel result = assembler.toModel(book, linkProvider);

        // validation
        assertThat(result).isSameAs(bookModel);
        verify(linkProvider).apply(bookModel, book);
    }

}
