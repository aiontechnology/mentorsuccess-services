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
import io.aiontechnology.mentorsuccess.api.model.inbound.student.InboundContactModel;
import io.aiontechnology.mentorsuccess.entity.Person;
import io.aiontechnology.mentorsuccess.entity.StudentPersonRole;
import io.aiontechnology.mentorsuccess.util.PhoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Whitney Hunter
 * @since 0.3.0
 */
@Component
@RequiredArgsConstructor
public class StudentPersonModelToEntityUpdateMapper implements OneWayUpdateMapper<InboundContactModel, StudentPersonRole> {

    private final OneWayMapper<PersonModel, Person> personModelToEntityMapper;

    private final PhoneService phoneService;

    @Override
    public Optional<StudentPersonRole> map(InboundContactModel inboundContactModel, StudentPersonRole studentPersonRole) {
        return Optional.ofNullable(inboundContactModel)
                .map(contactModel -> {
                    studentPersonRole.setPersonType(contactModel.getType());
                    PersonModel personModel = PersonModel.builder()
                            .withFirstName(contactModel.getFirstName())
                            .withLastName(contactModel.getLastName())
                            .withWorkPhone(phoneService.normalize(contactModel.getWorkPhone()))
                            .withCellPhone(phoneService.normalize(contactModel.getCellPhone()))
                            .withEmail(contactModel.getEmail())
                            .build();
                    studentPersonRole.setPerson(personModelToEntityMapper.map(personModel)
                            .orElse(null));
                    studentPersonRole.setIsEmergencyContact(contactModel.getIsEmergencyContact());
                    studentPersonRole.setPreferredContactMethod(contactModel.getPreferredContactMethod());
                    studentPersonRole.setComment(contactModel.getComment());
                    return studentPersonRole;
                });
    }

}
