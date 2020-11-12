package io.aiontechnology.mentorsuccess.client.feign;

import feign.Param;
import feign.RequestLine;
import io.aiontechnology.mentorsuccess.model.inbound.student.InboundStudent;
import io.aiontechnology.mentorsuccess.model.outbound.student.OutboundStudent;
import org.springframework.hateoas.CollectionModel;

import java.util.UUID;

/**
 * Client for the student API.
 *
 * @author Whitney Hunter
 * @since 0.4.0
 */
public interface StudentApi {

    /**
     * Send a POST to create a new student.
     *
     * @param schoolId The id of the school for the new student.
     * @param student The new student.
     * @return The created student.
     */
    @RequestLine("POST /api/v1/schools/{schoolId}/students")
    OutboundStudent createStudent(@Param("schoolId") UUID schoolId, InboundStudent student);

    /**
     * Send a GET to retrieve all students for a school.
     *
     * @param schoolId The id of the school.
     * @return A collection of students for the school.
     */
    @RequestLine("GET /api/v1/schools/{schoolId}")
    CollectionModel<OutboundStudent> getAllStudents(@Param("schoolId") UUID schoolId);

    /**
     * Send a GET to retrieve a single student for a school.
     *
     * @param schoolId The id of the school.
     * @param studentId The id of the desired student.
     * @return The student.
     */
    @RequestLine("GET /api/v1/schools/{schoolId}/students/{studentId}")
    OutboundStudent getStudent(@Param("schoolId") UUID schoolId, @Param("studentId") UUID studentId);

    /**
     * Send a PUT to update a student.
     *
     * @param schoolId The id of the school.
     * @param studentId The id of the student to update.
     * @param student The student model to update to.
     * @return The updated student.
     */
    @RequestLine("PUT /api/v1/schools/{schoolId}/students/{studentId}")
    OutboundStudent updateStudent(@Param("schoolId") UUID schoolId, @Param("studentId") UUID studentId,
            InboundStudent student);

    /**
     * Send a DELETE to deactivate a student.
     *
     * @param schoolId The id of the school.
     * @param studentId The id of the student to deactivate.
     */
    @RequestLine("DELETE /api/v1/schools/{schoolId}/students/{studentId}")
    void deleteStudent(@Param("schoolId") UUID schoolId, @Param("studentId") UUID studentId);

}
