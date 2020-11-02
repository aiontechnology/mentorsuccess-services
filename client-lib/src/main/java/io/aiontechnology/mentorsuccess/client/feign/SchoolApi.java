package io.aiontechnology.mentorsuccess.client.feign;

import feign.Param;
import feign.RequestLine;
import io.aiontechnology.mentorsuccess.model.inbound.InboundSchool;
import io.aiontechnology.mentorsuccess.model.outbound.OutboundSchool;

import java.util.UUID;

/**
 * Client for the school API.
 *
 * @author Whitney Hunter
 * @since 0.4.0
 */
public interface SchoolApi {

    /**
     * Send a POST to create a new school.
     *
     * @param school The new school.
     * @return The created school.
     */
    @RequestLine("POST /api/v1/schools")
    OutboundSchool createSchool(InboundSchool school);

    /**
     * Send a GET to retrieve a single school.
     *
     * @param shoolId The id of the desired school.
     * @return The school.
     */
    @RequestLine("GET /api/v1/schools/{schoolId}")
    OutboundSchool getSchool(@Param("schoolId") UUID shoolId);

}
