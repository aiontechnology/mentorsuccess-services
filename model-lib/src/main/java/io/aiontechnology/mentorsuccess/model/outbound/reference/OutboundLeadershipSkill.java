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
@Relation(collectionRelation = "leadershipSkillModelList")
public class OutboundLeadershipSkill extends RepresentationModel<OutboundLeadershipSkill> {

    /** The leadership skill's name */
    private final String name;

}
