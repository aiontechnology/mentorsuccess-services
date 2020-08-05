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

variable "region" {
  description = "AWS region"
  type = string
  default = "us-west-2"
}

variable "name" {
  description = "The project name"
  type = string
  default = "mentorsuccess"
}

variable "environment" {
  description = "The name of the environment (e.g. dev, test, prod)"
  type = string
}

variable "token_redirect" {
  description = "The URL to which Cognito tokens should be redirected"
  type = string
}

variable "logout_redirect" {
  description = "The URL to which the app will be redirected upon logout"
  type = string
}