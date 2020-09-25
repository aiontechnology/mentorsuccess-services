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

package io.aiontechnology.mentorsuccess.api.mapping.tomodel.misc;

import io.aiontechnology.mentorsuccess.api.controller.TeacherController;
import io.aiontechnology.mentorsuccess.api.mapping.OneWayBiMapper;
import io.aiontechnology.mentorsuccess.entity.Person;
import io.aiontechnology.mentorsuccess.entity.SchoolPersonRole;
import io.aiontechnology.mentorsuccess.entity.School;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.Objects;
import java.util.Optional;

import static io.aiontechnology.mentorsuccess.entity.RoleType.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Mapper that converts a {@link Person Teacher} to a URI.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
@Component
public class TeacherEntityToUriMapper implements OneWayBiMapper<School, SchoolPersonRole, URI> {

    /**
     * Map the given {@link Person Teacher} to a URI.
     *
     * @param role The {@link Person Teacher} to map.
     * @return The resulting URI.
     */
    @Override
    public Optional<URI> map(School school, SchoolPersonRole role) {
        Objects.requireNonNull(school);
        return Optional.ofNullable(role)
                .filter(r -> r.getType().equals(TEACHER))
                .map(r -> methodOn(TeacherController.class).getTeacher(school.getId(), r.getId()))
                .map(WebMvcLinkBuilder::linkTo)
                .map(WebMvcLinkBuilder::toUri);
    }

}
