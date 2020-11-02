/*
 * Copyright 2020 Aion Technology LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.aiontechnology.mentorsuccess.api.assembler;

import io.aiontechnology.atlas.mapping.OneWayMapper;
import io.aiontechnology.mentorsuccess.api.controller.GameController;
import io.aiontechnology.mentorsuccess.entity.Game;
import io.aiontechnology.mentorsuccess.model.inbound.InboundGame;
import io.aiontechnology.mentorsuccess.model.outbound.OutboundGame;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Assembles a {@link OutboundGame} from a {@link Game}.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@Component
public class GameModelAssembler extends RepresentationModelAssemblerSupport<Game, OutboundGame> {

    /** Mapper to map between {@link Game} and {@link OutboundGame}. */
    private final OneWayMapper<Game, OutboundGame> mapper;

    /** A utility class for adding links to a model object. */
    private final LinkHelper<OutboundGame> linkHelper;

    /**
     * Constructor
     *
     * @param mapper The mapper for mapping between {@link Game} and {@link OutboundGame}.
     * @param linkHelper A utility class for adding links to a model object.
     */
    @Inject
    public GameModelAssembler(OneWayMapper<Game, OutboundGame> mapper, LinkHelper<OutboundGame> linkHelper) {
        super(GameController.class, OutboundGame.class);
        this.mapper = mapper;
        this.linkHelper = linkHelper;
    }

    /**
     * Map a {@link Game} to a {@link OutboundGame} without adding links.
     *
     * @param game The {@link Game} to map.
     * @return The resulting {@link InboundGame}.
     */
    @Override
    public OutboundGame toModel(Game game) {
        return Optional.ofNullable(game)
                .flatMap(mapper::map)
                .orElse(null);
    }

    /**
     * Map a {@link Game} to a {@link InboundGame} and add links.
     *
     * @param game The {@link Game} to map.
     * @param linkProvider An object that provides links.
     * @return The resulting {@link InboundGame}.
     */
    public OutboundGame toModel(Game game, LinkProvider<OutboundGame, Game> linkProvider) {
        return Optional.ofNullable(game)
                .map(this::toModel)
                .map(model -> linkHelper.addLinks(model, linkProvider.apply(model, game)))
                .orElse(null);
    }

}
