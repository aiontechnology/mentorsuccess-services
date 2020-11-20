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

import io.aiontechnology.mentorsuccess.model.enumeration.ContactMethod;
import io.aiontechnology.mentorsuccess.model.enumeration.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.io.Serializable;
import java.util.UUID;

/**
 * An entity that connects {@link Person Persons} to a {@link Student}.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class StudentPersonRole {

    @EmbeddedId
    private StudentPersonPK studentPersonPK = new StudentPersonPK();

    /** The type of the person associated with this entry */
    @Column
    @Enumerated(EnumType.STRING)
    @EqualsAndHashCode.Include
    private RoleType personType;

    @Column
    @EqualsAndHashCode.Include
    private Boolean isEmergencyContact;

    @Column
    @Enumerated(EnumType.STRING)
    @EqualsAndHashCode.Include
    private ContactMethod preferredContactMethod;

    @Column
    @EqualsAndHashCode.Include
    private String comment;

    /** The associated {@link Student}. */
    @MapsId("student_id")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    /** The associated {@link Person}. */
    @MapsId("person_id")
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    @EqualsAndHashCode.Include
    private Person person = new Person();

    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class StudentPersonPK implements Serializable {

        private UUID student_id;
        private UUID person_id;

    }

}
