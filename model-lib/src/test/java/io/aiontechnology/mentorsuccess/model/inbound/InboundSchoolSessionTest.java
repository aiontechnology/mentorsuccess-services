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

package io.aiontechnology.mentorsuccess.model.inbound;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import javax.validation.ConstraintViolation;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Stream;

import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Whitney Hunter
 * @since 1.8.0
 */
public class InboundSchoolSessionTest extends BaseValidatorTest {

    @Test
    void testValid() {
        // set up the fixture
        InboundSchoolSession inboundSchoolSession = InboundSchoolSession.builder()
                .withStartDate(LocalDate.of(2022, JANUARY, 1))
                .withEndDate(LocalDate.of(2022, DECEMBER, 31))
                .withLabel("TEST")
                .build();

        // execute the SUT
        Set<ConstraintViolation<InboundSchoolSession>> constraintViolations =
                getValidator().validate(inboundSchoolSession);

        // validation
        assertThat(constraintViolations.size()).isEqualTo(0);
    }

    @ParameterizedTest
    @MethodSource("sessionInstanceProvider")
    void testInvalid(Pair<InboundSchoolSession, String> sessionInstance) {
        // set up the fixture

        // execute the SUT
        Set<ConstraintViolation<InboundSchoolSession>> constraintViolations =
                getValidator().validate(sessionInstance.getLeft());

        // validation
        assertThat(constraintViolations.size()).isEqualTo(1);
        assertThat(constraintViolations.iterator().next().getMessage()).isEqualTo(sessionInstance.getRight());
    }

    private static Stream<ImmutablePair<InboundSchoolSession, String>> sessionInstanceProvider() {
        InboundSchoolSession nullStartDate = InboundSchoolSession.builder()
                .withStartDate(null)
                .withEndDate(LocalDate.now())
                .withLabel("LABEL")
                .build();
        InboundSchoolSession nullEndDate = InboundSchoolSession.builder()
                .withStartDate(LocalDate.now())
                .withEndDate(null)
                .withLabel("LABEL")
                .build();
        InboundSchoolSession nullLabel = InboundSchoolSession.builder()
                .withStartDate(LocalDate.now())
                .withEndDate(LocalDate.now())
                .withLabel(null)
                .build();
        return Stream.of(ImmutablePair.of(nullStartDate, "{schoolsession.startdate.notNull}"),
                ImmutablePair.of(nullEndDate, "{schoolsession.enddate.notNull}"),
                ImmutablePair.of(nullLabel, "{schoolsession.label.notNull}"));
    }

}
