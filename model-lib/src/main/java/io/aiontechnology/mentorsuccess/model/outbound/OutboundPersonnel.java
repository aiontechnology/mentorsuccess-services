package io.aiontechnology.mentorsuccess.model.outbound;

import io.aiontechnology.mentorsuccess.model.enumeration.RoleType;
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
@Relation("personnelModelList")
public class OutboundPersonnel extends RepresentationModel<OutboundPersonnel> {

    /** The personnel type */
    RoleType type;

    /** The first name. */
    String firstName;

    /** The last name. */
    String lastName;

    /** The email address. */
    String email;

    /** The work phone number. */
    String workPhone;

    /** The cell phone number. */
    String cellPhone;

}
