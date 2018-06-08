package com.ich.monitor.service.impl;

import com.ich.monitor.pojo.IDataTask;
import com.ich.monitor.service.IDataTaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IDataTaskServiceImpl implements IDataTaskService {

    @Override
    @Transactional()
    public void publisherTask(String servercode, List<String> handleids) {
//        if(entity.getRepeat()){
//            //允许重复发布
//            //发布任务；事务配置（以一个新的事务运行）：PROPAGATION_REQUIRES_NEW
//            publisher();
//        }else{
//            //不允许重复发布
//            //检查重复性
//            List<IDataTask> records = readInPool(servercode,);
//            if(ObjectHelper.isEmpty(records)){
//                //发布任务
//                publisher();
//            }
//        }
    }

    @Override
    public List<IDataTask> obtainTasks(String servercode) {
//        return iDataMonitorRecordMapper.selectEffectiveByNameAndCode(servername,servercode);
        return null;
    }

    @Override
    public void completeTask(String taskid, int i) {
//        iDataMonitorRecordMapper.update(record);
    }


    private void publisher() {
//        //如果原来的任务没有完成，再次执行此方法？
//        List<IDataMonitor> iDataMonitors = iDataMonitorMapper.selectEffectiveByServercode(servercode);//获取执行此任务的全部有效实例
//        int monitorSum = iDataMonitors.size();
//        int monitorNum = 0;
//        //为每个实例分配任务
//        for(int i=0;i<handleids.size();i++){
//            //分配任务
//            monitorNum = i%monitorSum;
//            IDataMonitor monitor = iDataMonitors.get(monitorNum);
//            String handleid = handleids.get(i);
//            //封装保存任务
//            IDataTask record = new IDataTask();
//            iDataMonitorRecordMapper.insert(record);
//        }
    }

}
