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

package io.aiontechnology.mentorsuccess.configuration;

import io.aiontechnology.mentorsuccess.api.mapping.BehaviorMapper;
import io.aiontechnology.mentorsuccess.api.mapping.InterestMapper;
import io.aiontechnology.mentorsuccess.api.mapping.LeadershipSkillMapper;
import io.aiontechnology.mentorsuccess.api.mapping.LeadershipTraitMapper;
import io.aiontechnology.mentorsuccess.api.mapping.PhonogramMapper;
import io.aiontechnology.mentorsuccess.service.BehaviorService;
import io.aiontechnology.mentorsuccess.service.InterestService;
import io.aiontechnology.mentorsuccess.service.LeadershipSkillService;
import io.aiontechnology.mentorsuccess.service.LeadershipTraitService;
import io.aiontechnology.mentorsuccess.service.PhonogramService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring configuration class to establish mappers.
 *
 * @author <a href="mailto:whitney@aiontechnology.io">Whitney Hunter</a>
 * @since 1.0.0
 */
@Configuration
public class MapperConfiguration {

    @Bean
    public InterestMapper interestMapper(InterestService interestService) {
        return new InterestMapper(interestService::findInterestByName);
    }

    @Bean
    public LeadershipSkillMapper leadershipSkillMapper(LeadershipSkillService leadershipSkillService) {
        return new LeadershipSkillMapper(leadershipSkillService::findLeadershipSkillByName);
    }

    @Bean
    public LeadershipTraitMapper leadershipTraitMapper(LeadershipTraitService leadershipTraitService) {
        return new LeadershipTraitMapper(leadershipTraitService::findCharacterTraitByName);
    }

    @Bean
    public PhonogramMapper phonogramMapper(PhonogramService phonogramService) {
        return new PhonogramMapper(phonogramService::findPhonogramByName);
    }

    @Bean
    public BehaviorMapper behaviorMapper(BehaviorService behaviorService) {
        return new BehaviorMapper(behaviorService::findBehaviorByName);
    }

}
