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
@Relation(collectionRelation = "behaviorModelList")
public class OutboundBehavior extends RepresentationModel<OutboundBehavior> {

    /** The behavior's name */
    private final String name;

}
