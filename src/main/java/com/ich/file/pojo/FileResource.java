package com.ich.file.pojo;

import java.util.Date;

public class FileResource {

    /** 主键 */
    private String id;
    /** 附件原名 */
    private String oldname;
    /** 文件名称：随机生成的 */
    private String randomname;
    /** 文件路径：相对于服务器路径的地址 */
    private String path;
    /** 文件后缀 */
    private String suffix;
    /** 文件大小 */
    private Long size;
    /** 文件上传时间 */
    private Date createtime;
    /** 文件上传IP地址 */
    private String uploadip;
    /** 文件HASH值 现在用不到，以后可以用于文件的对比*/
    private String hash;
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

    public String getOldname() {
        return oldname;
    }

    public void setOldname(String oldname) {
        this.oldname = oldname;
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

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getUploadip() {
        return uploadip;
    }

    public void setUploadip(String uploadip) {
        this.uploadip = uploadip;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
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
