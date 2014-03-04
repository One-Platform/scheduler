package com.sinosoft.one.schedule.agent.test;

import com.sinosoft.one.schedule.agent.core.JobTask;


/**
 * Created by bin on 14-1-24.
 */
public class TaskTest implements JobTask{


    private int count;

    @Override
    public void execute() {
        System.out.println("TaskTest start");
        count++;
    }
}
