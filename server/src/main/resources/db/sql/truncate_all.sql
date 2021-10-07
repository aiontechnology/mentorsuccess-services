-- Copyright 2020-2021 Aion Technology LLC
--
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
--
--      http://www.apache.org/licenses/LICENSE-2.0
--
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.

truncate act_evt_log cascade;
truncate act_ge_bytearray cascade;
truncate act_ge_property cascade;
truncate act_hi_actinst cascade;
truncate act_hi_attachment cascade;
truncate act_hi_comment cascade;
truncate act_hi_detail cascade;
truncate act_hi_entitylink cascade;
truncate act_hi_identitylink cascade;
truncate act_hi_procinst cascade;
truncate act_hi_taskinst cascade;
truncate act_hi_tsk_log cascade;
truncate act_hi_varinst cascade;
truncate act_id_bytearray cascade;
truncate act_id_group cascade;
truncate act_id_info cascade;
truncate act_id_membership cascade;
truncate act_id_priv cascade;
truncate act_id_priv_mapping cascade;
truncate act_id_property cascade;
truncate act_id_token cascade;
truncate act_id_user cascade;
truncate act_procdef_info cascade;
truncate act_re_deployment cascade;
truncate act_re_model cascade;
truncate act_re_procdef cascade;
truncate act_ru_actinst cascade;
truncate act_ru_deadletter_job cascade;
truncate act_ru_entitylink cascade;
truncate act_ru_event_subscr cascade;
truncate act_ru_execution cascade;
truncate act_ru_external_job cascade;
truncate act_ru_history_job cascade;
truncate act_ru_identitylink cascade;
truncate act_ru_job cascade;
truncate act_ru_suspended_job cascade;
truncate act_ru_task cascade;
truncate act_ru_timer_job cascade;
truncate act_ru_variable cascade;
truncate activity_focus cascade;
truncate behavior cascade;
truncate book cascade;
truncate book_behavior cascade;
truncate book_interest cascade;
truncate book_leadershipskill cascade;
truncate book_leadershiptrait cascade;
truncate book_phonogram cascade;
truncate book_tag cascade;
-- truncate databasechangelog cascade;
-- truncate databasechangeloglock cascade;
truncate flw_channel_definition cascade;
truncate flw_ev_databasechangelog cascade;
truncate flw_ev_databasechangeloglock cascade;
truncate flw_event_definition cascade;
truncate flw_event_deployment cascade;
truncate flw_event_resource cascade;
truncate flw_ru_batch cascade;
truncate flw_ru_batch_part cascade;
truncate game cascade;
truncate game_activityfocus cascade;
truncate game_leadershipskill cascade;
truncate game_leadershiptrait cascade;
truncate interest cascade;
truncate leadership_skill cascade;
truncate leadership_trait cascade;
truncate person cascade;
truncate phonogram cascade;
truncate school cascade;
truncate school_book cascade;
truncate school_game cascade;
truncate school_person_role cascade;
truncate student cascade;
truncate student_behavior cascade;
truncate student_interest cascade;
truncate student_leadershipskill cascade;
truncate student_leadershiptrait cascade;
truncate student_mentor cascade;
truncate student_person_role cascade;
truncate student_teacher cascade;
truncate tag cascade;
