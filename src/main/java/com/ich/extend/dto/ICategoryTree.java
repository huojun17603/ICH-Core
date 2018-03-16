package com.ich.extend.dto;

import com.ich.core.http.entity.EasyUITreeNode;
import com.ich.extend.pojo.ICategory;

public class ICategoryTree extends EasyUITreeNode {

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

    public ICategoryTree(ICategory iCategory) {
        this.source = iCategory.getSource();
        this.status = iCategory.getStatus();
        this.onum = iCategory.getOnum();
        this.name = iCategory.getName();
        this.remark = iCategory.getRemark();
        this.attr = iCategory.getAttr();
        this.http = iCategory.getHttp();
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
