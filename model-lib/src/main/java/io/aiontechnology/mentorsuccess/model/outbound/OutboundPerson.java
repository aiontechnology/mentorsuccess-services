package io.aiontechnology.mentorsuccess.model.outbound;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.NonFinal;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.UUID;

/**
 * @author Whitney Hunter
 * @since 0.4.0
 */
@Value
@EqualsAndHashCode(callSuper = true)
@Builder(setterPrefix = "with")
@NonFinal
@Relation(collectionRelation = "personModelList")
public class OutboundPerson extends RepresentationModel<OutboundPerson> {

    /** The person's id. */
    UUID id;

    /** The person's first name. */
    String firstName;

    /** The person's last name. */
    String lastName;

    /** The person's email. */
    String email;

    /** The person's work phone number. */
    String workPhone;

    /** The person's cell phone number. */
    String cellPhone;

}
