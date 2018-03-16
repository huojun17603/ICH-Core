package com.ich.extend.pojo;

/**
 * 通用类目
 * 可用于树形与非树形结构
 */
public class ICategory {
    /** ID */
    private Long id;
    /** 父ID */
    private Long pid;
    /** 类型 */
    private Integer source;
    /** 状态*/
    private Boolean status;
    /** 排序值*/
    private Integer onum;
    /** 类目名称 */
    private String name;
    /** 类目说明 */
    private String remark;
    /** 扩展属性：用户自定义，多属性用逗号分隔*/
    private String attr;
    /** HTTP链接：可以是网页、图片*/
    private String http;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getOnum() {
        return onum;
    }

    public void setOnum(Integer onum) {
        this.onum = onum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public String getHttp() {
        return http;
    }

    public void setHttp(String http) {
        this.http = http;
    }
}
