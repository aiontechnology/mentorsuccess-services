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

package io.aiontechnology.mentorsuccess.api.mapping;

import io.aiontechnology.mentorsuccess.entity.StudentBehavior;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Sync that collection passed in as toSync to match the collection passed in as syncFrom
 *
 * @author Whitney Hunter
 * @since 0.3.0
 */
public class CollectionSyncHelper<T> {

    public Collection<T> sync(Collection<T> toSync, Collection<T> syncFrom) {
        Collection<T> newCollection = new ArrayList<>(syncFrom);
        Collection<T> toRemove = new ArrayList<>(toSync);
        toRemove.removeAll(newCollection);
        Collection<T> toAdd = new ArrayList<>(newCollection);
        toAdd.removeAll(syncFrom);

        toSync.removeAll(toRemove);
        toSync.addAll(toAdd);
        return toSync;
    }

}
