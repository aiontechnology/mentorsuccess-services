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

package io.aiontechnology.mentorsuccess.service;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.AdminAddUserToGroupRequest;
import com.amazonaws.services.cognitoidp.model.AdminAddUserToGroupResult;
import com.amazonaws.services.cognitoidp.model.AdminCreateUserRequest;
import com.amazonaws.services.cognitoidp.model.AdminCreateUserResult;
import com.amazonaws.services.cognitoidp.model.AttributeType;
import io.aiontechnology.mentorsuccess.model.inbound.InboundProgramAdmin;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.amazonaws.services.cognitoidp.model.DeliveryMediumType.EMAIL;
import static java.lang.Boolean.FALSE;

/**
 * @author Whitney Hunter
 * @since 0.12.0
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AwsService {

    @Value("${aws.cognito.userPoolId}")
    private String userPoolId;

    private final AWSCognitoIdentityProvider awsCognitoIdentityProvider;

    public InboundProgramAdmin addAwsUser(UUID schoolId, InboundProgramAdmin programAdmin) {

        AdminCreateUserResult addUserResult = createAwsUser(schoolId, programAdmin);
        log.debug("Sent add user request. Result: {}", addUserResult);

        AdminAddUserToGroupResult addUserToGroupResult = addAwsUserToGroup(programAdmin);
        log.debug("Sent add user to group request. Result: {}", addUserToGroupResult);

        return programAdmin;
    }

    private AdminCreateUserResult createAwsUser(UUID schoolId, InboundProgramAdmin programAdmin) {
        log.debug("Adding a program admin to AWS");
        AdminCreateUserRequest cognitoRequest = new AdminCreateUserRequest()
                .withUserPoolId(userPoolId)
                .withUsername(programAdmin.getEmail())
                .withUserAttributes(
                        new AttributeType()
                                .withName("email")
                                .withValue(programAdmin.getEmail()),
                        new AttributeType()
                                .withName("given_name")
                                .withValue(programAdmin.getFirstName()),
                        new AttributeType()
                                .withName("family_name")
                                .withValue(programAdmin.getLastName()),
                        new AttributeType()
                                .withName("email_verified")
                                .withValue("true"),
                        new AttributeType()
                                .withName("custom:school_uuid")
                                .withValue(schoolId.toString()))
                .withDesiredDeliveryMediums(EMAIL)
                .withForceAliasCreation(FALSE);
        return awsCognitoIdentityProvider.adminCreateUser(cognitoRequest);
    }

    private AdminAddUserToGroupResult addAwsUserToGroup(InboundProgramAdmin programAdmin) {
        AdminAddUserToGroupRequest cognitoRequest = new AdminAddUserToGroupRequest()
                .withUsername(programAdmin.getEmail())
                .withUserPoolId(userPoolId)
                .withGroupName("PROGRAM_ADMIN");
        return awsCognitoIdentityProvider.adminAddUserToGroup(cognitoRequest);
    }

}
