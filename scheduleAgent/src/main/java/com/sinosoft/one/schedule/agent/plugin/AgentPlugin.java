package com.sinosoft.one.schedule.agent.plugin;

import com.sinosoft.one.schedule.agent.dto.TriggerInfo;

/**
 * 客户端暴露给外部调用的Mbean接口
 * Created by bin on 14-1-17.
 */
public interface AgentPlugin {

    public void add(TriggerInfo triggerInfo);

    public void update(TriggerInfo triggerInfo);

    public void delete(TriggerInfo triggerInfo);
}
