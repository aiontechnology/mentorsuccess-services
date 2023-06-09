/*
 * Copyright 2022 Aion Technology LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.aiontechnology.mentorsuccess.api.mapping.toentity.misc;

import io.aiontechnology.atlas.mapping.OneWayMapper;
import io.aiontechnology.mentorsuccess.entity.Student;
import io.aiontechnology.mentorsuccess.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Whitney Hunter
 * @since 1.8.0
 */
@Component
@RequiredArgsConstructor
public class UriModelToStudentMapper implements OneWayMapper<URI, Student> {

    private final Pattern STUDENT_PATTERN = Pattern.compile("http?://.*/api/v1/schools/.*/students/(.*)");

    private final StudentService studentService;

    @Override
    public Optional<Student> map(URI studentUri) {
        return Optional.ofNullable(studentUri)
                .flatMap(u -> {
                    UUID studentId = null;

                    Matcher matcher = STUDENT_PATTERN.matcher(studentUri.toString());
                    if (matcher.find()) {
                        studentId = UUID.fromString(matcher.group(1));
                    }

                    return studentId != null ? studentService.getStudentById(studentId) : Optional.empty();
                });
    }

}
