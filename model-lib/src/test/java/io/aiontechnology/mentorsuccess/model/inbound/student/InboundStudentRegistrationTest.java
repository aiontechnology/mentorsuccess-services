/*
 * Copyright 2022 Aion Technology LLC
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

package io.aiontechnology.mentorsuccess.model.inbound.student;

import io.aiontechnology.mentorsuccess.model.inbound.BaseValidatorTest;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import javax.validation.ConstraintViolation;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Whitney Hunter
 * @since 1.8.0
 */
public class InboundStudentRegistrationTest extends BaseValidatorTest {

    @Test
    void testValid() {
        // set up the fixture
        InboundStudentRegistration inboundStudentRegistration = InboundStudentRegistration.builder()
                .withFirstName("FIRST")
                .withLastName("LAST")
                .withParentEmailAddress("test@test.com")
                .build();

        // execute the SUT
        Set<ConstraintViolation<InboundStudentRegistration>> constraintViolations =
                getValidator().validate(inboundStudentRegistration);

        // validation
        assertThat(constraintViolations.size()).isEqualTo(0);
    }

    @ParameterizedTest
    @MethodSource("studentRegistrationInstanceProvider")
    void testInvalid(Pair<InboundStudentRegistration, String> studentRegistrationInstance) {
        // set up the fixture

        // execute the SUT
        Set<ConstraintViolation<InboundStudentRegistration>> constraintViolations =
                getValidator().validate(studentRegistrationInstance.getLeft());

        // validation
        assertThat(constraintViolations.size()).isEqualTo(1);
        assertThat(constraintViolations.iterator().next().getMessage()).isEqualTo(studentRegistrationInstance.getRight());
    }

    private static Stream<Pair<InboundStudentRegistration, String>> studentRegistrationInstanceProvider() {
        InboundStudentRegistration nullFirstName = InboundStudentRegistration.builder()
                .withFirstName(null)
                .withLastName("LAST")
                .withParentEmailAddress("test@test.com")
                .build();
        InboundStudentRegistration firstNameTooLong = InboundStudentRegistration.builder()
                .withFirstName("123456789012345678901234567890123456789012345678901") // 51 characters
                .withLastName("LAST")
                .withParentEmailAddress("test@test.com")
                .build();
        InboundStudentRegistration nullLastName = InboundStudentRegistration.builder()
                .withFirstName("FIRST")
                .withLastName(null)
                .withParentEmailAddress("test@test.com")
                .build();
        InboundStudentRegistration lastNameTooLong = InboundStudentRegistration.builder()
                .withFirstName("FIRST")
                .withLastName("123456789012345678901234567890123456789012345678901") // 51 characters
                .withParentEmailAddress("test@test.com")
                .build();
        InboundStudentRegistration nullParentEmail = InboundStudentRegistration.builder()
                .withFirstName("FIRST")
                .withLastName("LAST")
                .withParentEmailAddress(null)
                .build();
        InboundStudentRegistration parentEmailTooLong = InboundStudentRegistration.builder()
                .withFirstName("FIRST")
                .withLastName("LAST")
                .withParentEmailAddress("123456789012345678901234567890123456789012@test.com") // 51 characters
                .build();
        InboundStudentRegistration parentEmailPattern = InboundStudentRegistration.builder()
                .withFirstName("FIRST")
                .withLastName("LAST")
                .withParentEmailAddress("invalid")
                .build();
        return Stream.of(ImmutablePair.of(nullFirstName, "{registration.firstname.notNull}"),
                ImmutablePair.of(firstNameTooLong, "{student.firstname.size}"),
                ImmutablePair.of(nullLastName, "{registration.lastname.notNull}"),
                ImmutablePair.of(lastNameTooLong, "{student.lastname.size}"),
                ImmutablePair.of(nullParentEmail, "{registration.email.notNull}"),
                ImmutablePair.of(parentEmailTooLong, "{registration.email.size}"),
                ImmutablePair.of(parentEmailPattern, "{registration.email.invalid}"));
    }

}
