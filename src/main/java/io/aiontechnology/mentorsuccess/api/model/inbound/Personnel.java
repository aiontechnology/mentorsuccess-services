/*
 * Copyright 2020 Aion Technology LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.aiontechnology.mentorsuccess.api.model.inbound;

/**
 * The interface of a school's personnel.
 *
 * @author Whitney Hunter
 * @since 0.1.0
 */
public interface Personnel {

    /**
     * Get the personnel's first name.
     *
     * @return The first name.
     */
    String getFirstName();

    /**
     * Get the personnel's last name.
     *
     * @return The last name.
     */
    String getLastName();

    /**
     * Get the personnel's email address.
     *
     * @return The email address.
     */
    String getEmail();

    /**
     * Get the personnel's work phone number.
     *
     * @return The work phone number.
     */
    String getWorkPhone();

    /**
     * Get the personnel's cell phone number.
     *
     * @return The cell phone number.
     */
    String getCellPhone();

}
