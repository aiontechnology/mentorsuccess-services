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

package io.aiontechnology.mentorsuccess.service;

import io.aiontechnology.mentorsuccess.entity.Role;
import io.aiontechnology.mentorsuccess.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

/**
 * Service that provides business logic for roles.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@Service
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class RoleService {

    /** The repository used to interact with the database */
    private final RoleRepository roleRepository;

    /**
     * Create a role in the database by saving the provided {@link Role}.
     *
     * @param role The {@link Role} to save.
     * @return The resulting {@link Role}. Will have a db generated id populated.
     */
    @Transactional
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    /**
     * Deactivate a {@link Role} in the system.
     *
     * @param role The {@link Role} to deactivate.
     */
    @Transactional
    public Role deactivateRole(Role role) {
        role.setIsActive(false);
        roleRepository.save(role);
        return role;
    }

    /**
     * Find a {@link Role} by its id.
     *
     * @param id The id of the desired {@link Role}.
     * @return The {@link Role} if it could be found.
     */
    public Optional<Role> findRoleById(UUID id) {
        return roleRepository.findById(id);
    }

    /**
     * Update the given {@link Role} in the database.
     *
     * @param role The {@link Role} to update.
     * @return The updated {@link Role}.
     */
    @Transactional
    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }

}
