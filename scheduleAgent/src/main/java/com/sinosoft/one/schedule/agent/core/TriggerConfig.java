package com.sinosoft.one.schedule.agent.core;

/**
 * Created by bin on 14-1-26.
 */
public class TriggerConfig {

    private String jobName;
    private String cronExpression;

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
}
