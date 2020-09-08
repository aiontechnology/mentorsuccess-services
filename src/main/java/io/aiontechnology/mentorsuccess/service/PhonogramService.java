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

import io.aiontechnology.mentorsuccess.entity.Phonogram;
import io.aiontechnology.mentorsuccess.repository.PhonogramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Optional;
import java.util.UUID;

/**
 * @author <a href="mailto:whitney@aiontechnology.io">Whitney Hunter</a>
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class PhonogramService {

    private final PhonogramRepository phonogramRepository;

    public Optional<Phonogram> getPhonogram(UUID id) {
        return phonogramRepository.findById(id);
    }

    public Iterable<Phonogram> getAllPhonograms() {
        return phonogramRepository.findAllByOrderByNameAsc();
    }

    public Optional<Phonogram> findPhonogramByName(String name) {
        return phonogramRepository.findByName(name);
    }

}
