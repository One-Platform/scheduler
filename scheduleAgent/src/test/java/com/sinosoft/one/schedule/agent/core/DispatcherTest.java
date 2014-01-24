package com.sinosoft.one.schedule.agent.core;

import com.sinosoft.one.schedule.agent.exception.ScheduleAgentException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.quartz.JobDetail;
import org.quartz.Trigger;

import java.util.List;
import java.util.Map;

/**
 * Created by bin on 14-1-21.
 */
public class DispatcherTest {

    Dispatcher dispatcher;
    List<JobDetail> jobDetails;
    @Before
    public void before(){
        dispatcher = new Dispatcher();
        try {
            dispatcher.initProperties();
            jobDetails = dispatcher.createJobDetail();
        } catch (ScheduleAgentException e) {
            e.printStackTrace();
        }

    }

    @Test(expected = ScheduleAgentException.class)
    public void initProperties() throws ScheduleAgentException {


        dispatcher.initProperties();

        Assert.assertNotNull(dispatcher.getProperties());
        Assert.assertEquals("scheduleAgent", dispatcher.getClientId());
//        Assert.assertEquals("127.0.0.1",dispatcher.getClientIp());
//        Assert.assertEquals("8000",dispatcher.getClientPort());
        Assert.assertEquals("",dispatcher.getClientUsername());
        Assert.assertEquals("",dispatcher.getClientPassword());
//        Assert.assertEquals("a1",dispatcher.getJobName());
//        Assert.assertEquals("127.0.0.1",dispatcher.getServerIp());
//        Assert.assertEquals("9000",dispatcher.getServerPort());
        Assert.assertEquals("admin",dispatcher.getServerUsername());
        Assert.assertEquals("admin",dispatcher.getServerPassword());
        Assert.assertEquals("",dispatcher.getServerMatchKey());
//        Assert.assertEquals("true",dispatcher.getDependOnServer());
    }

    //@Test(expected = ScheduleAgentException.class)
    @Test
   public void createJobDetailTest() throws ScheduleAgentException {

        dispatcher.initProperties();
        List<JobDetail> jobDetails =  dispatcher.createJobDetail();
        Assert.assertNotNull(jobDetails);
   }

    @Test
   public void  createJobTriggerTest(){
        try {
            Map<JobDetail,List<Trigger>> jobDetailListMap =  dispatcher.createJobTrigger(jobDetails);
            Assert.assertNotNull(jobDetailListMap);
        } catch (ScheduleAgentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void startTest(){
        dispatcher.start();
    }

}
