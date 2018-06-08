package com.ich.monitor.service;

import com.ich.monitor.pojo.IDataLock;

public interface IDataLockService {

    /**
     * 添加锁，无事务
     * @param servercode 锁名称
     * @return 是否成功
     */
    boolean enableLock(String servercode);

    /**
     * 解除锁，无事务
     * @param servercode 锁名称
     * @return 是否成功
     */
    boolean disableLock(String servercode);

    /**
     * 查询锁，无事务
     * @param servercode 锁名称
     * @return 对象
     */
    IDataLock findLock(String servercode);



}
