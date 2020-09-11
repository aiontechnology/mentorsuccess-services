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

import io.aiontechnology.mentorsuccess.entity.ActivityFocus;
import io.aiontechnology.mentorsuccess.repository.ActivityFocusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * Service that provides business logic for activity focuses.
 *
 * @author Whitney Hunter
 * @since 0.2.0
 */
@Service
@RequiredArgsConstructor
public class ActivityFocusService {

    private final ActivityFocusRepository activityFocusRepository;

    public Optional<ActivityFocus> getActivityFocus(UUID id) {
        return activityFocusRepository.findById(id);
    }

    public Iterable<ActivityFocus> getAllActivityFocuses() {
        return activityFocusRepository.findAll();
    }

    public Optional<ActivityFocus> findActivityFocustByName(String name) {
        return activityFocusRepository.findByName(name);
    }

}
