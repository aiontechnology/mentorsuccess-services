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

import io.aiontechnology.mentorsuccess.api.controller.InterestController;
import io.aiontechnology.mentorsuccess.api.mapping.InterestMapper;
import io.aiontechnology.mentorsuccess.api.model.InterestModel;
import io.aiontechnology.mentorsuccess.entity.Interest;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Optional;

/**
 * @author Whitney Hunter
 */
@Component
public class InterestModelAssembler extends RepresentationModelAssemblerSupport<Interest, InterestModel> {

    private final InterestMapper interestMapper;

    /**
     * Construct an instance.
     */
    @Inject
    public InterestModelAssembler(InterestMapper interestMapper) {
        super(InterestController.class, InterestModel.class);
        this.interestMapper = interestMapper;
    }

    @Override
    public InterestModel toModel(Interest interest) {
        return Optional.ofNullable(interest)
                .map(interestMapper::mapEntityToModel)
                .orElse(null);
    }

}
