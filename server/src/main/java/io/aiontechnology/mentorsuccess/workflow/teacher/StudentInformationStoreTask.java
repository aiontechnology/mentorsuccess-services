/*
 * Copyright 2023 Aion Technology LLC
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

package io.aiontechnology.mentorsuccess.workflow.teacher;

import io.aiontechnology.atlas.mapping.OneWayMapper;
import io.aiontechnology.mentorsuccess.entity.School;
import io.aiontechnology.mentorsuccess.entity.SchoolPersonRole;
import io.aiontechnology.mentorsuccess.entity.SchoolSession;
import io.aiontechnology.mentorsuccess.entity.Student;
import io.aiontechnology.mentorsuccess.entity.StudentBehavior;
import io.aiontechnology.mentorsuccess.entity.StudentLeadershipSkill;
import io.aiontechnology.mentorsuccess.entity.StudentLeadershipTrait;
import io.aiontechnology.mentorsuccess.entity.StudentSchoolSession;
import io.aiontechnology.mentorsuccess.entity.reference.Behavior;
import io.aiontechnology.mentorsuccess.entity.reference.LeadershipSkill;
import io.aiontechnology.mentorsuccess.entity.reference.LeadershipTrait;
import io.aiontechnology.mentorsuccess.model.inbound.student.InboundStudentInformation;
import io.aiontechnology.mentorsuccess.service.StudentService;
import io.aiontechnology.mentorsuccess.workflow.TaskUtilities;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentInformationStoreTask implements JavaDelegate {

    // Services
    private final StudentService studentService;

    // Mappers
    private final OneWayMapper<String, Behavior> behaviorMapper;
    private final OneWayMapper<String, LeadershipSkill> leadershipSkillMapper;
    private final OneWayMapper<String, LeadershipTrait> leadershipTraitMapper;

    // Other
    private final TaskUtilities taskUtilities;

    @Override
    public void execute(DelegateExecution execution) {
        School school = taskUtilities.getSchool(execution).orElseThrow();
        SchoolSession currentSchoolSession = school.getCurrentSession();
        Student student = taskUtilities.getStudent(execution).orElseThrow();
        SchoolPersonRole teacher = taskUtilities.getTeacher(execution).orElseThrow();
        StudentSchoolSession currentStudentSchoolSession =
                student.findCurrentSessionForStudent(currentSchoolSession).orElseThrow();

        taskUtilities.getInboundStudentInformation(execution)
                .ifPresent(studentInformation -> {
                    currentStudentSchoolSession.setStudentBehaviors(mapBehaviors(studentInformation, teacher));
                    currentStudentSchoolSession
                            .setStudentLeadershipSkills(mapLeadershipSkills(studentInformation, teacher));
                    currentStudentSchoolSession
                            .setStudentLeadershipTraits(mapLeadershipTraits(studentInformation, teacher));
                    currentStudentSchoolSession.setTeacherComment(studentInformation.getTeacherComment());
                    currentStudentSchoolSession.setQuestion1(studentInformation.getQuestion1());
                    currentStudentSchoolSession.setQuestion2(studentInformation.getQuestion2());
                    currentStudentSchoolSession.setQuestion3(studentInformation.getQuestion3());
                    currentStudentSchoolSession.setQuestion4(studentInformation.getQuestion4());
                    currentStudentSchoolSession.setQuestion5(studentInformation.getQuestion5());
                    currentStudentSchoolSession.setQuestion6(studentInformation.getQuestion6());
                    currentStudentSchoolSession.setQuestion7(studentInformation.getQuestion7());
                    currentStudentSchoolSession.setQuestion8(studentInformation.getQuestion8());
                    currentStudentSchoolSession.setQuestion9(studentInformation.getQuestion9());
                    currentStudentSchoolSession.setQuestion10(studentInformation.getQuestion10());
                    currentStudentSchoolSession.setQuestion11(studentInformation.getQuestion11());
                    currentStudentSchoolSession.setQuestion12(studentInformation.getQuestion12());
                    currentStudentSchoolSession.setQuestion13(studentInformation.getQuestion13());
                    currentStudentSchoolSession.setQuestion14(studentInformation.getQuestion14());
                    currentStudentSchoolSession.setQuestion15(studentInformation.getQuestion15());
                    currentStudentSchoolSession.setQuestion16(studentInformation.getQuestion16());
                    currentStudentSchoolSession.setQuestion17(studentInformation.getQuestion17());
                    currentStudentSchoolSession.setQuestion18(studentInformation.getQuestion18());
                    currentStudentSchoolSession.setQuestion19(studentInformation.getQuestion19());
                    currentStudentSchoolSession.setQuestion20(studentInformation.getQuestion20());
                    currentStudentSchoolSession.setQuestion21(studentInformation.getQuestion21());
                    currentStudentSchoolSession.setQuestion22(studentInformation.getQuestion22());
                    currentStudentSchoolSession.setQuestion23(studentInformation.getQuestion23());
                    currentStudentSchoolSession.setQuestion24(studentInformation.getQuestion24());
                    currentStudentSchoolSession.setQuestion25(studentInformation.getQuestion25());
                    currentStudentSchoolSession.setQuestion26(studentInformation.getQuestion26());
                    currentStudentSchoolSession.setQuestion27(studentInformation.getQuestion27());
                    currentStudentSchoolSession.setQuestion28(studentInformation.getQuestion28());
                    currentStudentSchoolSession.setQuestion29(studentInformation.getQuestion29());
                    currentStudentSchoolSession.setQuestion30(studentInformation.getQuestion30());
                    currentStudentSchoolSession.setQuestion31(studentInformation.getQuestion31());
                    currentStudentSchoolSession.setQuestion32(studentInformation.getQuestion32());
                    currentStudentSchoolSession.setQuestion33(studentInformation.getQuestion33());
                    currentStudentSchoolSession.setQuestion34(studentInformation.getQuestion34());
                    currentStudentSchoolSession.setQuestion35(studentInformation.getQuestion35());
                    currentStudentSchoolSession.setPreBehavioralAssessment(
                            studentInformation.getQuestion1() +
                                    studentInformation.getQuestion2() +
                                    studentInformation.getQuestion3() +
                                    studentInformation.getQuestion4() +
                                    studentInformation.getQuestion5() +
                                    studentInformation.getQuestion6() +
                                    studentInformation.getQuestion7() +
                                    studentInformation.getQuestion8() +
                                    studentInformation.getQuestion9() +
                                    studentInformation.getQuestion10() +
                                    studentInformation.getQuestion11() +
                                    studentInformation.getQuestion12() +
                                    studentInformation.getQuestion13() +
                                    studentInformation.getQuestion14() +
                                    studentInformation.getQuestion15() +
                                    studentInformation.getQuestion16() +
                                    studentInformation.getQuestion17() +
                                    studentInformation.getQuestion18() +
                                    studentInformation.getQuestion19() +
                                    studentInformation.getQuestion20() +
                                    studentInformation.getQuestion21() +
                                    studentInformation.getQuestion22() +
                                    studentInformation.getQuestion23() +
                                    studentInformation.getQuestion24() +
                                    studentInformation.getQuestion25() +
                                    studentInformation.getQuestion26() +
                                    studentInformation.getQuestion27() +
                                    studentInformation.getQuestion28() +
                                    studentInformation.getQuestion29() +
                                    studentInformation.getQuestion30() +
                                    studentInformation.getQuestion31() +
                                    studentInformation.getQuestion32() +
                                    studentInformation.getQuestion33() +
                                    studentInformation.getQuestion34() +
                                    studentInformation.getQuestion35()
                    );
                    studentService.updateStudent(student);
                });
    }

    private Collection<StudentBehavior> mapBehaviors(InboundStudentInformation studentInformation,
            SchoolPersonRole teacher) {
        return studentInformation.getBehaviors().stream()
                .map(behaviorMapper::map)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(behavior -> {
                    StudentBehavior studentBehavior = new StudentBehavior();
                    studentBehavior.setBehavior(behavior);
                    studentBehavior.setRole(teacher);
                    return studentBehavior;
                })
                .collect(Collectors.toList());
    }

    private Collection<StudentLeadershipSkill> mapLeadershipSkills(InboundStudentInformation studentInformation,
            SchoolPersonRole teacher) {
        return studentInformation.getLeadershipSkills().stream()
                .map(leadershipSkillMapper::map)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(leadershipSkill -> {
                    StudentLeadershipSkill studentLeadershipSkill = new StudentLeadershipSkill();
                    studentLeadershipSkill.setLeadershipSkill(leadershipSkill);
                    studentLeadershipSkill.setRole(teacher);
                    return studentLeadershipSkill;
                })
                .collect(Collectors.toList());
    }

    private Collection<StudentLeadershipTrait> mapLeadershipTraits(InboundStudentInformation studentInformation,
            SchoolPersonRole teacher) {
        return studentInformation.getLeadershipTraits().stream()
                .map(leadershipTraitMapper::map)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(leadershipTrait -> {
                    StudentLeadershipTrait studentLeadershipTrait = new StudentLeadershipTrait();
                    studentLeadershipTrait.setLeadershipTrait(leadershipTrait);
                    studentLeadershipTrait.setRole(teacher);
                    return studentLeadershipTrait;
                })
                .collect(Collectors.toList());
    }

}
