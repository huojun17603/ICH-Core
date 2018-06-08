package com.ich.monitor.pojo;

import java.util.Date;

/**
 * 任务监控实例
 * 初始化数据来源于配置文件
 * 谁？启动了什么服务？是否正常运行？
 */
public class IDataMonitor {

    //主键：服务器名称
    private String servername;
    //主键：服务编号：类名_方法名
    private String servercode;
    //服务器IP地址
    private String serverip;
    //服务器备注
    private String serverremark;

    //是否使用任务池，应该是外部代码决定的

    //是否作为任务发布者，注册的时候保证其唯一性，就可以保证任务不重复
    private Boolean publisher;
    //是否重复发布任务，
    //如果是则在存在未完成任务的情况下依然发布（可用于任务本身执行不会重复的情况，例如：新增/删除记录），这种方案一定要保证任务可以被处理完！
    //如果否则任务没有处理完则不会发布任务，保证任务量不会无限累计（可用于任务本身执行会重复的情况，例如：定时更新记录）
    private Boolean repeat;


    //最新处理时间
    private Date latesttime;



    //警告时间戳，当最新处理时间与当前时间的差值大于此值，发出警告（单位毫秒）
    private Long warnstamp;
    //服务状态，1：进行中；2：异常
    private Integer serverstatus;
    //是否通知，暂时不要
    private Boolean inform;
    //通知参数集

    public String getServername() {
        return servername;
    }

    public void setServername(String servername) {
        this.servername = servername;
    }

    public String getServercode() {
        return servercode;
    }

    public void setServercode(String servercode) {
        this.servercode = servercode;
    }

    public String getServerip() {
        return serverip;
    }

    public void setServerip(String serverip) {
        this.serverip = serverip;
    }

    public String getServerremark() {
        return serverremark;
    }

    public void setServerremark(String serverremark) {
        this.serverremark = serverremark;
    }

    public Boolean getPublisher() {
        return publisher;
    }

    public void setPublisher(Boolean publisher) {
        this.publisher = publisher;
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

    public Integer getServerstatus() {
        return serverstatus;
    }

    public void setServerstatus(Integer serverstatus) {
        this.serverstatus = serverstatus;
    }

    public Boolean getRepeat() {
        return repeat;
    }

    public void setRepeat(Boolean repeat) {
        this.repeat = repeat;
    }

    public Boolean getInform() {
        return inform;
    }

    public void setInform(Boolean inform) {
        this.inform = inform;
    }
}
