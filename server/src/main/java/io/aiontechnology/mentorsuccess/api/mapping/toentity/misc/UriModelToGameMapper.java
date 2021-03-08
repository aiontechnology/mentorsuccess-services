/*
 * Copyright 2021 Aion Technology LLC
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.aiontechnology.mentorsuccess.api.mapping.toentity.misc;

import io.aiontechnology.atlas.mapping.OneWayMapper;
import io.aiontechnology.mentorsuccess.entity.Game;
import io.aiontechnology.mentorsuccess.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Whitney Hunter
 * @since 1.1.0
 */
@Component
@RequiredArgsConstructor
public class UriModelToGameMapper implements OneWayMapper<URI, Game> {

    private final Pattern GAME_PATTERN = Pattern.compile("https?://.*/api/v1/games/(.*)");

    private final GameService gameService;

    @Override
    public Optional<Game> map(URI gameUri) {
        return Optional.ofNullable(gameUri).
                flatMap(u -> {
                    UUID gameUUID = null;

                    Matcher matcher1 = GAME_PATTERN.matcher(gameUri.toString());
                    if (matcher1.find()) {
                        gameUUID = UUID.fromString(matcher1.group(1));
                    }

                    return gameUUID != null ? gameService.findGameById(gameUUID) : Optional.empty();
                });
    }

}
