package com.sinosoft.one.schedule.agent.dto;

/**
 * 数据传输对象，client端的数据格式
 * Created by bin on 14-1-17.
 */
public class ClientInfo {

    private String clientId;
    private String clientIp;
    private String clientPort;
    private String clientUsername;
    private String clientPassword;
    private String serverMatchKey;

    public String getServerMatchKey() {
        return serverMatchKey;
    }

    public void setServerMatchKey(String serverMatchKey) {
        this.serverMatchKey = serverMatchKey;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getClientPort() {
        return clientPort;
    }

    public void setClientPort(String clientPort) {
        this.clientPort = clientPort;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }

    public String getClientPassword() {
        return clientPassword;
    }

    public void setClientPassword(String clientPassword) {
        this.clientPassword = clientPassword;
    }
}
