/*
 * Copyright 2020-2022 Aion Technology LLC
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

import io.aiontechnology.mentorsuccess.entity.reference.LeadershipSkill;
import io.aiontechnology.mentorsuccess.entity.reference.LeadershipTrait;
import io.aiontechnology.mentorsuccess.model.enumeration.ResourceLocation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
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
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

/**
 * Entity that represents a game.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@Entity
@Table(name = "game")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Where(clause = "is_active = true")
public class Game implements Identifiable<UUID> {

    /** The ID of the game. */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    /** The name of the game. */
    @Column
    private String name;

    /** The start of the game's grade level range. */
    @Column
    private Integer grade1;

    /** The end of the game's grade level range. */
    @Column
    private Integer grade2;

    /** Is the game active? */
    @Column
    private Boolean isActive;

    /** The location of the resource */
    @Column
    @Enumerated(EnumType.STRING)
    private ResourceLocation location;

    /** A collection activity focuses for the game. */
    @ManyToMany
    @JoinTable(name = "game_activityfocus",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "activityfocus_id"))
    @ToString.Exclude
    private Collection<ActivityFocus> activityFocuses = new ArrayList<>();

    /** A collection leadership skills for the game. */
    @ManyToMany
    @JoinTable(name = "game_leadershipskill",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "leadershipskill_id"))
    @ToString.Exclude
    private Collection<LeadershipSkill> leadershipSkills = new ArrayList<>();

    /** A collection leadership traits for the game. */
    @ManyToMany
    @JoinTable(name = "game_leadershiptrait",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "leadershiptrait_id"))
    @ToString.Exclude
    private Collection<LeadershipTrait> leadershipTraits = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Game game = (Game) o;
        return id != null && Objects.equals(id, game.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }

}
