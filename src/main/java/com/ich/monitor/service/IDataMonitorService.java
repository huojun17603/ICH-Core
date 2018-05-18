package com.ich.monitor.service;

import com.ich.core.http.entity.HttpResponse;
import com.ich.monitor.pojo.IDataMonitor;

public interface IDataMonitorService {

    /**
     * 任务执行
     */
    public void execute();

    public HttpResponse editLatestTime(String code);

    public IDataMonitor findByCode(String code);

}
