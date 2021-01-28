/*
 * Copyright 2020-2021 Aion Technology LLC
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

import io.aiontechnology.mentorsuccess.entity.reference.Interest;
import io.aiontechnology.mentorsuccess.repository.InterestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service that provides business logic for interests.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class InterestService {

    private static final String CREATE_ME_TOKEN = "CREATE_ME";

    /** The repository used to interact with the database */
    private final InterestRepository interestRepository;

    /**
     * Find an {@link Interest} by its id.
     *
     * @param id The id of the desired {@link Interest}.
     * @return The {@link Interest} if it could be found.
     */
    public Optional<Interest> findInterestById(UUID id) {
        return interestRepository.findById(id);
    }

    /**
     * Find an {@link Interest} by its name.
     *
     * @param name The name of the desired {@link Interest}.
     * @return The {@link Interest} if it could be found.
     */
    public Optional<Interest> findInterestByName(String name) {
        return interestRepository.findByName(name);
    }

    /**
     * Get all {@link Interest Interests}.
     *
     * @return All {@link Interest Interests}.
     */
    public Iterable<Interest> getAllInterests() {
        return interestRepository.findAllByOrderByNameAsc();
    }

    @Transactional
    public void updateInterests(Map<String, String> updatedInterests) {
        var currentInterestsByValue = StreamSupport.stream(getAllInterests().spliterator(), false)
                .collect(Collectors.toMap(Interest::getName, Function.identity()));

        updatedInterests.keySet().stream()
                .map(newValue -> {
                    String oldValue = updatedInterests.get(newValue);
                    return CREATE_ME_TOKEN.equals(oldValue)
                            ? createNewInterest(currentInterestsByValue, newValue)
                            : updateExistingInterest(currentInterestsByValue, oldValue, newValue);
                })
                .filter(interest -> !interest.isEmpty())
                .map(Optional::get)
                .forEach(interestRepository::save);
    }

    Optional<Interest> createNewInterest(Map<String, Interest> currentInterestsByValue, String newValue) {
        Optional<Interest> result = Optional.empty();
        if (!currentInterestsByValue.containsKey(newValue)) {
            Interest newInterest = new Interest();
            newInterest.setName(newValue);
            result = Optional.of(newInterest);
        }
        return result;
    }

    Optional<Interest> updateExistingInterest(Map<String, Interest> currentInterestsByValue, String oldValue, String newValue) {
        Optional<Interest> result = Optional.empty();
        if (currentInterestsByValue.containsKey(oldValue)) {
            Interest currentInterest = currentInterestsByValue.get(oldValue);
            currentInterest.setName(newValue);
            result = Optional.of(currentInterest);
        }
        return result;
    }

}
