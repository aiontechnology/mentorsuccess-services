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
@Relation(collectionRelation = "bookModelList")
public class OutboundBook extends OutboundResource<OutboundBook> {

    /** The book's id. */
    UUID id;

    /** The book's title. */
    String title;

    /** The book's author. */
    String author;

    /** The book's grade level. */
    Integer gradeLevel;

    /** The book's location */
    ResourceLocation location;

    /** The interests associated with the book. */
    Collection<String> interests;

    /** The leadership traits associated with the book. */
    Collection<String> leadershipTraits;

    /** The leadership skills associated with the book. */
    Collection<String> leadershipSkills;

    /** The phonograms associated with the book. */
    Collection<String> phonograms;

    /** The behaviors associated with the book. */
    Collection<String> behaviors;

}
