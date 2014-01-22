package com.sinosoft.one.schedule.agent.core;

import org.apache.log4j.Logger;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import java.io.*;
import java.util.*;

/**
 * Created by bin on 14-1-10.
 * 调度器
 */
public class Dispatcher {


    private static Logger logger = Logger.getLogger(Dispatcher.class);

    private Properties properties;
    private String clientId;
    private String clientIp;
    private String clientPort;
    private String clientUsername;
    private String clientPassword;
    private String serverIp;
    private String serverPort;
    private String serverUsername;
    private String serverPassword;
    private String serverMatchKey;
    private String jobName;
    private String dependOnServer;

    public Dispatcher(){
        this.properties = new Properties();
    }

    public Properties getProperties() {
        return properties;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientIp() {
        return clientIp;
    }

    public String getClientPort() {
        return clientPort;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public String getClientPassword() {
        return clientPassword;
    }

    public String getServerIp() {
        return serverIp;
    }

    public String getServerPort() {
        return serverPort;
    }

    public String getServerUsername() {
        return serverUsername;
    }

    public String getServerPassword() {
        return serverPassword;
    }

    public String getServerMatchKey() {
        return serverMatchKey;
    }

    public String getJobName() {
        return jobName;
    }

    public String getDependOnServer() {
        return dependOnServer;
    }

    /**
     * 提供给外部调用启动方法
     **/
    public void start(){



        try{
            StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory(properties);
        }catch (SchedulerException se){
            logger.error("初始化StdSchedulerFactory失败"+se.getMessage());
        }
    }

    /**
     * 初始化properties文件
     */
     void initProperties(){
       String filename = "quartz.properties";
       InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
       try {
           if(is != null) {
               is = new BufferedInputStream(is);
           } else {
               is = new BufferedInputStream(new FileInputStream(filename));
           }
           properties.load(is);
           clientId = properties.getProperty("org.quartz.scheduler.instanceName");
           clientIp = properties.getProperty("client.ip");
           clientPort = properties.getProperty("client.port");
           clientUsername = properties.getProperty("client.username");
           clientPassword = properties.getProperty("client.password");
           jobName = properties.getProperty("jobName");
           serverIp = properties.getProperty("server.ip");
           serverPort = properties.getProperty("server.port");
           serverUsername = properties.getProperty("server.username");
           serverPassword = properties.getProperty("server.password");
           serverMatchKey = properties.getProperty("server.matchKey");
           dependOnServer = properties.getProperty("dependOnServer");
           if("".equals(clientId)){
               throw new RuntimeException("instanceName不能为空");
           }else if("".equals(jobName)){
               throw new RuntimeException("jobName不能为空");
           }else if("".equals(dependOnServer)){
               throw new RuntimeException("dependOnServer不能为空");
           }else if("true".equals(dependOnServer)&&(!clientIp.matches("\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3}")||!serverIp.matches("\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3}"))){
               throw new RuntimeException("ip地址格式不正确");
           }else if("true".equals(dependOnServer)&&(!clientPort.matches("\\d{1,5}"))){
               throw new RuntimeException("端口格式不正确");
           }
       } catch (IOException ioe) {
        logger.error("加载配置文件信息失败"+ioe.getMessage());
        }
        finally {
            if(is != null)
                try {
                    is.close();
                } catch(IOException ignore) {
                    logger.error("输入流关闭失败"+ignore.getMessage());
                }
        }
   }

    /**
     * 创建trigger临时文件
     * @param properties
     * @param jobName
     */
    void createTriggerFile(Properties properties,String jobName){

    }
}
