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

package io.aiontechnology.mentorsuccess.api.mapping.toentity.person;

import io.aiontechnology.mentorsuccess.api.mapping.OneWayUpdateMapper;
import io.aiontechnology.mentorsuccess.api.model.inbound.TeacherModel;
import io.aiontechnology.mentorsuccess.entity.Person;
import io.aiontechnology.mentorsuccess.entity.SchoolPersonRole;
import io.aiontechnology.mentorsuccess.util.PhoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

import static io.aiontechnology.mentorsuccess.entity.RoleType.TEACHER;

/**
 * @author Whitney Hunter
 * @since 0.3.0
 */
@Component
@RequiredArgsConstructor
public class TeacherModelToEntityUpdateMapper implements OneWayUpdateMapper<TeacherModel, SchoolPersonRole> {

    /** Phone number formatting service */
    private final PhoneService phoneService;

    /**
     * Map the given {@link TeacherModel} ot the given {@link SchoolPersonRole}.
     *
     * @param teacherModel The {@link TeacherModel} to map.
     * @param role The {@link SchoolPersonRole} to map to.
     * @return The resulting {@link SchoolPersonRole}.
     */
    @Override
    public Optional<SchoolPersonRole> map(TeacherModel teacherModel, SchoolPersonRole role) {
        Objects.requireNonNull(role);
        return Optional.ofNullable(teacherModel)
                .map(t -> {
                    Person person = role.getPerson() != null ? role.getPerson() : new Person();
                    person.setFirstName(t.getFirstName());
                    person.setLastName(t.getLastName());
                    person.setEmail(t.getEmail());
                    person.setWorkPhone(phoneService.normalize(t.getWorkPhone()));
                    person.setCellPhone(phoneService.normalize(t.getCellPhone()));

                    role.setPerson(person);
                    role.setIsActive(true);
                    role.setGrade1(teacherModel.getGrade1());
                    role.setGrade2(teacherModel.getGrade2());
                    role.setType(TEACHER);
                    return role;
                });

    }

}
