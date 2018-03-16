package com.ich.extend.pojo;

import java.util.Date;

public class IWitnesses {

    //`id` varchar(32) NOT NULL COMMENT 'ID',
    private String id;
    //`aid` varchar(32) NOT NULL COMMENT '举报人ID',
    private String aid;
    //`aname` varchar(64) NOT NULL COMMENT '举报人账号',
    private String aname;
    //`wid` varchar(32) NOT NULL COMMENT '被举报人ID',
    private String wid;
    //`wname` varchar(64) NOT NULL COMMENT '被举报人账号',
    private String wname;
    //`reason` varchar(255) NOT NULL COMMENT '举报理由；',
    private String reason;
    //`status` int(11) NOT NULL COMMENT '状态；0：未处理；1：已处理',
    private Integer status;
    //`createtime` datetime DEFAULT NULL COMMENT '举报时间',
    private Date createtime;
    //`handletime` datetime DEFAULT NULL COMMENT '处理时间',
    private Date handletime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getWid() {
        return wid;
    }

    public void setWid(String wid) {
        this.wid = wid;
    }

    public String getWname() {
        return wname;
    }

    public void setWname(String wname) {
        this.wname = wname;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getHandletime() {
        return handletime;
    }

    public void setHandletime(Date handletime) {
        this.handletime = handletime;
    }
}
