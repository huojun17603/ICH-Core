package com.ich.monitor.service.impl;

import com.ich.core.http.entity.HttpResponse;
import com.ich.monitor.dao.IDataMonitorMapper;
import com.ich.monitor.pojo.IDataMonitor;
import com.ich.monitor.service.IDataMonitorNoticeService;
import com.ich.monitor.service.IDataMonitorService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class IDataMonitorServiceImpl implements IDataMonitorService {

    @Autowired
    IDataMonitorNoticeService iDataMonitorNoticeService;

    @Autowired
    IDataMonitorMapper iDataMonitorMapper;

    @Override
    public void execute() {
        Date day = new Date();
        List<IDataMonitor> list = iDataMonitorMapper.selectAll();
        for(IDataMonitor iDataMonitor : list){
            String code = iDataMonitor.getCode();
            Long cz = day.getTime() - iDataMonitor.getLatesttime().getTime();//差值
            if(cz >= iDataMonitor.getWarnstamp()){
                if(iDataMonitor.getIsnotice()==0){
                    iDataMonitorMapper.updateIsnotice(code,1);
                    iDataMonitorNoticeService.executeNotice(code);
                }
            }else{
                if(iDataMonitor.getIsnotice()==1){
                    iDataMonitorMapper.updateIsnotice(code,0);
                }
            }
        }
        try {
            editLatestTime("iDataMonitorServiceImpl_execute");
        } catch (Exception e) {
            // 用try catch块包住,避免监控异常打断任务执行事务
            e.printStackTrace();
        }
    }

    @Override
    public HttpResponse editLatestTime(String code) {
        int result = iDataMonitorMapper.updateLatestTime(code);
        return result==0?new HttpResponse(HttpResponse.HTTP_ERROR,HttpResponse.HTTP_MSG_ERROR):new HttpResponse(HttpResponse.HTTP_OK,HttpResponse.HTTP_MSG_OK);
    }

    @Override
    public IDataMonitor findByCode(String code) {
        return this.iDataMonitorMapper.selectById(code);
    }
}
