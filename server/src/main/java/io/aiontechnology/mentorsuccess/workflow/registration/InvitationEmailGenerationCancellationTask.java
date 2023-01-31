/*
 * Copyright 2023 Aion Technology LLC
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

import io.aiontechnology.mentorsuccess.model.inbound.InboundInvitation;
import io.aiontechnology.mentorsuccess.workflow.EmailGenerationTask;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.flowable.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Service;

import static io.aiontechnology.mentorsuccess.workflow.RegistrationWorkflowConstants.INVITATION;

@Service("invitationCancellationGenerator")
@Slf4j
public class InvitationEmailGenerationCancellationTask extends EmailGenerationTask {

    @Override
    protected void extendVelocityContext(DelegateExecution execution, VelocityContext context) {
        InboundInvitation invitation = execution.getVariable(INVITATION, InboundInvitation.class);
        context.put(INVITATION, invitation);
    }

    @Override
    protected Template getTemplate() {
        log.debug("Retrieving cancellation template");
        return Velocity.getTemplate("templates/invitation-cancelled-email.vm");
    }

}
