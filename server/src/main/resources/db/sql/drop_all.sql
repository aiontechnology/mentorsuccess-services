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

drop table if exists act_evt_log cascade;
drop table if exists act_ge_bytearray cascade;
drop table if exists act_ge_property cascade;
drop table if exists act_hi_actinst cascade;
drop table if exists act_hi_attachment cascade;
drop table if exists act_hi_comment cascade;
drop table if exists act_hi_detail cascade;
drop table if exists act_hi_entitylink cascade;
drop table if exists act_hi_identitylink cascade;
drop table if exists act_hi_procinst cascade;
drop table if exists act_hi_taskinst cascade;
drop table if exists act_hi_tsk_log cascade;
drop table if exists act_hi_varinst cascade;
drop table if exists act_id_bytearray cascade;
drop table if exists act_id_group cascade;
drop table if exists act_id_info cascade;
drop table if exists act_id_membership cascade;
drop table if exists act_id_priv cascade;
drop table if exists act_id_priv_mapping cascade;
drop table if exists act_id_property cascade;
drop table if exists act_id_token cascade;
drop table if exists act_id_user cascade;
drop table if exists act_procdef_info cascade;
drop table if exists act_re_deployment cascade;
drop table if exists act_re_model cascade;
drop table if exists act_re_procdef cascade;
drop table if exists act_ru_actinst cascade;
drop table if exists act_ru_deadletter_job cascade;
drop table if exists act_ru_entitylink cascade;
drop table if exists act_ru_event_subscr cascade;
drop table if exists act_ru_execution cascade;
drop table if exists act_ru_external_job cascade;
drop table if exists act_ru_history_job cascade;
drop table if exists act_ru_identitylink cascade;
drop table if exists act_ru_job cascade;
drop table if exists act_ru_suspended_job cascade;
drop table if exists act_ru_task cascade;
drop table if exists act_ru_timer_job cascade;
drop table if exists act_ru_variable cascade;
drop table if exists activity_focus cascade;
drop table if exists behavior cascade;
drop table if exists book cascade;
drop table if exists book_behavior cascade;
drop table if exists book_interest cascade;
drop table if exists book_leadershipskill cascade;
drop table if exists book_leadershiptrait cascade;
drop table if exists book_phonogram cascade;
drop table if exists book_tag cascade;
drop table if exists databasechangelog cascade;
drop table if exists databasechangeloglock cascade;
drop table if exists flw_channel_definition cascade;
drop table if exists flw_ev_databasechangelog cascade;
drop table if exists flw_ev_databasechangeloglock cascade;
drop table if exists flw_event_definition cascade;
drop table if exists flw_event_deployment cascade;
drop table if exists flw_event_resource cascade;
drop table if exists flw_ru_batch cascade;
drop table if exists flw_ru_batch_part cascade;
drop table if exists game cascade;
drop table if exists game_activityfocus cascade;
drop table if exists game_leadershipskill cascade;
drop table if exists game_leadershiptrait cascade;
drop table if exists interest cascade;
drop table if exists leadership_skill cascade;
drop table if exists leadership_trait cascade;
drop table if exists person cascade;
drop table if exists phonogram cascade;
drop table if exists school cascade;
drop table if exists school_book cascade;
drop table if exists school_game cascade;
drop table if exists school_person_role cascade;
drop table if exists school_session cascade;
drop table if exists student cascade;
drop table if exists student_activityfocus cascade;
drop table if exists student_behavior cascade;
drop table if exists student_interest cascade;
drop table if exists student_leadershipskill cascade;
drop table if exists student_leadershiptrait cascade;
drop table if exists student_mentor cascade;
drop table if exists student_person_role cascade;
drop table if exists student_schoolsession cascade;
drop table if exists student_teacher cascade;
drop table if exists tag cascade;
