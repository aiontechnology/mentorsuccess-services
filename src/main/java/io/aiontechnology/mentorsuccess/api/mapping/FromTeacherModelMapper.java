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

package io.aiontechnology.mentorsuccess.api.mapping;

import io.aiontechnology.mentorsuccess.api.model.TeacherModel;
import io.aiontechnology.mentorsuccess.entity.Person;
import io.aiontechnology.mentorsuccess.entity.Role;
import io.aiontechnology.mentorsuccess.util.PhoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static io.aiontechnology.mentorsuccess.entity.Role.RoleType.TEACHER;

/**
 * @author Whitney Hunter
 */
@Component
@RequiredArgsConstructor
public class FromTeacherModelMapper implements MutableMapper<TeacherModel, Role> {

    private final PhoneService phoneService;

    @Override
    public Role map(TeacherModel teacherModel) {
        Role role = new Role();
        return map(teacherModel, role);
    }

    @Override
    public Role map(TeacherModel teacherModel, Role role) {
        Person person = new Person();
        person.setFirstName(teacherModel.getFirstName());
        person.setLastName(teacherModel.getLastName());
        person.setEmail(teacherModel.getEmail());
        person.setHomePhone(phoneService.normalize(teacherModel.getHomePhone()));
        person.setCellPhone(phoneService.normalize(teacherModel.getCellPhone()));

        role.setType(TEACHER);
        role.setGrade1(teacherModel.getGrade1());
        role.setGrade2(teacherModel.getGrade2());
        role.setPerson(person);
        role.setIsActive(true);
        return role;
    }

}
