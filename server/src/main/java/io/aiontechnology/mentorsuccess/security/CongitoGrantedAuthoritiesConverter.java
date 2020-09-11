/*
 * Copyright 2021 Aion Technology LLC
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.aiontechnology.mentorsuccess.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

/**
 * @author Whitney Hunter
 * @since 0.8.0
 */
@Component
@Slf4j
public class CongitoGrantedAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    private static final String COGNITO_GROUPS = "cognito:groups";
    private static final String SPRING_AUTHORITIES = "authorities";

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        log.info("--> MADE IT");
        if (jwt.getClaims().containsKey(COGNITO_GROUPS)) {
            log.info("--> CLAIMS FOUND");
            jwt.getClaims().put(SPRING_AUTHORITIES, jwt.getClaims().get(COGNITO_GROUPS));
        }
        return Collections.emptyList();
    }

}
