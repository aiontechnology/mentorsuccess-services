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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

/**
 * Entity that represents a person.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Person {

    /** The ID of the person. */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @EqualsAndHashCode.Exclude
    private UUID id;

    /** The first name of the person. */
    @Column
    private String firstName;

    /** The last name of the person. */
    @Column
    private String lastName;

    /** The work phone of the person. */
    @Column
    private String workPhone;

    /** The cell phone of the person. */
    @Column
    private String cellPhone;

    /** The email address of the person. */
    @Column
    private String email;

    /** The roles played by the person. */
//    @OneToMany(mappedBy = "person")
//    @Where(clause = "is_active = true")
//    @EqualsAndHashCode.Exclude
//    private Collection<SchoolPersonRole> roles;

}
