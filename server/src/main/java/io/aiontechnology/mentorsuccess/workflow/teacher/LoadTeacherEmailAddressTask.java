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

package io.aiontechnology.mentorsuccess.workflow.teacher;

import io.aiontechnology.mentorsuccess.workflow.TaskUtilities;
import lombok.RequiredArgsConstructor;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import static io.aiontechnology.mentorsuccess.workflow.RegistrationWorkflowConstants.TEACHER_EMAIL_ADDRESS;

@Service
@RequiredArgsConstructor
public class LoadTeacherEmailAddressTask implements JavaDelegate {

    private final TaskUtilities taskUtilities;

    @Override
    public void execute(DelegateExecution execution) {
        taskUtilities.getTeacherEmailAddress(execution)
                .ifPresent(emailAddress -> execution.setTransientVariable(TEACHER_EMAIL_ADDRESS, emailAddress));
    }

}
