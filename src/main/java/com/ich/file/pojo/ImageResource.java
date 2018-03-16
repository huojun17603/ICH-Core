package com.ich.file.pojo;

import java.util.Date;

/**
 * 图片统一管理表
 * 保存的图片必须是最终显示出来的图片，加工过程中的零时图片都不需要
 * 所有其他表会涉及到图片的，都应该保存在这里
 * 这样通过此表可以有效的处理项目中的无效图片
 */
public class ImageResource {

    //ID
    private String id;
    //图片名称：随机生成的
    private String randomname;
    //系统相对路径
    private String path;
    //HTTP路径
    private String http;
    //创建时间
    private Date createtime;
    //序列
    private Integer onum;
    //图片高度
    private Integer height;
    //图片宽度
    private Integer width;
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

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getRandomname() {
        return randomname;
    }

    public void setRandomname(String randomname) {
        this.randomname = randomname;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getHttp() {
        return http;
    }

    public void setHttp(String http) {
        this.http = http;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getOnum() {
        return onum;
    }

    public void setOnum(Integer onum) {
        this.onum = onum;
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
