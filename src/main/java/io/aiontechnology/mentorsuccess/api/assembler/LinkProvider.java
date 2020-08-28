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

import org.springframework.hateoas.Link;

import java.util.List;
import java.util.function.BiFunction;

/**
 * Interface for classes that provide a list of {@link Link Links}.
 *
 * @param <M> The class of the model object.
 * @param <E> The class of the entity object.
 * @author Whitney Hunter
 */
@FunctionalInterface
public interface LinkProvider<M, E> extends BiFunction<M, E, List<Link>> {
}
