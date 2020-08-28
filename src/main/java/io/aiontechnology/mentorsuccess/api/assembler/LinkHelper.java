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
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Helper component for adding {@link Link Links} to {@link RepresentationModel} instances.
 *
 * @author Whitney Hunter
 */
@Component
public class LinkHelper<M extends RepresentationModel<?>> {

    /**
     * Add the {@link Link Links} from the given list to the given model object.
     *
     * @param model The model into which links should added.
     * @param links The list of {@link Link Linis} that should be added.
     * @return The model object.
     */
    public M addLinks(M model, List<Link> links) {
        for (Link link : links) {
            model.add(link);
        }
        return model;
    }

}
