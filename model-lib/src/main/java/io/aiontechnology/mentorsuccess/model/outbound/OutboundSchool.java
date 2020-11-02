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
@Relation(collectionRelation = "schoolModelList")
public class OutboundSchool extends RepresentationModel<OutboundSchool> {

    /** The school's id */
    private final UUID id;

    /** The name of the school */
    private final String name;

    /** The school's address */
    private final OutboundAddress address;

    /** The school's phone number */
    private final String phone;

    /** The school district that the school is in */
    private final String district;

    /** Indicates whether the school is private or public */
    private final Boolean isPrivate;

}
