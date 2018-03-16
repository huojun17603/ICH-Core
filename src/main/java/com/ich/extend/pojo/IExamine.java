package com.ich.extend.pojo;

import java.util.Date;

/***
 * 通用审核/处理
 */
public class IExamine {

    private String id;
    //审核结果，根据系统中不同的来源定义不同
    private Integer handleresult;
    //审核时间
    private Date handletime;
    //审核人
    private String handlername;
    //审核说明
    private String handlermark;
    //来源组，针对同一数据重复审核；由小到大，默认0
    private Integer sourcegroup;
    //对应来源
    private Integer source;
    //对应来源ID
    private String sourceid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getHandleresult() {
        return handleresult;
    }

    public void setHandleresult(Integer handleresult) {
        this.handleresult = handleresult;
    }

    public Date getHandletime() {
        return handletime;
    }

    public void setHandletime(Date handletime) {
        this.handletime = handletime;
    }

    public String getHandlername() {
        return handlername;
    }

    public void setHandlername(String handlername) {
        this.handlername = handlername;
    }

    public String getHandlermark() {
        return handlermark;
    }

    public void setHandlermark(String handlermark) {
        this.handlermark = handlermark;
    }

    public Integer getSourcegroup() {
        return sourcegroup;
    }

    public void setSourcegroup(Integer sourcegroup) {
        this.sourcegroup = sourcegroup;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getSourceid() {
        return sourceid;
    }

    public void setSourceid(String sourceid) {
        this.sourceid = sourceid;
    }
}
