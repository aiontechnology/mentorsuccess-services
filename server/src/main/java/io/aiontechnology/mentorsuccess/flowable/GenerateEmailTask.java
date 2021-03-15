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

package io.aiontechnology.mentorsuccess.flowable;

import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

import java.io.StringWriter;
import java.util.Properties;

/**
 * @author Whitney Hunter
 * @since 1.1.0
 */
@Slf4j
public class GenerateEmailTask implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        log.debug("==> Executing Java delegate");
        Properties p = new Properties();
        p.setProperty("resource.loader", "class");
        p.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init( p );
        VelocityContext context = new VelocityContext();
        context.put("firstName", execution.getVariable("firstName"));
        Template template = Velocity.getTemplate("templates/registration-email.vm");
        StringWriter writer = new StringWriter();
        template.merge(context, writer);

        execution.setVariable("emailSubject", "This is a custom subject");
        execution.setVariable("emailBody", writer.toString());
    }

}
