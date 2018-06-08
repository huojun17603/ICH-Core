package com.ich.monitor.service;

import com.ich.monitor.pojo.IDataTask;

import java.util.List;

public interface IDataTaskService {

    void publisherTask(String servercode, List<String> handleids);

    List<IDataTask> obtainTasks(String servercode);

    void completeTask(String taskid, int i);
}
