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

import io.aiontechnology.mentorsuccess.entity.reference.LeadershipTrait;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

/**
 * An entity that connects {@link LeadershipTrait LeadershipTraits} to a {@link Student}.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Table(name = "student_leadershiptrait")
public class StudentLeadershipTrait {

    @EmbeddedId
    private StudentLeadershipTraitPK studentLeadershipTraitPK = new StudentLeadershipTraitPK();

    /** The associated {@link Student}. */
    @MapsId("student_id")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    /** The associated {@link LeadershipTrait}. */
    @MapsId("leadershiptrait_id")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    @JoinColumn(name = "leadershiptrait_id", referencedColumnName = "id")
    @EqualsAndHashCode.Include
    @ToString.Include
    private LeadershipTrait leadershipTrait;

    /** The associated {@link Person}. */
    @MapsId("role_Id")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @EqualsAndHashCode.Include
    private SchoolPersonRole role;

    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class StudentLeadershipTraitPK implements Serializable {

        private UUID student_id;
        private UUID leadershiptrait_id;
        private UUID role_Id;

    }

}
