/*
 * Copyright 2023 Aion Technology LLC
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

package io.aiontechnology.mentorsuccess.api.assembler.impl;

import io.aiontechnology.mentorsuccess.api.assembler.AssemblerSupport;
import io.aiontechnology.mentorsuccess.entity.Student;
import io.aiontechnology.mentorsuccess.resource.StudentInformationResource;

import java.util.Optional;

public class StudentInformationAssembler extends AssemblerSupport<Student, StudentInformationResource> {

    @Override
    protected Optional<StudentInformationResource> doMap(Student student) {
        return Optional.ofNullable(student).map(s -> {
            StudentInformationResource resource = new StudentInformationResource(s);
            resource.setStudentName(s.getFullName());
            return resource;
        });
    }

}
