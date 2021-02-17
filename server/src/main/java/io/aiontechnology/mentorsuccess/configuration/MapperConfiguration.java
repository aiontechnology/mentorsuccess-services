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

package io.aiontechnology.mentorsuccess.configuration;

import io.aiontechnology.atlas.mapping.OneWayCollectionMapper;
import io.aiontechnology.atlas.mapping.OneWayMapper;
import io.aiontechnology.atlas.mapping.OneWayUpdateMapper;
import io.aiontechnology.atlas.mapping.impl.SimpleOneWayCollectionMapper;
import io.aiontechnology.atlas.mapping.impl.UpdateMapperBasedOneWayMapper;
import io.aiontechnology.atlas.synchronization.CollectionSynchronizer;
import io.aiontechnology.atlas.synchronization.impl.SimpleCollectionSynchronizer;
import io.aiontechnology.mentorsuccess.api.assembler.MentorModelAssembler;
import io.aiontechnology.mentorsuccess.api.assembler.PersonModelAssembler;
import io.aiontechnology.mentorsuccess.api.assembler.TeacherModelAssembler;
import io.aiontechnology.mentorsuccess.api.controller.MentorController;
import io.aiontechnology.mentorsuccess.api.controller.PersonController;
import io.aiontechnology.mentorsuccess.api.controller.TeacherController;
import io.aiontechnology.mentorsuccess.api.mapping.AssemblerMapperAdaptor;
import io.aiontechnology.mentorsuccess.entity.ActivityFocus;
import io.aiontechnology.mentorsuccess.entity.Book;
import io.aiontechnology.mentorsuccess.entity.Game;
import io.aiontechnology.mentorsuccess.entity.Person;
import io.aiontechnology.mentorsuccess.entity.School;
import io.aiontechnology.mentorsuccess.entity.SchoolPersonRole;
import io.aiontechnology.mentorsuccess.entity.Student;
import io.aiontechnology.mentorsuccess.entity.StudentBehavior;
import io.aiontechnology.mentorsuccess.entity.StudentLeadershipSkill;
import io.aiontechnology.mentorsuccess.entity.StudentLeadershipTrait;
import io.aiontechnology.mentorsuccess.entity.StudentPersonRole;
import io.aiontechnology.mentorsuccess.entity.StudentTeacher;
import io.aiontechnology.mentorsuccess.entity.reference.Behavior;
import io.aiontechnology.mentorsuccess.entity.reference.Interest;
import io.aiontechnology.mentorsuccess.entity.reference.LeadershipSkill;
import io.aiontechnology.mentorsuccess.entity.reference.LeadershipTrait;
import io.aiontechnology.mentorsuccess.entity.reference.Phonogram;
import io.aiontechnology.mentorsuccess.model.inbound.InboundBook;
import io.aiontechnology.mentorsuccess.model.inbound.InboundGame;
import io.aiontechnology.mentorsuccess.model.inbound.InboundMentor;
import io.aiontechnology.mentorsuccess.model.inbound.InboundPerson;
import io.aiontechnology.mentorsuccess.model.inbound.InboundPersonnel;
import io.aiontechnology.mentorsuccess.model.inbound.InboundProgramAdmin;
import io.aiontechnology.mentorsuccess.model.inbound.InboundSchool;
import io.aiontechnology.mentorsuccess.model.inbound.InboundTeacher;
import io.aiontechnology.mentorsuccess.model.inbound.student.InboundContact;
import io.aiontechnology.mentorsuccess.model.inbound.student.InboundStudent;
import io.aiontechnology.mentorsuccess.model.inbound.student.InboundStudentTeacher;
import io.aiontechnology.mentorsuccess.model.outbound.OutboundMentor;
import io.aiontechnology.mentorsuccess.model.outbound.OutboundPerson;
import io.aiontechnology.mentorsuccess.model.outbound.OutboundTeacher;
import io.aiontechnology.mentorsuccess.model.outbound.student.OutboundContact;
import io.aiontechnology.mentorsuccess.service.ActivityFocusService;
import io.aiontechnology.mentorsuccess.service.BehaviorService;
import io.aiontechnology.mentorsuccess.service.InterestService;
import io.aiontechnology.mentorsuccess.service.LeadershipSkillService;
import io.aiontechnology.mentorsuccess.service.LeadershipTraitService;
import io.aiontechnology.mentorsuccess.service.PhonogramService;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * Spring configuration class to establish mappers.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@Configuration
public class MapperConfiguration {

    /*
     * ActivityFocus
     */

    @Bean
    public Function<String, Optional<ActivityFocus>> findActivityFocusFunction(ActivityFocusService activityFocusService) {
        return activityFocusService::findActivityFocusByName;
    }

    @Bean
    public OneWayCollectionMapper<String, ActivityFocus> activityFocusModelToEntityOneWayCollectionMapper(
            OneWayMapper<String, ActivityFocus> mapper) {
        return new SimpleOneWayCollectionMapper<>(mapper);
    }

