# Copyright 2020 Aion Technology LLC
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

####################################################################################################
# Define locals
####################################################################################################
locals {
  resource_tag = "${var.name}-${var.environment}"
}

####################################################################################################
# Define Cognito
####################################################################################################
resource "aws_cognito_user_pool" "user_pool" {
  name = "${local.resource_tag}-user-pool"
  username_configuration {
    case_sensitive = false
  }
  username_attributes = ["email", "phone_number"]
  auto_verified_attributes = []
  schema {
    name = "family_name"
    attribute_data_type = "String"
    required = true
  }
  schema {
    name = "given_name"
    attribute_data_type = "String"
    required = true
  }
  admin_create_user_config {
    allow_admin_create_user_only = true
    invite_message_template {
      email_subject = "MentorSuccess Account"
      email_message = <<EOF
Hello. Your MentorSuccess account has been created. Your username is {username}. Your temporary password is {####}. You will be required to change it when you log in.
      EOF
      sms_message = "Your MentorSuccess username is {username} and temporary password is {####}. "
    }
  }
  tags = {
  }
}

resource "aws_cognito_user_pool_client" "user_pool_client" {
  name = "${local.resource_tag}-user-pool-client"
  user_pool_id = aws_cognito_user_pool.user_pool.id
  supported_identity_providers = ["COGNITO"]
  allowed_oauth_flows = ["implicit"]
  allowed_oauth_flows_user_pool_client = true
  allowed_oauth_scopes = ["openid", "profile"]
  callback_urls = [var.token_redirect]
  logout_urls = [var.logout_redirect]
}

resource "aws_cognito_user_pool_domain" "user_pool_domain" {
  domain = "mentorsuccess-${local.resource_tag}"
  user_pool_id = aws_cognito_user_pool.user_pool.id
}