package io.aiontechnology.mentorsuccess.model.outbound;

import io.aiontechnology.mentorsuccess.model.enumeration.ResourceLocation;
import io.aiontechnology.mentorsuccess.model.outbound.reference.OutboundLeadershipSkill;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.server.core.Relation;

import java.util.Collection;
import java.util.UUID;

/**
 * @author Whitney Hunter
 * @since 0.4.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(setterPrefix = "with")
@Relation(collectionRelation = "gameModelList")
public class OutboundGame extends OutboundResource<OutboundGame> {

    /** The game's id. */
    private final UUID id;

    /** The game's name. */
    private final String name;

    /** The game's starting grade level. */
    private final Integer grade1;

    /** The game's ending grade level. */
    private final Integer grade2;

    private final ResourceLocation location;

    /** The activity focus associated with the game. */
    private final Collection<OutboundActivityFocus> activityFocuses;

    /** The leadership skills associated with the book. */
    private final Collection<OutboundLeadershipSkill> leadershipSkills;

}
