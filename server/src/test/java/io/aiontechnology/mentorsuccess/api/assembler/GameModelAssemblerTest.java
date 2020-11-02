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
import io.aiontechnology.mentorsuccess.entity.Game;
import io.aiontechnology.mentorsuccess.model.outbound.OutboundGame;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Optional;

import static java.lang.Boolean.TRUE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link GameModelAssembler}.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
public class GameModelAssemblerTest {
    private static final String NAME = "NAME";
    private static final String DESCIPTION = "DESCRIPTION";
    private static final Integer GRADE_LEVEL = 1;
    private static final Boolean IS_ACTIVE = TRUE;

    @Test
    void testToModel() throws Exception {
        // setup the fixture
        Game game = new Game();

        OneWayMapper<Game, OutboundGame> gameMapper = mock(OneWayMapper.class);
        OutboundGame outboundGame = mock(OutboundGame.class);
        when(gameMapper.map(game)).thenReturn(Optional.of(outboundGame));

        GameModelAssembler assembler = new GameModelAssembler(gameMapper, null);

        // execute the SUT
        OutboundGame result = assembler.toModel(game);

        // validation
        assertThat(result).isSameAs(outboundGame);
    }

    @Test
    void testToModel_nullBook() throws Exception {
        // setup the fixture
        Game game = null;

        OneWayMapper<Game, OutboundGame> gameMapper = mock(OneWayMapper.class);
        OutboundGame outboundGame = mock(OutboundGame.class);
        when(gameMapper.map(game)).thenReturn(Optional.of(outboundGame));

        GameModelAssembler assembler = new GameModelAssembler(gameMapper, null);

        // execute the SUT
        OutboundGame result = assembler.toModel(game);

        // validation
        assertThat(result).isNull();
    }

    @Test
    void testToModel_withLinkProvider() throws Exception {
        // setup the fixture
        Game game = new Game();

        OneWayMapper<Game, OutboundGame> gameMapper = mock(OneWayMapper.class);
        OutboundGame outboundGame = mock(OutboundGame.class);
        when(gameMapper.map(game)).thenReturn(Optional.of(outboundGame));

        LinkProvider<OutboundGame, Game> linkProvider = mock(LinkProvider.class);
        when(linkProvider.apply(any(), any())).thenReturn(Collections.emptyList());
        GameModelAssembler assembler = new GameModelAssembler(gameMapper, new LinkHelper<>());

        // execute the SUT
        OutboundGame result = assembler.toModel(game, linkProvider);

        // validation
        assertThat(result).isSameAs(outboundGame);
        verify(linkProvider).apply(outboundGame, game);
    }

}
