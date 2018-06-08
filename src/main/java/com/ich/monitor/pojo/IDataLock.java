package com.ich.monitor.pojo;

import java.util.Date;

public class IDataLock {

    /** 主键：锁名称 */
    private String lock;
    /** 创建时间 */
    private Date createtime;

    public String getLock() {
        return lock;
    }

    public void setLock(String lock) {
        this.lock = lock;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
