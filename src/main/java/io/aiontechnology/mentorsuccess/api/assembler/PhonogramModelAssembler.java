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

import io.aiontechnology.mentorsuccess.api.controller.PhonogramController;
import io.aiontechnology.mentorsuccess.api.mapping.PhonogramMapper;
import io.aiontechnology.mentorsuccess.api.model.PhonogramModel;
import io.aiontechnology.mentorsuccess.entity.Phonogram;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Optional;

/**
 * @author Whitney Hunter
 */
@Component
public class PhonogramModelAssembler extends RepresentationModelAssemblerSupport<Phonogram, PhonogramModel> {

    private final PhonogramMapper phonogramMapper;

    /**
     * Construct an instance.
     */
    @Inject
    public PhonogramModelAssembler(PhonogramMapper phonogramMapper) {
        super(PhonogramController.class, PhonogramModel.class);
        this.phonogramMapper = phonogramMapper;
    }

    @Override
    public PhonogramModel toModel(Phonogram phonogram) {
        return Optional.ofNullable(phonogram)
                .map(phonogramMapper::mapEntityToModel)
                .orElse(null);
    }

}
