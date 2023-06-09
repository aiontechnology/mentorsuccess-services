/*
 * Copyright 2021 Aion Technology LLC
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.aiontechnology.mentorsuccess.security;

import io.aiontechnology.mentorsuccess.entity.SchoolPersonRole;
import io.aiontechnology.mentorsuccess.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Whitney Hunter
 * @since 0.12.0
 */
@Component
@RequiredArgsConstructor
public class UserClaimConverter implements Converter<Object, Optional<SchoolPersonRole>> {

    private final RoleService roleService;

    @Override
    public Optional<SchoolPersonRole> convert(Object username) {
        UUID idpUserId = UUID.fromString((String) username);
        return roleService.findRoleByIdpUserId(idpUserId);
    }

}
