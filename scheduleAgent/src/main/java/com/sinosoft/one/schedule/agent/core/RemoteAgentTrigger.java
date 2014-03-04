package com.sinosoft.one.schedule.agent.core;

import org.quartz.JobDetail;
import org.quartz.Trigger;

import java.util.List;
import java.util.Map;

/**
 * Created by bin on 14-1-26.
 */
public class RemoteAgentTrigger implements AgentTrigger{

    @Override
    public Map<JobDetail, List<Trigger>> createJobTrigger(List<JobDetail> jobDetails) {
        return null;
    }
}
