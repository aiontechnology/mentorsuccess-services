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

package io.aiontechnology.mentorsuccess.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.aiontechnology.mentorsuccess.entity.StudentSchoolSession;
import io.aiontechnology.mentorsuccess.model.outbound.student.OutboundStudentTeacher;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Whitney Hunter
 * @since 1.8.0
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties({"content"})
public class StudentTeacherResource extends OutboundStudentTeacher<StudentSchoolSession> {

    public StudentTeacherResource(StudentSchoolSession studentSchoolSession) {
        super(studentSchoolSession);
    }

}
