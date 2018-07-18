package com.ich.monitor.service;

import com.ich.core.http.entity.HttpResponse;
import com.ich.monitor.pojo.IDataMonitor;
import com.ich.monitor.pojo.IDataTask;

import java.util.List;

public interface IDataMonitorService {

    /**
     * 执行监控任务
     */
    public void execute();

    /**
     * 更新任务执行时间
     * @param servercode
     * @return
     */
    public HttpResponse editLatestTime(String servercode);

    /**
     * 初始化classpath:task/task.xml文件中的任务列表
     * @param monitor
     */
    public void init(IDataMonitor monitor);

    /**
     * 发布任务
     * @param servercode 服务编码
     * @param handleids 任务ID集
     */
    public void publisherTasks(String servercode,List<String> handleids);
    /**
     * 领取任务
     * @param servercode 服务编码
     */
    public List<IDataTask> obtainTasks(String servercode);
    /**
     * 正在执行
     * @param taskid
     */
    public void startTask(Long taskid);
    /**
     * 任务完成
     * @param taskid 任务ID
     */
    public void successTask(Long taskid);
    /**
     * 任务失败
     * @param taskid 任务ID
     */
    public void failureTask(Long taskid);
}
