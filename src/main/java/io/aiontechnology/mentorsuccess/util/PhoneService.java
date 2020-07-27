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

package io.aiontechnology.mentorsuccess.util;

import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Whitney Hunter
 */
@Component
public class PhoneService {

    public String normalize(String phoneNumber) {
        return Optional.ofNullable(phoneNumber)
                .map(pn -> pn.replace("(", "")
                        .replace(")", "")
                        .replace("-", "")
                        .replace(" ", ""))
                .orElse(null);
    }

    public String format(String phoneNumber) {
        return Optional.ofNullable(phoneNumber)
                .map(pn -> pn.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3"))
                .orElse(null);
    }

}
