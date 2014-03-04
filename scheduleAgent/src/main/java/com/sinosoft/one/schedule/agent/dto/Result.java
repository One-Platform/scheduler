package com.sinosoft.one.schedule.agent.dto;

/**
 * 数据传输对象，服务器返回结果
 * Created by bin on 14-1-17.
 */
public class Result {

    private boolean flag;
    private String description;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
