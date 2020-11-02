package io.aiontechnology.mentorsuccess.model.outbound;

import io.aiontechnology.mentorsuccess.model.enumeration.RoleType;
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
@Relation("personnelModelList")
public class OutboundPersonnel extends RepresentationModel<OutboundPersonnel> {

    /** The personnel type */
    private final RoleType type;

    /** The first name. */
    private final String firstName;

    /** The last name. */
    private final String lastName;

    /** The email address. */
    private final String email;

    /** The work phone number. */
    private final String workPhone;

    /** The cell phone number. */
    private final String cellPhone;

}
