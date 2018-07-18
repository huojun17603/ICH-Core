package com.ich.monitor.service.impl;

import com.ich.core.base.ObjectHelper;
import com.ich.core.http.entity.HttpResponse;
import com.ich.monitor.dao.IDataLockMapper;
import com.ich.monitor.dao.IDataMonitorMapper;
import com.ich.monitor.pojo.IDataMonitor;
import com.ich.monitor.pojo.IDataTask;
import com.ich.monitor.processor.IDataMonitorProcessor;
import com.ich.monitor.service.IDataLockService;
import com.ich.monitor.service.IDataMonitorService;
import com.ich.monitor.service.IDataTaskService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 问题：如果其2个服务而使用相同的服务名称则会导致错误
 */
@Service
public class IDataMonitorServiceImpl implements IDataMonitorService {

    protected final Logger logger = Logger.getLogger(IDataMonitorServiceImpl.class);

    @Value("${SERVER_NAME}")
    private String SERVER_NAME;
    @Value("${SERVER_IP}")
    private String SERVER_IP;
    @Value("${SERVER_DOMAIN}")
    private String SERVER_DOMAIN;
    @Value("${SERVER_REMARK}")
    private String SERVER_REMARK;

    @Autowired
    IDataLockService iDataLockService;
    @Autowired
    IDataTaskService iDataTaskService;

    @Autowired
    IDataLockMapper iDataLockMapper;
    @Autowired
    IDataMonitorMapper iDataMonitorMapper;


    private boolean isone = true;

    @Override
    public void init(IDataMonitor monitor) {
        //初始化基本配置
        monitor.setServername(SERVER_NAME);
        monitor.setServerremark(SERVER_REMARK);
        monitor.setServerip(SERVER_IP);
        monitor.setServerstatus(1);
        monitor.setLatesttime(new Date());//重置最后更新时间
        IDataMonitor entity = this.iDataMonitorMapper.selectByPrimarykeys(SERVER_NAME,monitor.getServercode());
        if(ObjectHelper.isEmpty(entity)){
            iDataMonitorMapper.insertInit(monitor);
        }else {
            iDataMonitorMapper.updateInit(monitor);
        }
    }

    @Override
    public void publisherTasks(String servercode,List<String> handleids) {
        //添加分布式锁（通过主键唯一性保证添加的原子性）
        boolean flag = iDataLockService.enableLock(servercode);//事务配置（必须以非事务方式运行）：PROPAGATION_NOT_SUPPORTED
        if(flag){
            try {
                //如果成功则发布任务
                logger.debug("成功获取任务发布者！开始发布任务！");
                iDataTaskService.publisherTask(servercode,handleids);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                //有任何问题都必须解开锁
                iDataLockService.disableLock(servercode);//事务配置（必须以非事务方式运行）
            }
        }else{
            //如果失败则等待发布任务完成
            boolean isLock = true;//查询当前锁是否关闭
            try {
                while (isLock){
                    logger.debug("等待任务发布完成！");
                    Thread.sleep(100);//等待时间
                    isLock = ObjectHelper.isEmpty(iDataLockService.findLock(servercode));//查询当前锁是否关闭
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<IDataTask> obtainTasks(String servercode){
        return iDataTaskService.obtainTasks(servercode);
    }

    //任务执行者在分配到任务后因为未知原因无法按时完成时，如何保证这些任务会被完成

    @Override
    public void startTask(Long taskid){
        iDataTaskService.completeTask(taskid,1);
    }

    @Override
    public void successTask(Long taskid){
        iDataTaskService.completeTask(taskid,2);
    }

    @Override
    public void failureTask(Long taskid){
        iDataTaskService.completeTask(taskid,3);
    }

    //更新任务执行时间：应在任务开始时执行，不参与业务逻辑中导致的问题
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)//无事务保证及时性
    public HttpResponse editLatestTime(String servercode) {
        if(ObjectHelper.isEmpty(servercode))
            return new HttpResponse(HttpResponse.HTTP_ERROR,HttpResponse.HTTP_MSG_ERROR);
        int result = iDataMonitorMapper.updateLatestTime(SERVER_NAME,servercode);
        return result==0?new HttpResponse(HttpResponse.HTTP_ERROR,HttpResponse.HTTP_MSG_ERROR):new HttpResponse(HttpResponse.HTTP_OK,HttpResponse.HTTP_MSG_OK);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)//无事务保证及时性
    public void execute() {
        //所有服务都应运行此方法
        //检查服务是否正常运行
        //如果真的需要分布式方案来解决问题，那么实时监管服务器就是必然要人看着的了，再加上容错的机制，就不要内置的通知行为了！
        editLatestTime("IDataMonitorServiceImpl_execute");
        Date day = new Date();
        List<IDataMonitor> list = iDataMonitorMapper.selectAll();
        for (IDataMonitor iDataMonitor : list) {
            Long cz = day.getTime() - iDataMonitor.getLatesttime().getTime();//差值
            if (cz >= iDataMonitor.getWarnstamp()) {
                if (iDataMonitor.getServerstatus() == 1) {
                    iDataMonitor.setServerstatus(2);
                    iDataMonitorMapper.updateServerstatus(iDataMonitor);
                }
            } else {//当小于差值时，更新为进行中状态；注意：差值的设置
                if (iDataMonitor.getServerstatus() == 2) {
                    iDataMonitor.setServerstatus(1);
                    iDataMonitorMapper.updateServerstatus(iDataMonitor);
                }
            }
        }
    }




}
