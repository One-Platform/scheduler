package com.sinosoft.one.schedule.agent.core;

import org.apache.log4j.Logger;
import org.quartz.*;

/**
 * 本类提供给外部继承写业务逻辑
 * Created by bin on 14-1-2.
 */
public abstract class Task implements Job{

    private static Logger logger = Logger.getLogger(Task.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try{
           doBiz();

        }catch (Throwable t){
         //  logger.error(t.getMessage());

           throw new JobExecutionException(t);
        }
    }

    /**
     * 提供给外部实现的方法,里面写业务逻辑
     * @throws Throwable
     */
    public abstract void doBiz() throws Throwable;

}
