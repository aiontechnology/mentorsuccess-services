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

import io.aiontechnology.mentorsuccess.api.assembler.PersonModelAssembler;
import io.aiontechnology.mentorsuccess.api.assembler.TeacherModelAssembler;
import io.aiontechnology.mentorsuccess.api.controller.PersonController;
import io.aiontechnology.mentorsuccess.api.controller.TeacherController;
import io.aiontechnology.mentorsuccess.api.mapping.AssemblerMapperAdaptor;
import io.aiontechnology.mentorsuccess.api.mapping.CollectionSyncHelper;
import io.aiontechnology.mentorsuccess.api.mapping.ModelCollectionToEntityCollectionMapper;
import io.aiontechnology.mentorsuccess.api.mapping.OneWayCollectionMapper;
import io.aiontechnology.mentorsuccess.api.mapping.OneWayMapper;
import io.aiontechnology.mentorsuccess.api.mapping.OneWayUpdateMapper;
import io.aiontechnology.mentorsuccess.api.mapping.UpdateMapperBasedOneWayMapper;
import io.aiontechnology.mentorsuccess.api.model.inbound.ActivityFocusModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.BookModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.GameModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.InboundMentorModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.PersonModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.PersonnelModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.PhonogramModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.SchoolModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.TeacherModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.reference.BehaviorModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.reference.InterestModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.reference.LeadershipSkillModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.reference.LeadershipTraitModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.reference.ProgramAdminModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.student.InboundContactModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.student.InboundStudentModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.student.InboundStudentTeacherModel;
import io.aiontechnology.mentorsuccess.api.model.outbound.student.OutboundContactModel;
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
import io.aiontechnology.mentorsuccess.entity.StudentStaff;
import io.aiontechnology.mentorsuccess.entity.reference.Behavior;
import io.aiontechnology.mentorsuccess.entity.reference.Interest;
import io.aiontechnology.mentorsuccess.entity.reference.LeadershipSkill;
import io.aiontechnology.mentorsuccess.entity.reference.LeadershipTrait;
import io.aiontechnology.mentorsuccess.entity.reference.Phonogram;
import io.aiontechnology.mentorsuccess.service.ActivityFocusService;
import io.aiontechnology.mentorsuccess.service.BehaviorService;
import io.aiontechnology.mentorsuccess.service.InterestService;
import io.aiontechnology.mentorsuccess.service.LeadershipSkillService;
import io.aiontechnology.mentorsuccess.service.LeadershipTraitService;
import io.aiontechnology.mentorsuccess.service.PhonogramService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Optional;
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
    public OneWayCollectionMapper<ActivityFocusModel, ActivityFocus> activityFocusModelToEntityOneWayCollectionMapper(
            OneWayMapper<ActivityFocusModel, ActivityFocus> mapper) {
        return new ModelCollectionToEntityCollectionMapper<>(mapper);
    }

    @Bean
    public OneWayCollectionMapper<ActivityFocus, ActivityFocusModel> activityFocusEntityToModelOneWayCollectionMapper(
            OneWayMapper<ActivityFocus, ActivityFocusModel> mapper) {
        return new ModelCollectionToEntityCollectionMapper<>(mapper);
    }

    /*
     * Behavior
     */

    @Bean
    public Function<String, Optional<Behavior>> findBehaviorFunction(BehaviorService behaviorService) {
        return behaviorService::findBehaviorByName;
    }

    @Bean
    public OneWayCollectionMapper<BehaviorModel, Behavior> behaviorModelToEntityOneWayCollectionMapper(
            OneWayMapper<BehaviorModel, Behavior> mapper) {
        return new ModelCollectionToEntityCollectionMapper<>(mapper);
    }

    @Bean
    public OneWayCollectionMapper<Behavior, BehaviorModel> behaviorEntityToModelOneWayCollectionMapper(
            OneWayMapper<Behavior, BehaviorModel> mapper) {
        return new ModelCollectionToEntityCollectionMapper<>(mapper);
    }

    /*
     * Book
     */

    @Bean
    public OneWayMapper<BookModel, Book> bookModelToEntityMapper(OneWayUpdateMapper<BookModel, Book> mapper) {
        return new UpdateMapperBasedOneWayMapper<>(mapper, Book.class);
    }

    /*
     * Game
     */

    @Bean
    public OneWayMapper<GameModel, Game> gameModelToEntityMapper(OneWayUpdateMapper<GameModel, Game> mapper) {
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
    public OneWayCollectionMapper<InterestModel, Interest> interestModelToEntityOneWayCollectionMapper(
            OneWayMapper<InterestModel, Interest> mapper) {
        return new ModelCollectionToEntityCollectionMapper<>(mapper);
    }

    @Bean
    public OneWayCollectionMapper<Interest, InterestModel> interestEntityToModelOneWayCollectionMapper(
            OneWayMapper<Interest, InterestModel> mapper) {
        return new ModelCollectionToEntityCollectionMapper<>(mapper);
    }

    @Bean
    public OneWayCollectionMapper<Interest, String> interestEntityToStringOneWayCollectionMapper(
            OneWayMapper<Interest, String> mapper) {
        return new ModelCollectionToEntityCollectionMapper<>(mapper);
    }

    /*
     * LeadershipSkill
     */

    @Bean
    public Function<String, Optional<LeadershipSkill>> findLeadershipSkillFunction(LeadershipSkillService leadershipSkillService) {
        return leadershipSkillService::findLeadershipSkillByName;
    }

    @Bean
    public OneWayCollectionMapper<LeadershipSkillModel, LeadershipSkill> leadershipSkillModelToEntityOneWayCollectionMapper(
            OneWayMapper<LeadershipSkillModel, LeadershipSkill> mapper) {
        return new ModelCollectionToEntityCollectionMapper<>(mapper);
    }

    @Bean
    public OneWayCollectionMapper<LeadershipSkill, LeadershipSkillModel> leadershipSkillEntityToModelOneWayCollectionMapper(
            OneWayMapper<LeadershipSkill, LeadershipSkillModel> mapper) {
        return new ModelCollectionToEntityCollectionMapper<>(mapper);
    }

    /*
     * LeadershipTrait
     */

    @Bean
    public Function<String, Optional<LeadershipTrait>> findLeadershipTraitFunction(LeadershipTraitService leadershipTraitService) {
        return leadershipTraitService::findLeadershipTraitByName;
    }

    @Bean
    public OneWayCollectionMapper<LeadershipTraitModel, LeadershipTrait> leadershipTraitModelToEntityOneWayCollectionMapper(
            OneWayMapper<LeadershipTraitModel, LeadershipTrait> mapper) {
        return new ModelCollectionToEntityCollectionMapper<>(mapper);
    }

    @Bean
    public OneWayCollectionMapper<LeadershipTrait, LeadershipTraitModel> leadershipTraitEntityToModelOneWayCollectionMapper(
            OneWayMapper<LeadershipTrait, LeadershipTraitModel> mapper) {
        return new ModelCollectionToEntityCollectionMapper<>(mapper);
    }

    /*
     * Mentor
     */

    @Bean
    public OneWayMapper<InboundMentorModel, SchoolPersonRole> mentorModelToEntityMapper(
            OneWayUpdateMapper<InboundMentorModel, SchoolPersonRole> mapper) {
        return new UpdateMapperBasedOneWayMapper<>(mapper, SchoolPersonRole.class);
    }

    /*
     * Person
     */

    @Bean
    public OneWayMapper<PersonModel, Person> personModelToEntityMapper(OneWayUpdateMapper<PersonModel, Person> mapper) {
        return new UpdateMapperBasedOneWayMapper<>(mapper, Person.class);
    }

    @Bean("personAssemblerMapperAdaptor")
    public OneWayMapper<Person, PersonModel> personAssemblerMapperAdaptor(PersonModelAssembler personModelAssembler) {
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
    public OneWayMapper<PersonnelModel, SchoolPersonRole> personnelModelToEntityMapper(OneWayUpdateMapper<PersonnelModel, SchoolPersonRole> mapper) {
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
    public OneWayCollectionMapper<PhonogramModel, Phonogram> phonogramModelToEntityOneWayCollectionMapper(
            OneWayMapper<PhonogramModel, Phonogram> mapper) {
        return new ModelCollectionToEntityCollectionMapper<>(mapper);
    }

    @Bean
    public OneWayCollectionMapper<Phonogram, PhonogramModel> phonogramEntityToModelOneWayCollectionMapper(
            OneWayMapper<Phonogram, PhonogramModel> mapper) {
        return new ModelCollectionToEntityCollectionMapper<>(mapper);
    }

    /*
     * ProgramAdmin
     */

    @Bean
    public OneWayMapper<ProgramAdminModel, SchoolPersonRole> programAdminModelToEntityMapper(OneWayUpdateMapper<ProgramAdminModel, SchoolPersonRole> mapper) {
        return new UpdateMapperBasedOneWayMapper<>(mapper, SchoolPersonRole.class);
    }

    /*
     * School
     */

    @Bean
    public OneWayMapper<SchoolModel, School> schoolModelToEntityMapper(OneWayUpdateMapper<SchoolModel, School> mapper) {
        return new UpdateMapperBasedOneWayMapper<>(mapper, School.class);
    }

    /*
     * Student
     */

    @Bean
    public OneWayMapper<InboundStudentModel, Student> studentModelToEntityMapper(OneWayUpdateMapper<InboundStudentModel, Student> mapper) {
        return new UpdateMapperBasedOneWayMapper<>(mapper, Student.class);
    }

    /*
     * StudentBehavior
     */

    @Bean
    public CollectionSyncHelper<StudentBehavior> studentBehaviorCollectionSyncHelper() {
        return new CollectionSyncHelper<>();
    }

    /*
     * StudentLeadershipSkill
     */

    @Bean
    public CollectionSyncHelper<StudentLeadershipSkill> studentLeadershipSkillCollectionSyncHelper() {
        return new CollectionSyncHelper<>();
    }

    /*
     * StudentLeadershipTrait
     */

    @Bean
    public CollectionSyncHelper<StudentLeadershipTrait> studentLeadershipTraitCollectionSyncHelper() {
        return new CollectionSyncHelper<>();
    }

    /*
     * StudentPerson
     */

    @Bean
    public CollectionSyncHelper<StudentPersonRole> studentPersonRoleCollectionSyncHelper() {
        return new CollectionSyncHelper<>();
    }

    @Bean
    public OneWayMapper<InboundContactModel, StudentPersonRole> studentPersonModelToEntityMapper(
            OneWayUpdateMapper<InboundContactModel, StudentPersonRole> mapper) {
        return new UpdateMapperBasedOneWayMapper<>(mapper, StudentPersonRole.class);
    }

    @Bean
    public OneWayCollectionMapper<StudentPersonRole, OutboundContactModel> studentPersonEntityToModelOneWayCollectionMapper(
            OneWayMapper<StudentPersonRole, OutboundContactModel> mapper) {
        return new ModelCollectionToEntityCollectionMapper<>(mapper);
    }

    /*
     * StudentTeacher
     */

    @Bean
    public OneWayMapper<InboundStudentTeacherModel, StudentStaff> studentTeacherModelToEntityMapper(
            OneWayUpdateMapper<InboundStudentTeacherModel, StudentStaff> mapper) {
        return new UpdateMapperBasedOneWayMapper<>(mapper, StudentStaff.class);
    }

    /*
     * Teacher
     */

    @Bean
    public OneWayMapper<TeacherModel, SchoolPersonRole> teacherModelToEntityMapper(OneWayUpdateMapper<TeacherModel, SchoolPersonRole> mapper) {
        return new UpdateMapperBasedOneWayMapper<>(mapper, SchoolPersonRole.class);
    }

    @Bean("teacherAssemblerMapperAdaptor")
    public OneWayMapper<SchoolPersonRole, TeacherModel> teacherAssemblerMapperAdaptor(TeacherModelAssembler teacherModelAssembler) {
        return new AssemblerMapperAdaptor<>(teacherModelAssembler,
                (teacherModel, role) -> Arrays.asList(
                        linkTo(TeacherController.class, role.getSchool().getId()).slash(role.getId()).withSelfRel()
                )
        );
    }

}
