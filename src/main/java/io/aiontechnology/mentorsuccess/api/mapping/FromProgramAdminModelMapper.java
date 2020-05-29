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
import io.aiontechnology.mentorsuccess.entity.Role;
import io.aiontechnology.mentorsuccess.util.PhoneService;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

import static io.aiontechnology.mentorsuccess.entity.Role.RoleType.PROGRAM_ADMIN;

/**
 * @author Whitney Hunter
 */
@Component
public class FromProgramAdminModelMapper extends BaseFromPersonnelModelMapper<ProgramAdminModel> {

    @Inject
    public FromProgramAdminModelMapper(PhoneService phoneService) {
        super(phoneService);
    }

    protected Role doMapRole(ProgramAdminModel programAdminModel, Role role) {
        role.setType(PROGRAM_ADMIN);
        return role;
    }

}