    @Bean
    public OneWayCollectionMapper<ActivityFocus, String> activityFocusEntityToModelOneWayCollectionMapper(
            OneWayMapper<ActivityFocus, String> mapper) {
        return new SimpleOneWayCollectionMapper<>(mapper);
    }

    /*
     * Behavior
     */

    @Bean
    public Function<String, Optional<Behavior>> findBehaviorFunction(BehaviorService behaviorService) {
        return behaviorService::findBehaviorByName;
    }

    @Bean
    public OneWayCollectionMapper<String, Behavior> behaviorModelToEntityOneWayCollectionMapper(
            OneWayMapper<String, Behavior> mapper) {
        return new SimpleOneWayCollectionMapper<>(mapper);
    }

    @Bean
    public OneWayCollectionMapper<Behavior, String> behaviorEntityToModelOneWayCollectionMapper(
            OneWayMapper<Behavior, String> mapper) {
        return new SimpleOneWayCollectionMapper<>(mapper);
    }

    /*
     * Book
     */

    @Bean
    public OneWayMapper<InboundBook, Book> bookModelToEntityMapper(OneWayUpdateMapper<InboundBook, Book> mapper) {
        return new UpdateMapperBasedOneWayMapper<>(mapper, Book.class);
    }

    /*
     * Game
     */

    @Bean
    public OneWayMapper<InboundGame, Game> gameModelToEntityMapper(OneWayUpdateMapper<InboundGame, Game> mapper) {
        return new UpdateMapperBasedOneWayMapper<>(mapper, Game.class);
    }

    /*
     * Interest
     */

    @Bean
    public Function<String, Optional<Interest>> findInterestFunction(InterestService interestService) {
        return interestService::findInterestByName;
    }

    @Bean
    public OneWayCollectionMapper<String, Interest> interestModelToEntityOneWayCollectionMapper(
            OneWayMapper<String, Interest> mapper) {
        return new SimpleOneWayCollectionMapper<>(mapper);
    }

    @Bean
    public OneWayCollectionMapper<Interest, String> interestEntityToStringOneWayCollectionMapper(
            OneWayMapper<Interest, String> mapper) {
        return new SimpleOneWayCollectionMapper<>(mapper);
    }

    /*
     * LeadershipSkill
     */

    @Bean
    public Function<String, Optional<LeadershipSkill>> findLeadershipSkillFunction(LeadershipSkillService leadershipSkillService) {
        return leadershipSkillService::findLeadershipSkillByName;
    }

    @Bean
    public OneWayCollectionMapper<String, LeadershipSkill> leadershipSkillModelToEntityOneWayCollectionMapper(
            OneWayMapper<String, LeadershipSkill> mapper) {
        return new SimpleOneWayCollectionMapper<>(mapper);
    }

    @Bean
    public OneWayCollectionMapper<LeadershipSkill, String> leadershipSkillEntityToModelOneWayCollectionMapper(
            OneWayMapper<LeadershipSkill, String> mapper) {
        return new SimpleOneWayCollectionMapper<>(mapper);
    }

    /*
     * LeadershipTrait
     */

    @Bean
    public Function<String, Optional<LeadershipTrait>> findLeadershipTraitFunction(LeadershipTraitService leadershipTraitService) {
        return leadershipTraitService::findLeadershipTraitByName;
    }

    @Bean
    public OneWayCollectionMapper<String, LeadershipTrait> leadershipTraitModelToEntityOneWayCollectionMapper(
            OneWayMapper<String, LeadershipTrait> mapper) {
        return new SimpleOneWayCollectionMapper<>(mapper);
    }

    @Bean
    public OneWayCollectionMapper<LeadershipTrait, String> leadershipTraitEntityToModelOneWayCollectionMapper(
            OneWayMapper<LeadershipTrait, String> mapper) {
        return new SimpleOneWayCollectionMapper<>(mapper);
    }

    /*
     * Mentor
     */

    @Bean
    public OneWayMapper<InboundMentor, SchoolPersonRole> mentorModelToEntityMapper(
            OneWayUpdateMapper<InboundMentor, SchoolPersonRole> mapper) {
        return new UpdateMapperBasedOneWayMapper<>(mapper, SchoolPersonRole.class);
    }

    @Bean("mentorAssemblerMapperAdaptor")
    public OneWayMapper<SchoolPersonRole, OutboundMentor> mentorAssemblerMapperAdaptor(MentorModelAssembler mentorModelAssembler) {
        return new AssemblerMapperAdaptor<>(mentorModelAssembler,
                (mentorModel, role) -> Arrays.asList(
                        linkTo(MentorController.class, role.getSchool().getId()).slash(role.getId()).withSelfRel()
                )
        );
    }

    /*
     * Person
     */

    @Bean
    public OneWayMapper<InboundPerson, Person> personModelToEntityMapper(OneWayUpdateMapper<InboundPerson, Person> mapper) {
        return new UpdateMapperBasedOneWayMapper<>(mapper, Person.class);
    }

