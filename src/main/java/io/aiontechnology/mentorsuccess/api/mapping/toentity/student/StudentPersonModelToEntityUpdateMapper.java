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

package io.aiontechnology.mentorsuccess.api.mapping.toentity.student;

import io.aiontechnology.mentorsuccess.api.mapping.OneWayMapper;
import io.aiontechnology.mentorsuccess.api.mapping.OneWayUpdateMapper;
import io.aiontechnology.mentorsuccess.api.model.inbound.PersonModel;
import io.aiontechnology.mentorsuccess.api.model.inbound.student.InboundEmergencyContactModel;
import io.aiontechnology.mentorsuccess.entity.Person;
import io.aiontechnology.mentorsuccess.entity.StudentPerson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Whitney Hunter
 * @since 0.3.0
 */
@Component
@RequiredArgsConstructor
public class StudentPersonModelToEntityUpdateMapper implements OneWayUpdateMapper<InboundEmergencyContactModel, StudentPerson> {

    private final OneWayMapper<PersonModel, Person> personModelToEntityMapper;

    @Override
    public Optional<StudentPerson> map(InboundEmergencyContactModel inboundEmergencyContactModel, StudentPerson studentPerson) {
        return Optional.ofNullable(inboundEmergencyContactModel)
                .map(s -> {
                    studentPerson.setPersonType(inboundEmergencyContactModel.getType());
                    PersonModel personModel = PersonModel.builder()
                            .withFirstName(inboundEmergencyContactModel.getFirstName())
                            .withLastName(inboundEmergencyContactModel.getLastName())
                            .withWorkPhone(inboundEmergencyContactModel.getWorkPhone())
                            .withCellPhone(inboundEmergencyContactModel.getCellPhone())
                            .withEmail(inboundEmergencyContactModel.getEmail())
                            .build();
                    studentPerson.setPerson(personModelToEntityMapper.map(personModel)
                            .orElse(null));
                    return studentPerson;
                });
    }

}
