package io.aiontechnology.mentorsuccess.model.outbound;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
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
@Relation(collectionRelation = "schoolModelList")
public class OutboundSchool extends RepresentationModel<OutboundSchool> {

    /** The school's id */
    UUID id;

    /** The name of the school */
    String name;

    /** The school's address */
    OutboundAddress address;

    /** The school's phone number */
    String phone;

    /** The school district that the school is in */
    String district;

    /** Indicates whether the school is private or public */
    Boolean isPrivate;

}
