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

package io.aiontechnology.mentorsuccess.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Collection;
import java.util.UUID;

/**
 * Entity that represents a book.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Where(clause = "is_active = true")
public class Book {

    /** The ID of the book. */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    /** The title of the book. */
    @Column
    private String title;

    /** The author of the book. */
    @Column
    private String author;

    /** The grade level of the book. */
    @Column
    private Integer gradeLevel;

    /** Is the book active? */
    @Column
    private Boolean isActive;

    /** The location of the resource */
    @Column
    @Enumerated(EnumType.STRING)
    private ResourceLocation location;

    /** A collection interests for the book. */
    @ManyToMany
    @JoinTable(name = "book_interest",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "interest_id"))
    private Collection<Interest> interests;

    /** A collection leadership skills for the book. */
    @ManyToMany
    @JoinTable(name = "book_leadershipskill",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "leadershipskill_id"))
    private Collection<LeadershipSkill> leadershipSkills;

    /** A collection leadership traits for the book. */
    @ManyToMany
    @JoinTable(name = "book_leadershiptrait",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "leadershiptrait_id"))
    private Collection<LeadershipTrait> leadershipTraits;

    /** A collection phonograms for the book. */
    @ManyToMany
    @JoinTable(name = "book_phonogram",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "phonogram_id"))
    private Collection<Phonogram> phonograms;

    /** A collection behaviors for the book. */
    @ManyToMany
    @JoinTable(name = "book_behavior",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "behavior_id"))
    private Collection<Behavior> behaviors;

}
