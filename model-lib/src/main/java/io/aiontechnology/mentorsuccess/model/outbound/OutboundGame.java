package io.aiontechnology.mentorsuccess.model.outbound;

import io.aiontechnology.mentorsuccess.model.enumeration.ResourceLocation;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.hateoas.server.core.Relation;

import java.util.Collection;
import java.util.UUID;

/**
 * @author Whitney Hunter
 * @since 0.4.0
 */
@Value
@EqualsAndHashCode(callSuper = true)
@Builder(setterPrefix = "with")
@Relation(collectionRelation = "gameModelList")
public class OutboundGame extends OutboundResource<OutboundGame> implements LocationHolder {

    /** The game's id. */
    UUID id;

    /** The game's name. */
    String name;

    /** The game's starting grade level. */
    Integer grade1;

    /** The game's ending grade level. */
    Integer grade2;

    ResourceLocation location;

    /** The activity focus associated with the game. */
    Collection<String> activityFocuses;

    /** The leadership skills associated with the book. */
    Collection<String> leadershipSkills;

}
