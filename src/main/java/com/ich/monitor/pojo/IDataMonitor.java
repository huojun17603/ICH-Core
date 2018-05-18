package com.ich.monitor.pojo;

import java.util.Date;

/**
 * 数据通过库直接写入
 */
public class IDataMonitor {

    //主键：类名_方法名
    private String code;
    //最新处理时间
    private Date latesttime;
    //警告时间戳，当最新处理时间与当前时间的差值大于此值，发出警告（单位毫秒）
    private Long warnstamp;
    //是否已发送通知（防止重复发送）
    private Integer isnotice;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getLatesttime() {
        return latesttime;
    }

    public void setLatesttime(Date latesttime) {
        this.latesttime = latesttime;
    }

    public Long getWarnstamp() {
        return warnstamp;
    }

    public void setWarnstamp(Long warnstamp) {
        this.warnstamp = warnstamp;
    }

    public Integer getIsnotice() {
        return isnotice;
    }

    public void setIsnotice(Integer isnotice) {
        this.isnotice = isnotice;
    }
}
