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

package com.workplacesystems.queuj;

import com.workplacesystems.queuj.process.ProcessImplServer;
import com.workplacesystems.queuj.process.ProcessWrapper;
import com.workplacesystems.queuj.process.QueujFactory;
import java.io.Serializable;

import com.workplacesystems.queuj.utils.User;
import java.util.GregorianCalendar;

/**
 * This is the public interface to ProcessImpl and PartitionedProcessImpl.
 *
 * @author dave
 */
public final class Process implements Serializable {

    private String queueOwner;

    private Integer processKey;

    private transient ProcessWrapper process;

    public Process(ProcessWrapper process) {
        this.process = process;
        this.queueOwner = process.getQueueOwner();
        this.processKey = process.getProcessKey();
    }

    private synchronized ProcessWrapper getProcess() {
        if (process == null) {
            ProcessServer server = QueujFactory.getProcessServer(queueOwner);
            process = ((ProcessImplServer)server).get(processKey);
        }
        return process;
    }

    public String getQueueOwner() {
        return queueOwner;
    }

    public Integer getProcessKey() {
        return processKey;
    }

    public Serializable setParameter(String key, Serializable value) {
        return getProcess().setParameter(key, value);
    }

    public Object getParameter(String key) {
        return getProcess().getParameter(key);
    }

    public Queue getQueue() {
        return getProcess().getQueue();
    }

    public ProcessServer getContainingServer() {
        return getProcess().getContainingServer();
    }

    public void attach() {
        getProcess().attach();
    }

    public boolean isNotRun() {
        return getProcess().isNotRun();
    }

    public boolean isFailed() {
        return getProcess().isFailed();
    }

    public GregorianCalendar getNextRunTime() {
        return getProcess().getNextRunTime();
    }

    public boolean restart(User user) {
        return getProcess().restart(user);
    }

    public boolean restart() {
        return getProcess().restart();
    }

    public boolean delete(User user) {
        return getProcess().delete(user);
    }

    public boolean delete() {
        return getProcess().delete();
    }

    public void notifySelf() {
        getProcess().notifySelf();
    }

    public String getProcessName() {
        return getProcess().getProcessName();
    }

    public String getDescription() {
        return getProcess().getDescription();
    }

    public String getStatusDescription() {
        return getProcess().getStatusDescription();
    }

    public String getStatusImg() {
        return getProcess().getStatusImg();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Process other = (Process) obj;
        if (this.getProcess() != other.getProcess() && (this.getProcess() == null || !this.getProcess().equals(other.getProcess()))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.getProcess() != null ? this.getProcess().hashCode() : 0);
        return hash;
    }
}
