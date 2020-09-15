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
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.UUID;

/**
 * Entity that represents a school.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Where(clause = "is_active = true")
@FilterDef(name = "roleType", parameters = @ParamDef(name = "type", type = "string"))
public class School {

    /** The ID of the school. */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    /** The name of the school. */
    @Column
    private String name;

    /** The street 1 of the school. */
    @Column
    private String street1;

    /** The street 2 of the school. */
    @Column
    private String street2;

    /** The city of the school. */
    @Column
    private String city;

    /** The state of the school. */
    @Column
    private String state;

    /** The zip code of the school. */
    @Column
    private String zip;

    /** The phone number of the school. */
    @Column
    private String phone;

    /** The district that the school is in. */
    @Column
    private String district;

    /** Is the school private? */
    @Column
    private Boolean isPrivate;

    /** Is the school active? */
    @Column
    private Boolean isActive;

    /** The collection of {@link Role Roles} associated with the school. */
    @OneToMany(mappedBy = "school")
    @Where(clause = "is_active = true")
    @Filter(name = "roleType", condition = "type = :type")
    private Collection<Role> roles;

    /**
     * Add a {@link Role} to the school.
     *
     * @param role The {@link Role} to add to the school.
     * @return The added {@link Role}.
     */
    public Role addRole(Role role) {
        role.setSchool(this);
        roles.add(role);
        return role;
    }

}
