package io.aiontechnology.mentorsuccess.model.outbound;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

/**
 * @author Whitney Hunter
 * @since 0.4.0
 */
@Value
@EqualsAndHashCode(callSuper = true)
@Builder(setterPrefix = "with")
@Relation("programAdminModelList")
public class OutboundProgramAdmin extends RepresentationModel<OutboundProgramAdmin> {

    /** The first name of the program admin. */
    String firstName;

    /** The last name of the program admin. */
    String lastName;

    /** The program admin's email address. */
    String email;

    /** The program admin's work phone number. */
    String workPhone;

    /** The program admin's cell phone. */
    String cellPhone;

}
