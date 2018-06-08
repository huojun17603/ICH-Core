package com.ich.monitor.pojo;

public class IDataTask {

    //ID
    private Long id;
    //服务名称
    private String servername;
    //服务器IP地址
    private String serverip;
    //服务编号：类名_方法名
    private String servercode;
    //执行时间
    private String handletime;
    //执行ID
    private String handleid;
    //执行状态
    //0:未执行;1:已执行;2:执行失败
    private Integer handlestatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServername() {
        return servername;
    }

    public void setServername(String servername) {
        this.servername = servername;
    }

    public String getServerip() {
        return serverip;
    }

    public void setServerip(String serverip) {
        this.serverip = serverip;
    }

    public String getServercode() {
        return servercode;
    }

    public void setServercode(String servercode) {
        this.servercode = servercode;
    }

    public String getHandletime() {
        return handletime;
    }

    public void setHandletime(String handletime) {
        this.handletime = handletime;
    }

    public String getHandleid() {
        return handleid;
    }

    public void setHandleid(String handleid) {
        this.handleid = handleid;
    }

    public Integer getHandlestatus() {
        return handlestatus;
    }

    public void setHandlestatus(Integer handlestatus) {
        this.handlestatus = handlestatus;
    }
}
