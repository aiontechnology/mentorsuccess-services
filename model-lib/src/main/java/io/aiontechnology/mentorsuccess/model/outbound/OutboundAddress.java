package io.aiontechnology.mentorsuccess.model.outbound;

import lombok.Builder;
import lombok.Data;

/**
 * @author Whitney Hunter
 * @since 0.4.0
 */
@Data
@Builder(setterPrefix = "with")
public class OutboundAddress {

    /** The first street address. */
    private final String street1;

    /** The second street address. */
    private final String street2;

    /** The city. */
    private final String city;

    /** The state. */
    private final String state;

    /** The zip code */
    private final String zip;

}
