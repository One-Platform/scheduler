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

    public Dispatcher(Properties properties) {
        this.properties = new Properties();
        initProperties(properties);
    }

    /**
     * 提供给外部调用启动方法
     **/
    private  void start(){


        try{
            StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory(properties);
        }catch (SchedulerException se){
            logger.error("初始化StdSchedulerFactory失败"+se.getMessage());
        }
    }

    /**
     * 初始化properties文件
     * @param properties
     */
   private void initProperties(Properties properties){
       String filename = "quartz.properties";
       InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
       try {
           if(is != null) {
               is = new BufferedInputStream(is);
           } else {
               is = new BufferedInputStream(new FileInputStream(filename));
           }
           properties.load(is);
           clientId = properties.getProperty("client.id");
           clientIp = properties.getProperty("client.ip");
           clientPort = properties.getProperty("client.Port");
           clientUsername = properties.getProperty("client.username");
           clientPassword = properties.getProperty("client.password");
           jobName = properties.getProperty("job.name");
           serverIp = properties.getProperty("server.ip");
           serverPort = properties.getProperty("server.port");
           serverUsername = properties.getProperty("server.username");
           serverPassword = properties.getProperty("server.password");
           serverMatchKey = properties.getProperty("server.matchKey");
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
}
