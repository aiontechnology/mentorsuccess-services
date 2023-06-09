/*
 * Copyright 2021-2023 Aion Technology LLC
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

import io.aiontechnology.atlas.synchronization.CollectionSynchronizer;
import io.aiontechnology.atlas.synchronization.impl.SimpleCollectionSynchronizer;
import io.aiontechnology.mentorsuccess.entity.reference.ActivityFocus;
import io.aiontechnology.mentorsuccess.entity.reference.Interest;
import io.aiontechnology.mentorsuccess.model.enumeration.ResourceLocation;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * JPA Entity that represents a connection between a student and a school year.
 *
 * @author Whitney Hunter
 * @since 1.8.0
 */
@Entity
@Table(name = "student_schoolsession")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StudentSchoolSession {

    private static final CollectionSynchronizer<Interest> interestSyncHelper =
            new SimpleCollectionSynchronizer<>();

    private static final CollectionSynchronizer<StudentActivityFocus> studentActivityFocusSyncHelper =
            new SimpleCollectionSynchronizer<>();

    private static final CollectionSynchronizer<StudentBehavior> studentBehaviorSyncHelper =
            new SimpleCollectionSynchronizer<>();

    private static final CollectionSynchronizer<StudentLeadershipSkill> studentLeadershipSkillSyncHelper =
            new SimpleCollectionSynchronizer<>();

    private static final CollectionSynchronizer<StudentLeadershipTrait> studentLeadershipTraitSyncHelper =
            new SimpleCollectionSynchronizer<>();

    @Column(name = "actual_time", length = 30)
    private String actualTime;

    /** The student's grade */
    @Column(name = "grade")
    private Integer grade;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "student_interest",
            joinColumns = {@JoinColumn(name = "studentsession_id")},
            inverseJoinColumns = {@JoinColumn(name = "interest_id")}
    )
    private Collection<Interest> interests = new ArrayList<>();

    @Column
    private Boolean isActive;

    @Column(name = "is_media_release_signed")
    private Boolean isMediaReleaseSigned;

    @Column(name = "is_registration_signed")
    private Boolean isRegistrationSigned;

    /** The location of the resource */
    @Column(name = "location", length = 20)
    @Enumerated(EnumType.STRING)
    private ResourceLocation location;

    @ManyToOne
    @JoinColumn(name = "mentor_role_id")
    private SchoolPersonRole mentor;

    @Column
    private Integer postBehavioralAssessment;

    @Column
    private Integer preBehavioralAssessment;

    @Column(name = "preferred_time", length = 30)
    private String preferredTime;

    @ManyToOne(optional = false)
    @JoinColumn(name = "schoolsession_id", nullable = false)
    private SchoolSession schoolSession;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "question1")
    private Integer question1;

    @Column(name = "question2")
    private Integer question2;

    @Column(name = "question3")
    private Integer question3;

    @Column(name = "question4")
    private Integer question4;

    @Column(name = "question5")
    private Integer question5;

    @Column(name = "question6")
    private Integer question6;

    @Column(name = "question7")
    private Integer question7;

    @Column(name = "question8")
    private Integer question8;

    @Column(name = "question9")
    private Integer question9;

    @Column(name = "question10")
    private Integer question10;

    @Column(name = "question11")
    private Integer question11;

    @Column(name = "question12")
    private Integer question12;

    @Column(name = "question13")
    private Integer question13;

    @Column(name = "question14")
    private Integer question14;

    @Column(name = "question15")
    private Integer question15;

    @Column(name = "question16")
    private Integer question16;

    @Column(name = "question17")
    private Integer question17;

    @Column(name = "question18")
    private Integer question18;

    @Column(name = "question19")
    private Integer question19;

    @Column(name = "question20")
    private Integer question20;

    @Column(name = "question21")
    private Integer question21;

    @Column(name = "question22")
    private Integer question22;

    @Column(name = "question23")
    private Integer question23;

    @Column(name = "question24")
    private Integer question24;

    @Column(name = "question25")
    private Integer question25;

    @Column(name = "question26")
    private Integer question26;

    @Column(name = "question27")
    private Integer question27;

    @Column(name = "question28")
    private Integer question28;

    @Column(name = "question29")
    private Integer question29;

    @Column(name = "question30")
    private Integer question30;

    @Column(name = "question31")
    private Integer question31;

    @Column(name = "question32")
    private Integer question32;

    @Column(name = "question33")
    private Integer question33;

    @Column(name = "question34")
    private Integer question34;

    @Column(name = "question35")
    private Integer question35;

    @Column(name = "completed_info_flow_id")
    private String completedInfoFlowId;

    @Column(name = "started_info_flow_id")
    private String startedInfoFlowId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    /** The collection of {@link ActivityFocus ActivityFocuses} associated with the student. */
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "studentSchoolSession", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<StudentActivityFocus> studentActivityFocuses = new ArrayList<>();

    /** The collection of {@link StudentBehavior StudentBehaviors} associated with the student. */
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "studentSchoolSession", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<StudentBehavior> studentBehaviors = new ArrayList<>();

    /** The collection of {@link StudentLeadershipSkill StudentLeadershipSkills} associated with the student. */
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "studentSchoolSession", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<StudentLeadershipSkill> studentLeadershipSkills = new ArrayList<>();

    /** The collection of {@link StudentLeadershipTrait StudentLeadershipTraits} associated with the student. */
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "studentSchoolSession", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<StudentLeadershipTrait> studentLeadershipTraits = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "teacher_role_id")
    private SchoolPersonRole teacher;

    @Column(name = "teacher_comment")
    private String teacherComment;

    public StudentSchoolSession(StudentSchoolSession from) {
        grade = from.grade;
        location = from.location;
        isMediaReleaseSigned = from.isMediaReleaseSigned;
        student = from.student;
        isActive = from.isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StudentSchoolSession studentSchoolSession = (StudentSchoolSession) o;
        return id != null && Objects.equals(id, studentSchoolSession.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }

    public StudentSchoolSession incrementGrade() {
        grade = grade + 1;
        return this;
    }

    public StudentSchoolSession setSession(SchoolSession session) {
        this.setSchoolSession(session);
        session.getStudentSchoolSessions().add(this);
        return this;
    }

    public void setStudentActivityFocuses(Collection<StudentActivityFocus> studentActivityFocuses) {
        Collection<StudentActivityFocus> newCollection = studentActivityFocuses.stream()
                .map(studentActivityFocus -> {
                    studentActivityFocus.getStudentActivityFocusPK().setStudentsession_id(getId());
                    studentActivityFocus.setStudentSchoolSession(this);
                    return studentActivityFocus;
                })
                .collect(Collectors.toList());
        this.studentActivityFocuses = studentActivityFocusSyncHelper.sync(this.studentActivityFocuses,
                newCollection);
    }

    public void setStudentBehaviors(Collection<StudentBehavior> studentBehaviors) {
        Collection<StudentBehavior> newCollection = studentBehaviors.stream()
                .map(studentBehavior -> {
                    studentBehavior.getStudentBehaviorPK().setStudentsession_id(getId());
                    studentBehavior.setStudentSchoolSession(this);
                    return studentBehavior;
                })
                .collect(Collectors.toList());
        this.studentBehaviors = studentBehaviorSyncHelper.sync(this.studentBehaviors, newCollection);
    }

    public void setStudentInterests(Collection<Interest> interests) {
        this.interests = interestSyncHelper.sync(this.interests, interests);
    }

    public void setStudentLeadershipSkills(Collection<StudentLeadershipSkill> studentLeadershipSkills) {
        Collection<StudentLeadershipSkill> newCollection = studentLeadershipSkills.stream()
                .map(studentLeadershipSkill -> {
                    studentLeadershipSkill.getStudentLeadershipSkillPK().setStudentsession_id(getId());
                    studentLeadershipSkill.setStudentSchoolSession(this);
                    return studentLeadershipSkill;
                })
                .collect(Collectors.toList());
        this.studentLeadershipSkills = studentLeadershipSkillSyncHelper.sync(this.studentLeadershipSkills,
                newCollection);
    }

    public void setStudentLeadershipTraits(Collection<StudentLeadershipTrait> studentLeadershipTraits) {
        Collection<StudentLeadershipTrait> newCollection = studentLeadershipTraits.stream()
                .map(studentLeadershipTrait -> {
                    studentLeadershipTrait.getStudentLeadershipTraitPK().setStudentsession_id(getId());
                    studentLeadershipTrait.setStudentSchoolSession(this);
                    return studentLeadershipTrait;
                })
                .collect(Collectors.toList());
        this.studentLeadershipTraits = studentLeadershipTraitSyncHelper.sync(this.studentLeadershipTraits,
                newCollection);
    }

}