-- Copyright 2020 Aion Technology LLC
--
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
--
--   http://www.apache.org/licenses/LICENSE-2.0
--
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.

INSERT INTO leadership_skill (id, name) VALUES ('81aea425-4895-462d-9b9f-48a3150b7e49', 'LEADERSHIP_SKILL1');
INSERT INTO leadership_skill (id, name) VALUES ('d14ae031-b14e-4c9a-a9ba-d67703e80c29', 'LEADERSHIP_SKILL2');

INSERT INTO game (id, name, grade1, grade2, is_active, location) VALUES ('f53af381-d524-40f7-8df9-3e808c9ad46b', 'NAME', 1, 2, true, 'OFFLINE');
INSERT INTO game_leadershipskill (game_id, leadershipskill_id) VALUES ('f53af381-d524-40f7-8df9-3e808c9ad46b', '81aea425-4895-462d-9b9f-48a3150b7e49');
