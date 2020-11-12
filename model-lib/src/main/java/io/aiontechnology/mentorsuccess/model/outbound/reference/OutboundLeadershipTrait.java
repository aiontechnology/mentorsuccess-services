package io.aiontechnology.mentorsuccess.model.outbound.reference;

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
@Relation(collectionRelation = "leadershipTraitModelList")
public class OutboundLeadershipTrait extends RepresentationModel<OutboundLeadershipTrait> {

    /** The leadership trait's name */
    private final String name;

}