    @Bean("personAssemblerMapperAdaptor")
    public OneWayMapper<Person, OutboundPerson> personAssemblerMapperAdaptor(PersonModelAssembler personModelAssembler) {
        return new AssemblerMapperAdaptor<>(personModelAssembler,
                (personModel, person) -> Arrays.asList(
                        linkTo(PersonController.class).slash(person.getId()).withSelfRel()
                )
        );
    }

    /*
     * Personnel
     */

    @Bean
    public OneWayMapper<InboundPersonnel, SchoolPersonRole> personnelModelToEntityMapper(OneWayUpdateMapper<InboundPersonnel, SchoolPersonRole> mapper) {
        return new UpdateMapperBasedOneWayMapper<>(mapper, SchoolPersonRole.class);
    }

    /*
     * Phonogram
     */

    @Bean
    public Function<String, Optional<Phonogram>> findPhonogramFunction(PhonogramService phonogramService) {
        return phonogramService::findPhonogramByName;
    }

    @Bean
    public OneWayCollectionMapper<String, Phonogram> phonogramModelToEntityOneWayCollectionMapper(
            OneWayMapper<String, Phonogram> mapper) {
        return new SimpleOneWayCollectionMapper<>(mapper);
    }

    @Bean
    public OneWayCollectionMapper<Phonogram, String> phonogramEntityToModelOneWayCollectionMapper(
            OneWayMapper<Phonogram, String> mapper) {
        return new SimpleOneWayCollectionMapper<>(mapper);
    }

    /*
     * ProgramAdmin
     */

    @Bean
    public OneWayMapper<Pair<InboundProgramAdmin, UUID>, SchoolPersonRole> programAdminModelToEntityMapper(
            OneWayUpdateMapper<Pair<InboundProgramAdmin, UUID>, SchoolPersonRole> mapper) {
        return new UpdateMapperBasedOneWayMapper<>(mapper, SchoolPersonRole.class);
    }

    /*
     * School
     */

    @Bean
    public OneWayMapper<InboundSchool, School> schoolModelToEntityMapper(OneWayUpdateMapper<InboundSchool, School> mapper) {
        return new UpdateMapperBasedOneWayMapper<>(mapper, School.class);
    }

    /*
     * Student
     */

    @Bean
    public OneWayMapper<InboundStudent, Student> studentModelToEntityMapper(OneWayUpdateMapper<InboundStudent, Student> mapper) {
        return new UpdateMapperBasedOneWayMapper<>(mapper, Student.class);
    }

    /*
     * StudentBehavior
     */

    @Bean
    public CollectionSynchronizer<StudentBehavior> studentBehaviorCollectionSyncHelper() {
        return new SimpleCollectionSynchronizer<>();
    }

    /*
     * StudentLeadershipSkill
     */

    @Bean
    public CollectionSynchronizer<StudentLeadershipSkill> studentLeadershipSkillCollectionSyncHelper() {
        return new SimpleCollectionSynchronizer<>();
    }

    /*
     * StudentLeadershipTrait
     */

    @Bean
    public CollectionSynchronizer<StudentLeadershipTrait> studentLeadershipTraitCollectionSyncHelper() {
        return new SimpleCollectionSynchronizer<>();
    }

    /*
     * StudentPerson
     */

    @Bean
    public CollectionSynchronizer<StudentPersonRole> studentPersonRoleCollectionSyncHelper() {
        return new SimpleCollectionSynchronizer<>();
    }

    @Bean
    public OneWayMapper<InboundContact, StudentPersonRole> studentPersonModelToEntityMapper(
            OneWayUpdateMapper<InboundContact, StudentPersonRole> mapper) {
        return new UpdateMapperBasedOneWayMapper<>(mapper, StudentPersonRole.class);
    }

    @Bean
    public OneWayCollectionMapper<StudentPersonRole, OutboundContact> studentPersonEntityToModelOneWayCollectionMapper(
            OneWayMapper<StudentPersonRole, OutboundContact> mapper) {
        return new SimpleOneWayCollectionMapper<>(mapper);
    }

    /*
     * StudentTeacher
     */

    @Bean
    public OneWayMapper<InboundStudentTeacher, StudentTeacher> studentTeacherModelToEntityMapper(
            OneWayUpdateMapper<InboundStudentTeacher, StudentTeacher> mapper) {
        return new UpdateMapperBasedOneWayMapper<>(mapper, StudentTeacher.class);
    }

    /*
     * Teacher
     */

    @Bean
    public OneWayMapper<InboundTeacher, SchoolPersonRole> teacherModelToEntityMapper(OneWayUpdateMapper<InboundTeacher, SchoolPersonRole> mapper) {
        return new UpdateMapperBasedOneWayMapper<>(mapper, SchoolPersonRole.class);
    }

    @Bean("teacherAssemblerMapperAdaptor")
    public OneWayMapper<SchoolPersonRole, OutboundTeacher> teacherAssemblerMapperAdaptor(TeacherModelAssembler teacherModelAssembler) {
        return new AssemblerMapperAdaptor<>(teacherModelAssembler,
                (teacherModel, role) -> Arrays.asList(
                        linkTo(TeacherController.class, role.getSchool().getId()).slash(role.getId()).withSelfRel()
                )
        );
    }

}
