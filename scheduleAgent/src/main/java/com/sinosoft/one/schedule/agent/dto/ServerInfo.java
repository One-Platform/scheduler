package com.sinosoft.one.schedule.agent.dto;

/**
 * 数据传输对象，Server端信息
 * Created by bin on 14-1-17.
 */
public class ServerInfo {

    private String serverIp;
    private String serverPort;
    private String serverUsername;
    private String serverPassword;

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getServerPort() {
        return serverPort;
    }

    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }

    public String getServerUsername() {
        return serverUsername;
    }

    public void setServerUsername(String serverUsername) {
        this.serverUsername = serverUsername;
    }

    public String getServerPassword() {
        return serverPassword;
    }

    public void setServerPassword(String serverPassword) {
        this.serverPassword = serverPassword;
    }
}
