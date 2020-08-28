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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Set;
import java.util.UUID;

/**
 * @author Whitney Hunter
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Where(clause = "is_active = true")
public class Book {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column
    private String title;

    @Column
    private String author;

    @Column
    private Integer gradeLevel;

    @Column
    private Boolean isActive;

    @ManyToMany
    @JoinTable(name = "book_interest",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "interest_id"))
    private Set<Interest> interests;

    @ManyToMany
    @JoinTable(name = "book_leadershipskill",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "leadershipskill_id"))
    private Set<LeadershipSkill> leadershipSkills;

    @ManyToMany
    @JoinTable(name = "book_leadershiptrait",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "leadershiptrait_id"))
    private Set<LeadershipTrait> leadershipTraits;

    @ManyToMany
    @JoinTable(name = "book_phonogram",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "phonogram_id"))
    private Set<Phonogram> phonograms;

    @ManyToMany
    @JoinTable(name = "book_behavior",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "behavior_id"))
    private Set<Behavior> behaviors;

}
