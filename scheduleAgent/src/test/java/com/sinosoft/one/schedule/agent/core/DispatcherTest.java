package com.sinosoft.one.schedule.agent.core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by bin on 14-1-21.
 */
public class DispatcherTest {

    Dispatcher dispatcher;
    @Before
    public void before(){
        dispatcher = new Dispatcher();
    }

    @Test
    public void initProperties(){

        dispatcher.initProperties();
        Assert.assertNotNull(dispatcher.getProperties());
        Assert.assertEquals("scheduleAgent", dispatcher.getClientId());
        Assert.assertEquals("127.0.0.1",dispatcher.getClientIp());
        Assert.assertEquals("8000",dispatcher.getClientPort());
        Assert.assertEquals("",dispatcher.getClientUsername());
        Assert.assertEquals("",dispatcher.getClientPassword());
        Assert.assertEquals("a1",dispatcher.getJobName());
        Assert.assertEquals("127.0.0.1",dispatcher.getServerIp());
        Assert.assertEquals("9000",dispatcher.getServerPort());
        Assert.assertEquals("admin",dispatcher.getServerUsername());
        Assert.assertEquals("admin",dispatcher.getServerPassword());
        Assert.assertEquals("",dispatcher.getServerMatchKey());
        Assert.assertEquals("false",dispatcher.getDependOnServer());
    }


}
