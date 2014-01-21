package com.sinosoft.one.schedule.agent.core;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 定时任务处理本地保留的job执行信息
 * Created by bin on 14-1-17.
 */
public class AgentTimedTask implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

    }
}
