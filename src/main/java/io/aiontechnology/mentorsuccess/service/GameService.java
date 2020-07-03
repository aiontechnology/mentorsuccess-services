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

package io.aiontechnology.mentorsuccess.service;

import io.aiontechnology.mentorsuccess.entity.Game;
import io.aiontechnology.mentorsuccess.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

/**
 * A service the provides business logic for {@link Game Games}.
 *
 * @author <a href="mailto:whitney@aiontechnology.io">Whitney Hunter</a>
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class GameService {

    /** The repository for {@link Game Games} in the database */
    private final GameRepository gameRepository;

    /**
     * Create a game in the database by saving the provided {@link Game}.
     *
     * @param game The {@link Game} to save.
     * @return The resulting {@link Game}. Will have a db generated id populated.
     */
    @Transactional
    public Game createGame(Game game) {
        log.debug("Creating game: {}", game);
        return gameRepository.save(game);
    }

    /**
     * Deactivate a {@link Game} in the system.
     *
     * @param game The {@link Game} to deactivate.
     */
    @Transactional
    public void deactivateGame(Game game) {
        game.setIsActive(false);
        gameRepository.save(game);
    }

    /**
     * Get all {@link Game Games} in the system.
     *
     * @return All {@link Game Games}.
     */
    public Iterable<Game> getAllGames() {
        return gameRepository.findAll();
    }

    /**
     * Get a {@link Game} for the given id.
     *
     * @param id The id of the desired {@link Game}.
     * @return The {@link Game} if it could be found.
     */
    public Optional<Game> getGame(UUID id) {
        return gameRepository.findById(id);
    }

    /**
     * Update the given {@link Game} in the database.
     *
     * @param Game The {@link Game} to update.
     * @return The updated {@link Game}.
     */
    @Transactional
    public Game updateGame(Game Game) {
        return gameRepository.save(Game);
    }

}
