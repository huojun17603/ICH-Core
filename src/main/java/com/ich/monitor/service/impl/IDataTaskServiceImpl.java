package com.ich.monitor.service.impl;

import com.ich.core.base.ObjectHelper;
import com.ich.monitor.dao.IDataLockMapper;
import com.ich.monitor.dao.IDataMonitorMapper;
import com.ich.monitor.dao.IDataTaskMapper;
import com.ich.monitor.pojo.IDataMonitor;
import com.ich.monitor.pojo.IDataTask;
import com.ich.monitor.service.IDataTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
//（正常事务）
//获取所有任务列表（继承）
//抢发布权（无事务）
//发布与转移任务（新事务）
//获取我的任务（无事务）
//for(){
//    （新事务）
//    try{
//        开始此任务（无事务）
//        执行业务（继承）
//        完成此任务（无事务）
//    }ca(){
//        失败此任务（无事务）
//    }
//}
@Service
public class IDataTaskServiceImpl implements IDataTaskService {

    @Value("${SERVER_NAME}")
    private String SERVER_NAME;

    @Autowired
    IDataTaskMapper iDataTaskMapper;
    @Autowired
    IDataMonitorMapper iDataMonitorMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void publisherTask(String servercode, List<String> handleids) {
        publisher(servercode,handleids);//发布任务
        transfer(servercode);//转移任务
    }


    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<IDataTask> obtainTasks(String servercode) {
        return iDataTaskMapper.selectNINGByNameAndCode(SERVER_NAME,servercode);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void completeTask(Long taskid, Integer status) {
        iDataTaskMapper.updateStatus(taskid,status);
    }


    private void publisher(String servercode, List<String> handleids) {
        //重复-1：数据是否都处理完成，2：是否允许同ID重复

        List<IDataMonitor> iDataMonitors = iDataMonitorMapper.selectEffectiveByServercode(servercode);//获取执行此任务的全部有效实例
        if(ObjectHelper.isEmpty(iDataMonitors)) return;//如果没有有效的则不发布，下线必须对服务器处理
        Boolean repeat = iDataMonitors.get(0).getCover();//获取任务的发布性质
        Boolean repeatid = iDataMonitors.get(0).getRepeatid();
        int monitorSum = iDataMonitors.size();
        int monitorNum = 0;
        if(!repeat){ //不允许叠加发布
            List<IDataTask> list = iDataTaskMapper.selectINGByCode(servercode);//获取进行中的任务
            if(ObjectHelper.isNotEmpty(list)) return ;//如果存在任务则不再发布
        }

        for(int i=0;i<handleids.size();i++){//为每个实例分配任务
            String handleid = handleids.get(i);
            if(!repeatid){
                //不允许发布重复的业务ID任务
                List<IDataTask> list = iDataTaskMapper.selectByCodeAndHandleid(servercode,handleid);
                if(ObjectHelper.isNotEmpty(list)) continue;
            }
            //分配任务
            monitorNum = i%monitorSum;
            IDataMonitor monitor = iDataMonitors.get(monitorNum);
            //封装保存任务
            IDataTask record = new IDataTask();
            record.setServername(monitor.getServername());
            record.setServercode(monitor.getServercode());
            record.setServerip(monitor.getServerip());
            record.setHandleid(handleid);
            record.setHandlestatus(0);
            iDataTaskMapper.insert(record);
        }
    }

    private void transfer(String servercode) {//有待改进
        //获取执行此任务的全部无效实例，并且实例有进行中的任务
        Date day = new Date();
        List<IDataMonitor> iDataMonitors = iDataMonitorMapper.selectDisEffectiveByServercode(servercode);
        if(ObjectHelper.isEmpty(iDataMonitors)) return ;
        List<IDataMonitor> effective_iDataMonitors = iDataMonitorMapper.selectEffectiveByServercode(servercode);//获取执行此任务的全部有效实例
        for(IDataMonitor iDataMonitor : iDataMonitors){
            Long cz = day.getTime() - iDataMonitor.getLatesttime().getTime();//差值
            if (cz >= 5*iDataMonitor.getWarnstamp()) {
                List<IDataTask> tasks = iDataTaskMapper.selectNINGByNameAndCode(iDataMonitor.getServername(),servercode);
                if(ObjectHelper.isEmpty(tasks)) continue;
                int monitorSum = effective_iDataMonitors.size();
                int monitorNum = 0;
                for(int i=0;i<tasks.size();i++){//为每个实例分配任务
                    monitorNum = i%monitorSum;
                    IDataMonitor monitor = effective_iDataMonitors.get(monitorNum);
                    IDataTask record = tasks.get(i);
                    record.setServername(monitor.getServername());
                    record.setServercode(monitor.getServercode());
                    record.setServerip(monitor.getServerip());
                    iDataTaskMapper.update(record);
                }
            }
        }
    }

}
