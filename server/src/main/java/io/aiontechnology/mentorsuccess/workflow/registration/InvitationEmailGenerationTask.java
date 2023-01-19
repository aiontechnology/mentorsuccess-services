/*
 * Copyright 2021-2023 Aion Technology LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.aiontechnology.mentorsuccess.workflow.registration;

import io.aiontechnology.mentorsuccess.entity.School;
import io.aiontechnology.mentorsuccess.model.inbound.InboundInvitation;
import io.aiontechnology.mentorsuccess.util.UriBuilder;
import io.aiontechnology.mentorsuccess.workflow.EmailGenerationTask;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.flowable.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Service;

import static io.aiontechnology.mentorsuccess.workflow.RegistrationWorkflowConstants.BASE_REGISTRATION_URI;
import static io.aiontechnology.mentorsuccess.workflow.RegistrationWorkflowConstants.INVITATION;
import static io.aiontechnology.mentorsuccess.workflow.RegistrationWorkflowConstants.SCHOOL;

/**
 * @author Whitney Hunter
 * @since 1.1.0
 */
@Slf4j
@Service("invitationEmailGenerator")
public class InvitationEmailGenerationTask extends EmailGenerationTask {

    @Override
    protected void extendVelocityContext(DelegateExecution execution, VelocityContext context) {
        var school = execution.getVariable(SCHOOL, School.class);
        context.put(SCHOOL, school);

        InboundInvitation invitation = execution.getVariable(INVITATION, InboundInvitation.class);
        context.put(BASE_REGISTRATION_URI, createRegistrationUri(execution, school, invitation));
        context.put(INVITATION, invitation);
    }

    @Override
    protected Template getTemplate() {
        return Velocity.getTemplate("templates/invitation-email.vm");
    }

    private String createRegistrationUri(DelegateExecution execution, School school, InboundInvitation invitation) {
        return new UriBuilder(invitation.getStudentRegistrationUri())
                .withPathAddition("schools")
                .withPathAddition(school.getId().toString())
                .withPathAddition("registrations")
                .withPathAddition(execution.getProcessInstanceId())
                .build();
    }

}
