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
 * @author Whitney Hunter
 */
@Component
public class FromTeacherModelMapper implements MutableMapper<TeacherModel, Teacher> {

    @Override
    public Teacher map(TeacherModel teacherModel) {
        Teacher teacher = new Teacher();
        return map(teacherModel, teacher);
    }

    @Override
    public Teacher map(TeacherModel teacherModel, Teacher teacher) {
        teacher.setName(teacherModel.getName());
        teacher.setEmail(teacherModel.getEmail());
        String phone = teacherModel.getPhone()
                .replace("(", "")
                .replace(")", "")
                .replace("-", "")
                .replace(" ", "");

        teacher.setPhone(phone);
        teacher.setGrade1(teacherModel.getGrade1());
        teacher.setGrade2(teacherModel.getGrade2());
        return teacher;
    }

}
