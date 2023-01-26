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

import io.aiontechnology.mentorsuccess.api.mapping.toentity.misc.UriModelToRoleMapper;
import io.aiontechnology.mentorsuccess.model.inbound.student.InboundStudentRegistration;
import io.aiontechnology.mentorsuccess.workflow.EmailGenerationTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.flowable.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static io.aiontechnology.mentorsuccess.workflow.RegistrationWorkflowConstants.REGISTRATION;
import static io.aiontechnology.mentorsuccess.workflow.RegistrationWorkflowConstants.TEACHER_NAME;

@Slf4j
@Service("registrationCompleteEmailGenerator")
@RequiredArgsConstructor
public class RegistrationCompleteEmailGenerationTask extends EmailGenerationTask {

    private final UriModelToRoleMapper uriToRoleMapper;

    @Override
    protected void extendVelocityContext(DelegateExecution execution, VelocityContext context) {
        InboundStudentRegistration registration = execution.getVariable(REGISTRATION, InboundStudentRegistration.class);
        context.put(REGISTRATION, registration);

        context.put(TEACHER_NAME,
                Optional.ofNullable(registration.getTeacher())
                        .flatMap(uriToRoleMapper::map)
                        .map(role -> role.getPerson().getFullName())
                        .orElse(null));
    }

    @Override
    protected Template getTemplate() {
        return Velocity.getTemplate("templates/registration-complete-email.vm");
    }

}
