package com.ich.version.pojo;

import java.util.Date;

public class Versionapp {

    //`id` int(11) NOT NULL,
    private Long id;
    //`equipment` varchar(16) NOT NULL COMMENT '设备；自定义',
    private String equipment;
    //`appname` varchar(64) NOT NULL COMMENT '应用名称',
    private String appname;
    //`version` varchar(8) NOT NULL COMMENT '版本号',
    private String version;
    //`force` bit(1) DEFAULT NULL COMMENT '是否强制更新',
    private Boolean force;
    //`filezise` int(11) DEFAULT NULL COMMENT '文件大小（KB）',
    private Integer filezise;
    //`http` varchar(255) DEFAULT NULL COMMENT '下载地址',
    private String http;
    //`remark` varchar(255) DEFAULT NULL COMMENT '更新说明',
    private String remark;
    //`releasetime` datetime DEFAULT NULL COMMENT '发布时间',
    private Date releasetime;
    //`status` int(11) DEFAULT NULL COMMENT '状态；1：待发布；2：当前版本；3：过期版本',
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Boolean getForce() {
        return force;
    }

    public void setForce(Boolean force) {
        this.force = force;
    }

    public Integer getFilezise() {
        return filezise;
    }

    public void setFilezise(Integer filezise) {
        this.filezise = filezise;
    }

    public String getHttp() {
        return http;
    }

    public void setHttp(String http) {
        this.http = http;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getReleasetime() {
        return releasetime;
    }

    public void setReleasetime(Date releasetime) {
        this.releasetime = releasetime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
