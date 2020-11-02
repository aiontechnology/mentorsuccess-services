package io.aiontechnology.mentorsuccess.model.outbound;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.UUID;

/**
 * @author Whitney Hunter
 * @since 0.4.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(setterPrefix = "with")
@Relation(collectionRelation = "personModelList")
public class OutboundPerson extends RepresentationModel<OutboundPerson> {

    /** The person's id. */
    private final UUID id;

    /** The person's first name. */
    private final String firstName;

    /** The person's last name. */
    private final String lastName;

    /** The person's email. */
    private final String email;

    /** The person's work phone number. */
    private final String workPhone;

    /** The person's cell phone number. */
    private final String cellPhone;

}
