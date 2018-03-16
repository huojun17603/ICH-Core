package com.ich.extend.pojo;

import java.util.Date;

public class IFeedback {
    //ID
    private String id;
    //反馈人，可以是名字或其他
    private String username;
    //反馈人联系方式
    private String contact;
    //反馈类容
    private String context;
    //创建时间
    private Date createtime;
    //处理状态；可用，默认0
    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
