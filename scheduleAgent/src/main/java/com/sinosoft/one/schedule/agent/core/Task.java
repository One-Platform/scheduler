package com.sinosoft.one.schedule.agent.core;

import org.apache.log4j.Logger;
import org.quartz.*;

/**
 * 本类提供给外部继承写业务逻辑
 * Created by bin on 14-1-2.
 */
  class Task implements Job{

    private static Logger logger = Logger.getLogger(Task.class);

    private final JobTask jobTask;

    Task(final JobTask jobTask){
        this.jobTask = jobTask;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try{
            jobTask.execute();
        }catch (Throwable t){
           throw new JobExecutionException(t);
        }
    }



}
