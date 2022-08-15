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

-- Create Cornerstone Christian Academy
insert into school (id, name, street1, street2, city, state, zip, phone, district, is_private, is_active,
                    current_session_id)
values ('12b64b62-da7b-4268-b7f2-1feb207336f8', 'Cornerstone Christian Academy', '10818 NE 117th Ave', '',
        'Vancouver', 'WA', '98662', '3602569715', 'Vancouver', true, true, null);

-- Add the 2021 school session
insert into school_session (id, school_id, start_date, end_date, label)
values ('098223a4-61d7-44a4-9dd4-5c166d13edd0', '12b64b62-da7b-4268-b7f2-1feb207336f8', '01/01/2021', '12/31/2021',
        '2021');

-- Set the current session for Cornerstone Christian Academy
update school
set current_session_id = '098223a4-61d7-44a4-9dd4-5c166d13edd0'
where id = '12b64b62-da7b-4268-b7f2-1feb207336f8';

-- Add Tammy Teacher
insert into person(id, last_name, work_phone, email, cell_phone, first_name)
values ('95151b17-55bc-41a5-85bf-d63bffe2d128', 'Teacher', '3605551234', 'tammy@cornerstone.edu', '3605551235',
        'Tammy');
insert into school_person_role(id, school_id, person_id, type, grade1, grade2, is_active)
values ('83f909a6-f0b7-4e95-9f13-b0e6fd1fe1e2', '12b64b62-da7b-4268-b7f2-1feb207336f8',
        '95151b17-55bc-41a5-85bf-d63bffe2d128', 'TEACHER', 1, null, true);

-- Add Tom Teacher
insert into person(id, last_name, work_phone, email, cell_phone, first_name)
values ('ade3b8a6-5375-4005-878d-60071b8ca4a1', 'Teacher', '3605551236', 'tom@cornerstone.edu', '3605551237',
        'Tom');
insert into school_person_role(id, school_id, person_id, type, grade1, grade2, is_active)
values ('0e7d10d4-14ae-4694-9759-770fb065dd7e', '12b64b62-da7b-4268-b7f2-1feb207336f8',
        'ade3b8a6-5375-4005-878d-60071b8ca4a1', 'TEACHER', 2, null, true);

-- Add Sally Student
insert into student (id, student_id, first_name, last_name, school_id, pre_behavioral_assessment,
                     post_behavioral_assessment)
values ('cac776e6-feb4-4e7f-a53f-22b4ebee4258', 'student1', 'Sally', 'Student', '12b64b62-da7b-4268-b7f2-1feb207336f8',
        1, 2);
insert into student_schoolsession(id, student_id, schoolsession_id, grade, preferred_time, actual_time, location,
                                  is_media_release_signed, start_date, teacher_role_id, teacher_comment, mentor_role_id,
                                  is_active)
values ('7f9e77d6-6ec0-401b-8f75-4c77fa74c116', 'cac776e6-feb4-4e7f-a53f-22b4ebee4258',
        '098223a4-61d7-44a4-9dd4-5c166d13edd0', 1, '1:00pm', '2:00pm', 'ONLINE', true, '05/16/2022',
        '83f909a6-f0b7-4e95-9f13-b0e6fd1fe1e2', 'COMMENT', null, true);