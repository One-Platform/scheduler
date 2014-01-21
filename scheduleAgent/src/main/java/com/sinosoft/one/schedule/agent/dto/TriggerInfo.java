package com.sinosoft.one.schedule.agent.dto;

/**
 * 数据传输对象，trigger的相关信息
 * Created by bin on 14-1-17.
 */
public class TriggerInfo {

    private String triggerKey;
    private String triggerName;
    private String cronExpression;

    public String getTriggerKey() {
        return triggerKey;
    }

    public void setTriggerKey(String triggerKey) {
        this.triggerKey = triggerKey;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }
}
