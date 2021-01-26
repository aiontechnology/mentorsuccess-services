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

package io.aiontechnology.mentorsuccess.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.MappedJwtClaimSetConverter;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Whitney Hunter
 * @since 0.8.0
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        prePostEnabled = true,
        jsr250Enabled = true)
@Slf4j
public class OAuthSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String COGNITO_GROUPS = "cognito:groups";
    private static final String SPRING_AUTHORITIES = "authorities";

    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    String jwkSetUri;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorize -> authorize.anyRequest().authenticated())
                .oauth2ResourceServer(oauth2ResourceServer ->
                        oauth2ResourceServer.jwt(jwt -> jwt.decoder(jwtDecoder())));
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();
        jwtDecoder.setClaimSetConverter(new CognitoClaimAdaptor());
        return jwtDecoder;
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthoritiesClaimName(SPRING_AUTHORITIES);
        grantedAuthoritiesConverter.setAuthorityPrefix("");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }

    static class CognitoClaimAdaptor implements Converter<Map<String, Object>, Map<String, Object>> {

        private final MappedJwtClaimSetConverter delegate =
                MappedJwtClaimSetConverter.withDefaults(Collections.emptyMap());

        @Override
        public Map<String, Object> convert(Map<String, Object> claims) {
            Map<String, Object> convertedClaims = delegate.convert(claims);
            if (convertedClaims.containsKey(COGNITO_GROUPS)) {
                ((List<String>) convertedClaims.get(COGNITO_GROUPS)).stream()
                        .map(this::authoritiesForGroup)
                        .peek(authorities -> log.debug("Adding athorities: {}", authorities))
                        .forEach(groups -> convertedClaims.put(SPRING_AUTHORITIES, groups));
            }
            log.debug("Claims: {}", convertedClaims);
            return convertedClaims;
        }

        private List<String> authoritiesForGroup(String group) {
            return switch (group) {
                case "PROGRAM_ADMIN" -> createProgramAdminAuthorities();
                case "SYSTEM_ADMIN" -> createSystemAdminAuthorities();
                default -> Collections.emptyList();
            };
        }

        private List<String> createProgramAdminAuthorities() {
            List<String> authorities = new ArrayList<>();
            authorities.addAll(getBookAuthorities(false));
            authorities.addAll(getGameAuthorities(false));
            authorities.addAll(getMentorAuthorities(false));
            authorities.addAll(getPersonAuthorities(false));
            authorities.addAll(getPersonnelAuthorities(false));
            authorities.addAll(getProgramAdminAuthorities(false));
            authorities.addAll(getResourceAuthorities(false));
            authorities.addAll(getSchoolAuthorities(false));
            authorities.addAll(getStudentAuthorities(false));
            authorities.addAll(getTeacherAuthorities(false));
            return authorities;
        }

        private List<String> createSystemAdminAuthorities() {
            List<String> authorities = new ArrayList<>();
            authorities.addAll(getBookAuthorities(true));
            authorities.addAll(getGameAuthorities(true));
            authorities.addAll(getMentorAuthorities(true));
            authorities.addAll(getPersonAuthorities(true));
            authorities.addAll(getPersonnelAuthorities(true));
            authorities.addAll(getProgramAdminAuthorities(true));
            authorities.addAll(getResourceAuthorities(true));
            authorities.addAll(getSchoolAuthorities(true));
            authorities.addAll(getStudentAuthorities(true));
            authorities.addAll(getTeacherAuthorities(true));
            return authorities;
        }

        private List<String> getBookAuthorities(boolean isAdmin) {
            List<String> authorities = new ArrayList<>();
            authorities.addAll(Arrays.asList("book:read", "book:update"));
            if (isAdmin) {
                authorities.addAll(Arrays.asList("book:create", "books:read", "book:delete"));
            }
            return authorities;
        }

        private List<String> getGameAuthorities(boolean isAdmin) {
            List<String> authorities = new ArrayList<>();
            authorities.addAll(Arrays.asList("game:read", "game:update"));
            if (isAdmin) {
                authorities.addAll(Arrays.asList("game:create", "games:read", "game:delete"));
            }
            return authorities;
        }

        private List<String> getMentorAuthorities(boolean isAdmin) {
            List<String> authorities = new ArrayList<>();
            authorities.addAll(Arrays.asList("mentor:read", "mentor:update"));
            if (isAdmin) {
                authorities.addAll(Arrays.asList("mentor:create", "mentors:read", "mentor:delete"));
            }
            return authorities;
        }

        private List<String> getPersonAuthorities(boolean isAdmin) {
            List<String> authorities = new ArrayList<>();
            authorities.addAll(Arrays.asList("person:read"));
            if (isAdmin) {
                authorities.addAll(Arrays.asList("person:create"));
            }
            return authorities;
        }

        private List<String> getPersonnelAuthorities(boolean isAdmin) {
            List<String> authorities = new ArrayList<>();
            authorities.addAll(Arrays.asList("personnel:read", "personnel:update"));
            if (isAdmin) {
                authorities.addAll(Arrays.asList("personnel:create", "personnels:read", "personnel:delete"));
            }
            return authorities;
        }

        private List<String> getProgramAdminAuthorities(boolean isAdmin) {
            List<String> authorities = new ArrayList<>();
            authorities.addAll(Arrays.asList("program-admin:read", "program-admin:update"));
            if (isAdmin) {
                authorities.addAll(Arrays.asList("program-admin:create", "program-admins:read", "program-admin:delete"));
            }
            return authorities;
        }

        private List<String> getResourceAuthorities(boolean isAdmin) {
            List<String> authorities = new ArrayList<>();
            authorities.addAll(Arrays.asList("resource:read"));
            if (isAdmin) {
                authorities.addAll(Arrays.asList("resources:update"));
            }
            return authorities;
        }

        private List<String> getSchoolAuthorities(boolean isAdmin) {
            List<String> authorities = new ArrayList<>();
            authorities.addAll(Arrays.asList("school:read", "school:update"));
            if (isAdmin) {
                authorities.addAll(Arrays.asList("school:create", "schools:read", "school:delete"));
            }
            return authorities;
        }

        private List<String> getStudentAuthorities(boolean isAdmin) {
            List<String> authorities = new ArrayList<>();
            authorities.addAll(Arrays.asList("student:read", "student:update"));
            if (isAdmin) {
                authorities.addAll(Arrays.asList("student:create", "students:read", "student:delete"));
            }
            return authorities;
        }

        private List<String> getTeacherAuthorities(boolean isAdmin) {
            List<String> authorities = new ArrayList<>();
            authorities.addAll(Arrays.asList("teacher:read", "teacher:update"));
            if (isAdmin) {
                authorities.addAll(Arrays.asList("teacher:create", "teachers:read", "teacher:delete"));
            }
            return authorities;
        }

    }

}
