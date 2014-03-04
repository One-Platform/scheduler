package com.sinosoft.one.schedule.agent.core;

import org.quartz.JobDetail;

import java.util.List;

/**
 * Created by bin on 14-1-26.
 */
public class TriggerFactory {

    public static AgentTrigger createAgentTrigger(String flag,List<JobDetail> jobDetails){
        if("false".equals(flag)){

        }
        return null;
    }
}
