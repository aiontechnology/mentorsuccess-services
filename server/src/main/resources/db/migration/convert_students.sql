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

insert into student_schoolsession(student_id, schoolsession_id, grade, preferred_time, actual_time, location,
                                  is_media_release_signed, start_date, pre_behavioral_assessment,
                                  post_behavioral_assessment, teacher_role_id, teacher_comment, mentor_role_id,
                                  is_active)
select id,
       (select id as schoolsession_id from school_session where school_id = s.school_id),
       grade,
       preferred_time,
       actual_time,
       location,
       is_media_release_signed,
       start_date,
       pre_behavioral_assessment,
       post_behavioral_assessment,
       (select role_id as teacher_role_id from student_teacher where student_id = s.id),
       (select comment as teacher_comment from student_teacher where student_id = s.id),
       (select role_id as mentor_role_id from student_mentor where student_id = s.id),
       is_active
from student as s;
