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

import io.aiontechnology.mentorsuccess.api.mapping.ActivityFocusMapper;
import io.aiontechnology.mentorsuccess.api.mapping.BehaviorMapper;
import io.aiontechnology.mentorsuccess.api.mapping.InterestMapper;
import io.aiontechnology.mentorsuccess.api.mapping.LeadershipSkillMapper;
import io.aiontechnology.mentorsuccess.api.mapping.LeadershipTraitMapper;
import io.aiontechnology.mentorsuccess.api.mapping.PhonogramMapper;
import io.aiontechnology.mentorsuccess.entity.ActivityFocus;
import io.aiontechnology.mentorsuccess.entity.Behavior;
import io.aiontechnology.mentorsuccess.entity.Interest;
import io.aiontechnology.mentorsuccess.entity.LeadershipSkill;
import io.aiontechnology.mentorsuccess.entity.LeadershipTrait;
import io.aiontechnology.mentorsuccess.entity.Phonogram;
import io.aiontechnology.mentorsuccess.service.ActivityFocusService;
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
 * @author Whitney Hunter
 * @since 0.1.0
 */
@Configuration
public class MapperConfiguration {

    /**
     * Create a {@link ActivityFocusMapper}.
     *
     * @param activityFocusService The {@link ActivityFocusService} from which {@link ActivityFocus} objects can be retrieved.
     * @return The created {@link ActivityFocusMapper}.
     */
    @Bean
    public ActivityFocusMapper activityFocusMapper(ActivityFocusService activityFocusService) {
        return new ActivityFocusMapper(activityFocusService::findActivityFocusByName);
    }

    /**
     * Create a {@link BehaviorMapper}.
     *
     * @param behaviorService The {@link BehaviorService} from which {@link Behavior} objects can be retrieved.
     * @return The created {@link BehaviorMapper}.
     */
    @Bean
    public BehaviorMapper behaviorMapper(BehaviorService behaviorService) {
        return new BehaviorMapper(behaviorService::findBehaviorByName);
    }

    /**
     * Create an {@link InterestMapper}.
     *
     * @param interestService The {@link InterestService} from which {@link Interest} objects can be retrieved.
     * @return The created {@link InterestMapper}.
     */
    @Bean
    public InterestMapper interestMapper(InterestService interestService) {
        return new InterestMapper(interestService::findInterestByName);
    }

    /**
     * Create a {@link LeadershipSkillMapper}.
     *
     * @param leadershipSkillService The {@link LeadershipSkillService} from which {@link LeadershipSkill} objects can be retrieved.
     * @return The created {@link LeadershipSkillMapper}.
     */
    @Bean
    public LeadershipSkillMapper leadershipSkillMapper(LeadershipSkillService leadershipSkillService) {
        return new LeadershipSkillMapper(leadershipSkillService::findLeadershipSkillByName);
    }

    /**
     * Create a {@link LeadershipTraitMapper}.
     *
     * @param leadershipTraitService The {@link LeadershipTraitService} from which {@link LeadershipTrait} objects can be retrieved.
     * @return The created {@link LeadershipTraitMapper}.
     */
    @Bean
    public LeadershipTraitMapper leadershipTraitMapper(LeadershipTraitService leadershipTraitService) {
        return new LeadershipTraitMapper(leadershipTraitService::findLeadershipTraitByName);
    }

    /**
     * Create a {@link PhonogramMapper}.
     *
     * @param phonogramService The {@link PhonogramService} from which {@link Phonogram} objects can be retrieved.
     * @return The created {@link PhonogramMapper}.
     */
    @Bean
    public PhonogramMapper phonogramMapper(PhonogramService phonogramService) {
        return new PhonogramMapper(phonogramService::findPhonogramByName);
    }

}
