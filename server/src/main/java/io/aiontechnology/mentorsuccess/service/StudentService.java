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

import io.aiontechnology.mentorsuccess.entity.Student;
import io.aiontechnology.mentorsuccess.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

/**
 * Service that provides business logic for students.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
@Service
@RequiredArgsConstructor
public class StudentService {

    /** The repository used to interact with the database */
    private final StudentRepository studentRepository;

    @Transactional
    public void deactivateStudent(Student student) {
        student.setIsActive(false);
        studentRepository.save(student);
    }

    /**
     * Find a {@link Student} by its id.
     *
     * @param id The id of the desired {@link Student}.
     * @return The student if it could be found or empty if not.
     */
    public Optional<Student> getStudentById(UUID id) {
        return studentRepository.findById(id);
    }

    /**
     * Update the given {@link Student} in the database.
     *
     * @param student The {@link Student} to update.
     * @return The updated {@link Student}.
     */
    @Transactional
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

}
