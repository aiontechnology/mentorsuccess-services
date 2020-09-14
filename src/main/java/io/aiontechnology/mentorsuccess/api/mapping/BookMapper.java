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

import io.aiontechnology.mentorsuccess.api.model.BehaviorModel;
import io.aiontechnology.mentorsuccess.api.model.BookModel;
import io.aiontechnology.mentorsuccess.api.model.InterestModel;
import io.aiontechnology.mentorsuccess.api.model.LeadershipSkillModel;
import io.aiontechnology.mentorsuccess.api.model.LeadershipTraitModel;
import io.aiontechnology.mentorsuccess.api.model.PhonogramModel;
import io.aiontechnology.mentorsuccess.entity.Behavior;
import io.aiontechnology.mentorsuccess.entity.Book;
import io.aiontechnology.mentorsuccess.entity.Interest;
import io.aiontechnology.mentorsuccess.entity.LeadershipSkill;
import io.aiontechnology.mentorsuccess.entity.LeadershipTrait;
import io.aiontechnology.mentorsuccess.entity.Phonogram;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Mapper between {@link Book} and {@link BookModel}.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@Component
@RequiredArgsConstructor
public class BookMapper implements Mapper<Book, BookModel> {

    /** Mapper between {@link Interest} and {@link InterestModel}. */
    private final InterestMapper interestMapper;

    /** Mapper between {@link LeadershipSkill} and {@link LeadershipSkillModel}. */
    private final LeadershipSkillMapper leadershipSkillMapper;

    /** Mapper between {@link LeadershipTrait} and {@link LeadershipTraitModel}. */
    private final LeadershipTraitMapper leadershipTraitMapper;

    /** Mapper between {@link Phonogram} and {@link PhonogramModel}. */
    private final PhonogramMapper phonogramMapper;

    /** Mapper between {@link Behavior} and {@link BehaviorModel}. */
    private final BehaviorMapper behaviorMapper;

    /**
     * Map a {@link Book} to a new {@link BookModel}.
     *
     * @param book The {@link Book} to map.
     * @return The resulting {@link BookModel}.
     */
    @Override
    public BookModel mapEntityToModel(Book book) {
        return BookModel.builder()
                .withId(book.getId())
                .withTitle(book.getTitle())
                .withAuthor(book.getAuthor())
                .withGradeLevel(book.getGradeLevel())
                .withInterests(interestMapper.mapInterests(() -> book.getInterests()))
                .withLeadershipSkills(leadershipSkillMapper.mapLeadershipSkills(() -> book.getLeadershipSkills()))
                .withLeadershipTraits(leadershipTraitMapper.mapLeadershipTraits(() -> book.getLeadershipTraits()))
                .withPhonograms(phonogramMapper.mapPhonograms(() -> book.getPhonograms()))
                .withBehaviors(behaviorMapper.mapBehaviors(() -> book.getBehaviors()))
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
        book.setInterests(interestMapper.mapInterests(bookModel));
        book.setLeadershipSkills(leadershipSkillMapper.mapLeadershipSkills(bookModel));
        book.setLeadershipTraits(leadershipTraitMapper.mapLeadershipTraits(bookModel));
        book.setPhonograms(phonogramMapper.mapPhonograms(bookModel));
        book.setBehaviors(behaviorMapper.mapBehaviors(bookModel));
        book.setIsActive(true);
        return book;
    }

}
