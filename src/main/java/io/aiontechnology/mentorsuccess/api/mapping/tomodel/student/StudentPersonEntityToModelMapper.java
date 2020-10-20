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

package io.aiontechnology.mentorsuccess.api.mapping.tomodel.student;

import io.aiontechnology.mentorsuccess.api.mapping.OneWayMapper;
import io.aiontechnology.mentorsuccess.api.model.inbound.PersonModel;
import io.aiontechnology.mentorsuccess.api.model.outbound.student.OutboundEmergencyContactModel;
import io.aiontechnology.mentorsuccess.entity.Person;
import io.aiontechnology.mentorsuccess.entity.StudentPerson;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.Optional;

/**
 * @author Whitney Hunter
 * @since 0.3.0
 */
@Component
@RequiredArgsConstructor
@Getter(AccessLevel.PRIVATE)
public class StudentPersonEntityToModelMapper implements OneWayMapper<StudentPerson, OutboundEmergencyContactModel> {

    /** Mapper from {@link Person} to {@link URI}. */
    @Qualifier("personAssemblerMapperAdaptor")
    private final OneWayMapper<Person, PersonModel> personEntityToModelMapper;

    @Override
    public Optional<OutboundEmergencyContactModel> map(StudentPerson studentPerson) {
        return Optional.ofNullable(studentPerson)
                .map(s -> OutboundEmergencyContactModel.builder()
                        .withFirstName(studentPerson.getPerson().getFirstName())
                        .withLastName(studentPerson.getPerson().getLastName())
                        .withWorkPhone(studentPerson.getPerson().getWorkPhone())
                        .withCellPhone(studentPerson.getPerson().getCellPhone())
                        .withEmail(studentPerson.getPerson().getEmail())
                        .withType(studentPerson.getPersonType())
                        .build());
    }

}
