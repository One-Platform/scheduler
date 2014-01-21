package com.sinosoft.one.schedule.agent.utils;

import com.sinosoft.one.schedule.agent.dto.ClientInfo;
import com.sinosoft.one.schedule.agent.dto.JobInfo;
import com.sinosoft.one.schedule.agent.dto.Result;
import com.sinosoft.one.schedule.agent.dto.ServerInfo;

/**
 * 连接器
 * Created by bin on 14-1-17.
 */
public class Connector {

    private ServerInfo serverInfo;

    public Result sendData(ClientInfo clientInfo){
        Result result = new Result();
        return result;
    }

    public boolean sendData(JobInfo jobInfo){
        return false;
    }
}
