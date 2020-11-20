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
import io.aiontechnology.mentorsuccess.entity.Book;
import io.aiontechnology.mentorsuccess.model.outbound.OutboundBook;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link BookModelAssembler}.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
public class BookModelAssemblerTest {

    @Test
    void testToModel() throws Exception {
        // setup the fixture
        Book book = new Book();

        OneWayMapper<Book, OutboundBook> bookMapper = mock(OneWayMapper.class);
        OutboundBook outboundBook = OutboundBook.builder().build();
        when(bookMapper.map(book)).thenReturn(Optional.of(outboundBook));

        BookModelAssembler assembler = new BookModelAssembler(bookMapper, null);

        // execute the SUT
        OutboundBook result = assembler.toModel(book);

        // validation
        assertThat(result).isSameAs(outboundBook);
    }

    @Test
    void testToModel_nullBook() throws Exception {
        // setup the fixture
        Book book = null;

        OneWayMapper<Book, OutboundBook> bookMapper = mock(OneWayMapper.class);
        OutboundBook outboundBook = OutboundBook.builder().build();
        when(bookMapper.map(book)).thenReturn(Optional.of(outboundBook));

        BookModelAssembler assembler = new BookModelAssembler(bookMapper, null);

        // execute the SUT
        OutboundBook result = assembler.toModel(book);

        // validation
        assertThat(result).isNull();
    }

    @Test
    void testToModel_withLinkProvider() throws Exception {
        // setup the fixture
        Book book = new Book();

        OneWayMapper<Book, OutboundBook> bookMapper = mock(OneWayMapper.class);
        OutboundBook outboundBook = OutboundBook.builder().build();
        when(bookMapper.map(book)).thenReturn(Optional.of(outboundBook));

        LinkProvider<OutboundBook, Book> linkProvider = mock(LinkProvider.class);
        when(linkProvider.apply(any(), any())).thenReturn(Collections.emptyList());
        BookModelAssembler assembler = new BookModelAssembler(bookMapper, new LinkHelper<>());

        // execute the SUT
        OutboundBook result = assembler.toModel(book, linkProvider);

        // validation
        assertThat(result).isSameAs(outboundBook);
        verify(linkProvider).apply(outboundBook, book);
    }

}
