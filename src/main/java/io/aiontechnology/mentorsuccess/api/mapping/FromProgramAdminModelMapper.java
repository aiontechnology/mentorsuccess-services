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

import io.aiontechnology.mentorsuccess.api.model.ProgramAdminModel;
import io.aiontechnology.mentorsuccess.entity.Person;
import io.aiontechnology.mentorsuccess.entity.Role;
import io.aiontechnology.mentorsuccess.util.PhoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static io.aiontechnology.mentorsuccess.entity.Role.RoleType.PROGRAM_ADMIN;

/**
 * @author Whitney Hunter
 */
@Component
@RequiredArgsConstructor
public class FromProgramAdminModelMapper implements MutableMapper<ProgramAdminModel, Role> {

    private final PhoneService phoneService;

    @Override
    public Role map(ProgramAdminModel programAdminModel) {
        Role role = new Role();
        return map(programAdminModel, role);
    }

    @Override
    public Role map(ProgramAdminModel programAdminModel, Role role) {
        String phone = programAdminModel.getHomePhone()
                .replace("(", "")
                .replace(")", "")
                .replace("-", "")
                .replace(" ", "");

        Person person = new Person();
        person.setName(programAdminModel.getName());
        person.setEmail(programAdminModel.getEmail());
        person.setHomePhone(phoneService.normalize(programAdminModel.getHomePhone()));
        person.setCellPhone(phoneService.normalize(programAdminModel.getCellPhone()));

        role.setType(PROGRAM_ADMIN);
        role.setPerson(person);
        role.setIsActive(true);
        return role;
    }

}
