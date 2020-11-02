package io.aiontechnology.mentorsuccess.client.feign;

import feign.RequestLine;
import io.aiontechnology.mentorsuccess.model.inbound.InboundGame;
import io.aiontechnology.mentorsuccess.model.outbound.OutboundGame;
import org.springframework.hateoas.CollectionModel;

/**
 * Client for the game API.
 *
 * @author Whitney Hunter
 * @since 0.4.0
 */
public interface GameApi {

    @RequestLine("POST /api/v1/games")
    OutboundGame createGame(InboundGame game);

    @RequestLine("GET /api/v1/games")
    CollectionModel<OutboundGame> getAllGames();

}
