package com.sinosoft.one.schedule.agent.test;

import com.sinosoft.one.schedule.agent.core.Task;

/**
 * Created by bin on 14-1-24.
 */
public class TaskTest extends Task{
    @Override
    public void doBiz() throws Throwable {
        System.out.println("TaskTest start");
    }
}
