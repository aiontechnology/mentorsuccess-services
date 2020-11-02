package io.aiontechnology.mentorsuccess.model.outbound;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

/**
 * Model object representing a activity focus to be recieved from a client.
 *
 * @author Whitney Hunter
 * @since 0.4.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(setterPrefix = "with")
public class OutboundActivityFocus extends RepresentationModel<OutboundActivityFocus> {

    /** The activity focus' name */
    private final String name;

}
