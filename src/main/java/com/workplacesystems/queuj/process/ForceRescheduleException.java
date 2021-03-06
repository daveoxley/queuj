/*
 * Copyright 2010 Workplace Systems PLC (http://www.workplacesystems.com/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.workplacesystems.queuj.process;

import com.workplacesystems.queuj.Occurrence;

/**
 *
 * @author dave
 */
public class ForceRescheduleException extends RuntimeException {

    private final boolean commit_current_section;
    private final Occurrence occurence;

    public ForceRescheduleException(Occurrence occurence)
    {
        this(occurence, false);
    }

    public ForceRescheduleException(Occurrence occurence, boolean commit_current_section)
    {
        this.commit_current_section = commit_current_section;
        this.occurence = occurence;
    }

    boolean commitCurrentSection()
    {
        return commit_current_section;
    }

    protected Occurrence getNewOccurence(Occurrence old_occurence)
    {
        return occurence;
    }
}
