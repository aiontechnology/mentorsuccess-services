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
import io.aiontechnology.mentorsuccess.entity.Teacher;
import org.springframework.stereotype.Component;

/**
 * Mapper to create a {@link TeacherModel} from a {@link Teacher}.
 *
 * @author Whitney Hunter
 */
@Component
public class ToTeacherModelMapper implements ImmutableMapper<Teacher, TeacherModel> {

    @Override
    public TeacherModel map(Teacher teacher) {
        return TeacherModel.builder()
                .withName(teacher.getName())
                .withEmail(teacher.getEmail())
                .withPhone(teacher.getPhone())
                .withGrade1(teacher.getGrade1())
                .withGrade2(teacher.getGrade2())
                .build();
    }

}
