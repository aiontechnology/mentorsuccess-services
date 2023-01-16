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

package io.aiontechnology.mentorsuccess.workflow;

import io.aiontechnology.mentorsuccess.entity.School;
import io.aiontechnology.mentorsuccess.model.inbound.InboundInvitation;
import io.aiontechnology.mentorsuccess.util.UriBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

import java.io.StringWriter;
import java.util.Properties;

import static io.aiontechnology.mentorsuccess.workflow.RegistrationWorkflowConstants.BASE_REGISTRATION_URI;
import static io.aiontechnology.mentorsuccess.workflow.RegistrationWorkflowConstants.INVITATION;
import static io.aiontechnology.mentorsuccess.workflow.RegistrationWorkflowConstants.PROGRAM_ADMIN_EMAIL;
import static io.aiontechnology.mentorsuccess.workflow.RegistrationWorkflowConstants.PROGRAM_ADMIN_NAME;
import static io.aiontechnology.mentorsuccess.workflow.RegistrationWorkflowConstants.PROGRAM_ADMIN_PHONE;
import static io.aiontechnology.mentorsuccess.workflow.RegistrationWorkflowConstants.SCHOOL;

/**
 * @author Whitney Hunter
 * @since 1.1.0
 */
@Slf4j
public class StudentRegistrationEmailGenerationTask implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        School school = execution.getVariable(SCHOOL, School.class);
        InboundInvitation invitation = execution.getVariable(INVITATION, InboundInvitation.class);

        initializeVelocity();
        String registrationUri = createRegistrationUri(execution, school, invitation);
        String email = generateEmail(execution, createVelocityContext(execution, school, registrationUri));
        execution.setVariable("emailBody", email);
    }

    private String createRegistrationUri(DelegateExecution execution, School school, InboundInvitation invitation) {
        return new UriBuilder(invitation.getStudentRegistrationUri())
                .withPathAddition("schools")
                .withPathAddition(school.getId().toString())
                .withPathAddition("registrations")
                .withPathAddition(execution.getProcessInstanceId())
                .build();
    }

    private VelocityContext createVelocityContext(DelegateExecution execution, School school, String registrationUri) {
        VelocityContext context = new VelocityContext();
        InboundInvitation invitation = execution.getVariable(INVITATION, InboundInvitation.class);

        context.put(SCHOOL, school);
        context.put(BASE_REGISTRATION_URI, registrationUri);
        context.put(PROGRAM_ADMIN_NAME, execution.getVariable(PROGRAM_ADMIN_NAME));
        context.put(PROGRAM_ADMIN_EMAIL, execution.getVariable(PROGRAM_ADMIN_EMAIL));
        context.put(PROGRAM_ADMIN_PHONE, execution.getVariable(PROGRAM_ADMIN_PHONE));
        context.put(INVITATION, invitation);
        return context;
    }

    private String generateEmail(DelegateExecution execution, VelocityContext context) {
        Template template = Velocity.getTemplate("templates/registration-email.vm");
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        return writer.toString();
    }

    private void initializeVelocity() {
        Properties p = new Properties();
        p.setProperty("resource.loader", "class");
        p.setProperty("class.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(p);
    }

}
