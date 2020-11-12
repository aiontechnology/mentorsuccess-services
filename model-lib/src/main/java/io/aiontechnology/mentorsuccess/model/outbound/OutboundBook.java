package io.aiontechnology.mentorsuccess.model.outbound;

import io.aiontechnology.mentorsuccess.model.enumeration.ResourceLocation;
import io.aiontechnology.mentorsuccess.model.outbound.reference.OutboundBehavior;
import io.aiontechnology.mentorsuccess.model.outbound.reference.OutboundInterest;
import io.aiontechnology.mentorsuccess.model.outbound.reference.OutboundLeadershipSkill;
import io.aiontechnology.mentorsuccess.model.outbound.reference.OutboundLeadershipTrait;
import io.aiontechnology.mentorsuccess.model.outbound.reference.OutboundPhonogram;
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
@Relation(collectionRelation = "bookModelList")
public class OutboundBook extends OutboundResource<OutboundBook> {

    /** The book's id. */
    private final UUID id;

    /** The book's title. */
    private final String title;

    /** The book's author. */
    private final String author;

    /** The book's grade level. */
    private final Integer gradeLevel;

    private final ResourceLocation location;

    /** The interests associated with the book. */
    private final Collection<OutboundInterest> interests;

    /** The leadership traits associated with the book. */
    private final Collection<OutboundLeadershipTrait> leadershipTraits;

    /** The leadership skills associated with the book. */
    private final Collection<OutboundLeadershipSkill> leadershipSkills;

    /** The phonograms associated with the book. */
    private final Collection<OutboundPhonogram> phonograms;

    /** The behaviors associated with the book. */
    private final Collection<OutboundBehavior> behaviors;

}
