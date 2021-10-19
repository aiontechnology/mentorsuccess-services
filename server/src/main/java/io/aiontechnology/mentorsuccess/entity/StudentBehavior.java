/*
 * Copyright 2020-2022 Aion Technology LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.aiontechnology.mentorsuccess.entity;

import io.aiontechnology.mentorsuccess.entity.reference.Behavior;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * An entity that connects {@link Behavior Behaviors} to a {@link Student}.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
@Entity
@Table(name = "student_behavior")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
public class StudentBehavior {

    /** The associated {@link Behavior}. */
    @MapsId("behavior_id")
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "behavior_id", referencedColumnName = "id", insertable = false, updatable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Behavior behavior = new Behavior();

    /** The associated {@link SchoolPersonRole}. */
    @MapsId("role_id")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @EqualsAndHashCode.Include
    private SchoolPersonRole role;

    @EmbeddedId
    private StudentBehaviorPK studentBehaviorPK = new StudentBehaviorPK();

    /** The associated {@link StudentSchoolSession}. */
    @MapsId("studentsession_id")
    @ManyToOne
    @JoinColumn(name = "studentsession_id", referencedColumnName = "id")
    private StudentSchoolSession studentSchoolSession;

    public StudentBehavior(StudentBehavior from) {
        behavior = from.behavior;
        role = from.role;
        studentSchoolSession = from.studentSchoolSession;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StudentBehavior that = (StudentBehavior) o;
        return studentBehaviorPK != null && Objects.equals(studentBehaviorPK, that.studentBehaviorPK);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentBehaviorPK);
    }

    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class StudentBehaviorPK implements Serializable {

        private UUID behavior_id;
        private UUID role_id;
        private UUID studentsession_id;

    }

}
