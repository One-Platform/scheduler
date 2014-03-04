package com.sinosoft.one.schedule.agent.core;

import com.sinosoft.one.schedule.agent.exception.ScheduleAgentException;
import org.apache.log4j.Logger;
import org.quartz.*;
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
    public void  start(){
        try{
            StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory(properties);
            Scheduler scheduler = stdSchedulerFactory.getScheduler();
            //创建jobDetail
            List<JobDetail> jobDetails = null;
            try {
                jobDetails = createJobDetail();
//                if("false".equals(dependOnServer)){
//                    //创建job的trigger
//                    Map<JobDetail,List<Trigger>> jobDetailListMap = null;
//                    try {
//                        if(null!=jobDetails){
//                            jobDetailListMap = createJobTrigger(jobDetails);
//                            Iterator<Map.Entry<JobDetail,List<Trigger>>> iterator = jobDetailListMap.entrySet().iterator();
//                            while (iterator.hasNext()){
//                                Map.Entry<JobDetail,List<Trigger>> entry = iterator.next();
//                                JobDetail jobDetail = entry.getKey();
//                                scheduler.addJob(jobDetail,false);
//                                List<Trigger> triggers = entry.getValue();
//                                for(int i=0;i<triggers.size();i++){
//                                    scheduler.scheduleJob(triggers.get(i));
//                                }
//                            }
//                            scheduler.start();
//                        }else{
//
//                        }
//                    } catch (ScheduleAgentException e) {
//                        logger.error("构建trigger失败"+e.getMessage());
//                    }
//                }else{
//                    //判断网络连接 （未做）
//
//                    for(int i=0;i<jobDetails.size();i++){
//                        scheduler.addJob(jobDetails.get(i),false);
//                        scheduler.start();
//                    }
//                }
            } catch (ScheduleAgentException e) {
                logger.error("构建jobDetail失败"+e.getMessage());
            }
        }catch (SchedulerException se){
            logger.error("初始化StdSchedulerFactory失败"+se.getMessage());
        }
    }

    /**
     * 初始化properties文件
     */
     void initProperties() {
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
           //properties中属性格式验证
           if("".equals(dependOnServer)){
               throw new ScheduleAgentException("dependOnServer不能为空");
           }else if("".equals(clientId)){
               throw new ScheduleAgentException("instanceName不能为空");
           }else if("".equals(jobName)){
               throw new ScheduleAgentException("jobName不能为空");
           }else if("".equals(dependOnServer)){
               throw new ScheduleAgentException("dependOnServer不能为空");
           }else if("true".equals(dependOnServer)&&(!clientIp.matches("\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3}")||!serverIp.matches("\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3}"))){
               throw new ScheduleAgentException("ip地址格式不正确");
           }else if("true".equals(dependOnServer)&&(!clientPort.matches("\\d{1,5}"))){
               throw new ScheduleAgentException("端口格式不正确");
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
     * @param job
     * @param trigger
     * @throws ScheduleAgentException
     */
    void createTriggerFile(String job,String[] trigger){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<trigger.length;i++){
            sb.append(trigger+",");
        }
        String result = sb.substring(0,sb.length()-2);
        if("".equals(trigger)){
            throw new ScheduleAgentException("trigger配置不正确");
        }
        File file = new File(Thread.currentThread().getContextClassLoader().getResource("").getPath()+job+".trigger");
        try {
            file.createNewFile();
        } catch (IOException e) {
            logger.error("创建trigger文件失败"+e.getMessage());
        }
        OutputStream os = null;
        try {
            os = new FileOutputStream(file,true);
            os.write(result.getBytes());
            os.flush();
        } catch (FileNotFoundException e) {
            logger.error("trigger文件写入失败"+e.getMessage());
        } catch (IOException e) {
            logger.error("写文件失败"+e.getMessage());
        }finally {
            try {
                os.close();
            } catch (IOException e) {
                logger.error("文件流关闭失败"+e.getMessage());
            }
        }
    }

    /**
     * 创建jobDetail
     */
    List<JobDetail> createJobDetail() throws ScheduleAgentException {
        String[] jobNameArray = jobName.split(",");
        List<JobDetail> jobDetails = new ArrayList<JobDetail>();
        for(int i=0;i<jobNameArray.length;i++){
            String job = jobNameArray[i];
            String jobClass = properties.getProperty(job+".jobClass");
            if("".equals(jobClass)){
                throw new ScheduleAgentException("jobClass配置不正确");
            }
            try {
               JobTask s = (JobTask)Class.forName(jobClass).newInstance();
               Task t = new Task(s);
               JobDetail jobDetail = JobBuilder.newJob(t.getClass()).withIdentity(job, job).storeDurably(true).build();
               jobDetails.add(jobDetail);
            } catch (ClassNotFoundException e) {
                logger.error("jobClass配置不正确"+e.getMessage());
            } catch (InstantiationException e) {
                logger.error("jobClass创建失败" + e.getMessage());
            } catch (IllegalAccessException e) {
                logger.error("jobClass创建失败" + e.getMessage());
            }
        }
        return jobDetails;
    }

    /**
     * 根据job创建trigger
     * @param jobDetails
     * @return
     */
    Map<JobDetail,List<Trigger>> createJobTrigger(List<JobDetail> jobDetails) {
        Map<JobDetail,List<Trigger>> jobTriggerMap = new HashMap<JobDetail, List<Trigger>>();
        for(int i=0;i<jobDetails.size();i++){
            List<Trigger> triggers = new ArrayList<Trigger>();
            jobName = jobDetails.get(i).getKey().getName();
            String triggerStr = properties.getProperty(jobName+".trigger");
            String[] triggerArray = triggerStr.split(",");
            createTriggerFile(jobName,triggerArray);
            for(int j=0;j<triggerArray.length;j++){
                Trigger trigger =  TriggerBuilder.newTrigger().withIdentity(jobName + ".trigger", jobName + ".trigger").forJob(jobDetails.get(i)).withSchedule(CronScheduleBuilder.cronSchedule(triggerArray[i])).build();
                triggers.add(trigger);
            }
            jobTriggerMap.put(jobDetails.get(i),triggers);
        }
        return jobTriggerMap;
    }

}
