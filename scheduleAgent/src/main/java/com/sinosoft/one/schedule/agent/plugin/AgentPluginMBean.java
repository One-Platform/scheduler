package com.sinosoft.one.schedule.agent.plugin;

import com.sinosoft.one.schedule.agent.dto.TriggerInfo;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.spi.ClassLoadHelper;
import org.quartz.spi.SchedulerPlugin;

/**
 * Created by bin on 14-1-17.
 */
public class AgentPluginMBean implements AgentPlugin,SchedulerPlugin {
    @Override
    public void add(TriggerInfo triggerInfo) {

    }

    @Override
    public void update(TriggerInfo triggerInfo) {

    }

    @Override
    public void delete(TriggerInfo triggerInfo) {

    }

    @Override
    public void initialize(String name, Scheduler scheduler, ClassLoadHelper loadHelper) throws SchedulerException {

    }

    @Override
    public void start() {

    }

    @Override
    public void shutdown() {

    }
}
