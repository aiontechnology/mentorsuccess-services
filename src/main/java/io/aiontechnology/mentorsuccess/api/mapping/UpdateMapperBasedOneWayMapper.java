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

package io.aiontechnology.mentorsuccess.api.mapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.util.Optional;

/**
 * A {@link OneWayMapper} that is based on a corresponding {@link OneWayUpdateMapper}.
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
@Slf4j
@RequiredArgsConstructor
public class UpdateMapperBasedOneWayMapper<FROM, TO> implements OneWayMapper<FROM, TO> {

    private final OneWayUpdateMapper<FROM, TO> mapper;

    private final Class<TO> toClass;

    @Override
    public Optional<TO> map(FROM from) {
        return Optional.ofNullable(from)
                .flatMap(f -> {
                    try {
                        Constructor<TO> ctor = toClass.getConstructor();
                        return mapper.map(f, ctor.newInstance());
                    } catch (Exception e) {
                        log.error("Can't create instance of class: {}", toClass);
                        throw new RuntimeException("Invalid class provided", e);
                    }
                });
    }

}
