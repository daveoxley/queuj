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

package com.workplacesystems.queuj.process.seam;

import com.workplacesystems.queuj.process.jpa.ProcessImpl;
import com.workplacesystems.queuj.process.ProcessPersistence;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

/**
 *
 * @author dave
 */
@Name("processImplHome")
public class ProcessImplHome extends EntityHome<ProcessImpl> implements ProcessPersistence<ProcessImpl,Integer> {

    @Override
    public void setId(Integer id) {
        super.setId(id);
    }

    @Override
    protected ProcessImpl loadInstance() 
    {
        ProcessImpl p = super.loadInstance();
        getEntityManager().refresh(p);
        return p;
    }
}
