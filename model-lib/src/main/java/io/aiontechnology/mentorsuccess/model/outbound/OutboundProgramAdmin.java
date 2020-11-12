package io.aiontechnology.mentorsuccess.model.outbound;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

/**
 * @author Whitney Hunter
 * @since 0.4.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(setterPrefix = "with")
@Relation("programAdminModelList")
public class OutboundProgramAdmin extends RepresentationModel<OutboundProgramAdmin> {

    /** The first name of the program admin. */
    private final String firstName;

    /** The last name of the program admin. */
    private final String lastName;

    /** The program admin's email address. */
    private final String email;

    /** The program admin's work phone number. */
    private final String workPhone;

    /** The program admin's cell phone. */
    private final String cellPhone;

}
