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

import io.aiontechnology.mentorsuccess.api.assembler.LinkAddingRepresentationModelAssemblerSupport;
import io.aiontechnology.mentorsuccess.api.assembler.LinkProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.Optional;

/**
 * @author Whitney Hunter
 * @since 0.3.0
 */
@RequiredArgsConstructor
public class AssemblerMapperAdaptor<FROM, TO extends RepresentationModel<TO>> implements OneWayMapper<FROM, TO> {

    private final LinkAddingRepresentationModelAssemblerSupport<FROM, TO> assembler;

    private final LinkProvider<TO, FROM> linkProvider;

    @Override
    public Optional<TO> map(FROM from) {
        return Optional.of(assembler.toModel(from, linkProvider));
    }

}
