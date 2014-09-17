package com.sinosoft.one.schedule.agent.core;


import com.sinosoft.one.schedule.agent.dto.TriggerInfo;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import java.util.*;

/**
 * Created by bin on 14-1-26.
 */
public class LocalAgentTrigger implements AgentTrigger {

    private TriggerInfo triggerInfo;

    public LocalAgentTrigger(TriggerInfo triggerInfo){
        this.triggerInfo = triggerInfo;
    }

    @Override
    public Map<JobDetail, List<Trigger>> createJobTrigger(List<JobDetail> jobDetails) {
        Map<JobDetail,List<Trigger>> jobTriggerMap = new HashMap<JobDetail, List<Trigger>>();
        for(int i=0;i<jobDetails.size();i++){
            List<Trigger> triggers = new ArrayList<Trigger>();
            String jobName = jobDetails.get(i).getKey().getName();
            String[] triggerArray = triggerInfo.getCronExpression().split(",");
            for(int j=0;j<triggerArray.length;j++){
                Trigger trigger =  TriggerBuilder.newTrigger().withIdentity(jobName + ".trigger", jobName + ".trigger").forJob(jobDetails.get(i)).withSchedule(CronScheduleBuilder.cronSchedule(triggerArray[i])).build();
                triggers.add(trigger);
            }
            jobTriggerMap.put(jobDetails.get(i),triggers);
        }
        return jobTriggerMap;
    }
}
