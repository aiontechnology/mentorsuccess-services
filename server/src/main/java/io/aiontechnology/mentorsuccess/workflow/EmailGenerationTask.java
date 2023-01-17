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

package io.aiontechnology.mentorsuccess.workflow;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

import java.io.StringWriter;
import java.util.Properties;

import static io.aiontechnology.mentorsuccess.workflow.RegistrationWorkflowConstants.PROGRAM_ADMIN_EMAIL;
import static io.aiontechnology.mentorsuccess.workflow.RegistrationWorkflowConstants.PROGRAM_ADMIN_NAME;
import static io.aiontechnology.mentorsuccess.workflow.RegistrationWorkflowConstants.PROGRAM_ADMIN_PHONE;

public abstract class EmailGenerationTask implements JavaDelegate {

    @Override
    public final void execute(DelegateExecution execution) {
        VelocityContext context = initialize(execution);
        execution.setVariable("emailBody", generateEmail(context));
    }

    protected abstract void extendVelocityContext(DelegateExecution execution, VelocityContext context);

    protected abstract Template getTemplate();

    private VelocityContext createVelocityContext(DelegateExecution execution) {
        VelocityContext context = new VelocityContext();
        context.put(PROGRAM_ADMIN_NAME, execution.getVariable(PROGRAM_ADMIN_NAME));
        context.put(PROGRAM_ADMIN_EMAIL, execution.getVariable(PROGRAM_ADMIN_EMAIL));
        context.put(PROGRAM_ADMIN_PHONE, execution.getVariable(PROGRAM_ADMIN_PHONE));
        extendVelocityContext(execution, context);
        return context;
    }

    private String generateEmail(VelocityContext context) {
        StringWriter writer = new StringWriter();
        getTemplate().merge(context, writer);
        return writer.toString();
    }

    private VelocityContext initialize(DelegateExecution execution) {
        initializeVelocity();
        return createVelocityContext(execution);
    }

    private void initializeVelocity() {
        Properties p = new Properties();
        p.setProperty("resource.loader", "class");
        p.setProperty("class.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(p);
    }

}
