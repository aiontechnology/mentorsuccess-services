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

package io.aiontechnology.mentorsuccess.api.mapping;

import io.aiontechnology.mentorsuccess.api.model.BookModel;
import io.aiontechnology.mentorsuccess.api.model.InterestModel;
import io.aiontechnology.mentorsuccess.api.model.LeadershipSkillModel;
import io.aiontechnology.mentorsuccess.api.model.LeadershipTraitModel;
import io.aiontechnology.mentorsuccess.entity.Book;
import io.aiontechnology.mentorsuccess.entity.Interest;
import io.aiontechnology.mentorsuccess.entity.LeadershipSkill;
import io.aiontechnology.mentorsuccess.entity.LeadershipTrait;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Mapper between {@link Book} and {@link BookModel}.
 *
 * @author <a href="mailto:whitney@aiontechnology.io">Whitney Hunter</a>
 * @since 1.0.0
 */
@Component
@RequiredArgsConstructor
public class BookMapper implements Mapper<Book, BookModel> {

    private final LeadershipTraitMapper leadershipTraitMapper;

    private final InterestMapper interestMapper;

    private final LeadershipSkillMapper leadershipSkillMapper;

    /**
     * Map a {@link Book} to a new {@link BookModel}.
     *
     * @param book The {@link Book} to map.
     * @return The resulting {@link BookModel}.
     */
    @Override
    public BookModel mapEntityToModel(Book book) {
        return BookModel.builder()
                .withTitle(book.getTitle())
                .withAuthor(book.getAuthor())
                .withGradeLevel(book.getGradeLevel())
                .withInterests(mapInterests(book))
                .withLeadershipTraits(mapCharacterTraits(book))
                .withLeadershipSkills(mapLeadershipSkills(book))
                .build();
    }

    /**
     * Map a {@link BookModel} to a new {@link Book}.
     *
     * @param bookModel The {@link BookModel} to map.
     * @return The resulting {@link Book}.
     */
    @Override
    public Book mapModelToEntity(BookModel bookModel) {
        Book book = new Book();
        return mapModelToEntity(bookModel, book);
    }

    /**
     * Map a {@link BookModel} to the given {@link Book}.
     *
     * @param bookModel The {@link BookModel} to map.
     * @param book The {@link Book} to map to.
     * @return The resulting {@link Book}.
     */
    @Override
    public Book mapModelToEntity(BookModel bookModel, Book book) {
        book.setTitle(bookModel.getTitle());
        book.setAuthor(bookModel.getAuthor());
        book.setGradeLevel(bookModel.getGradeLevel());
        book.setInterests(mapInterests(bookModel));
        book.setLeadershipTraits(mapCharacterTraits(bookModel));
        book.setLeadershipSkills(mapLeadershipSkills(bookModel));
        book.setIsActive(true);
        return book;
    }

    private Set<InterestModel> mapInterests(Book book) {
        return book.getInterests().stream()
                .map(interestMapper::mapEntityToModel)
                .collect(Collectors.toSet());
    }

    private Set<Interest> mapInterests(BookModel bookModel) {
        if (bookModel.getInterests() != null) {
            return bookModel.getInterests().stream()
                    .map(interestMapper::mapModelToEntity)
                    .collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }

    private Set<LeadershipTraitModel> mapCharacterTraits(Book book) {
        return book.getLeadershipTraits().stream()
                .map(leadershipTraitMapper::mapEntityToModel)
                .collect(Collectors.toSet());
    }

    private Set<LeadershipTrait> mapCharacterTraits(BookModel bookModel) {
        if (bookModel.getLeadershipTraits() != null) {
            return bookModel.getLeadershipTraits().stream()
                    .map(leadershipTraitMapper::mapModelToEntity)
                    .collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }

    private Set<LeadershipSkillModel> mapLeadershipSkills(Book book) {
        return book.getLeadershipSkills().stream()
                .map(leadershipSkillMapper::mapEntityToModel)
                .collect(Collectors.toSet());
    }

    private Set<LeadershipSkill> mapLeadershipSkills(BookModel bookModel) {
        if (bookModel.getLeadershipSkills() != null) {
            return bookModel.getLeadershipSkills().stream()
                    .map(leadershipSkillMapper::mapModelToEntity)
                    .collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }

}
