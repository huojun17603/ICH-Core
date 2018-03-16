package com.ich.file.service;

import com.ich.core.http.entity.HttpResponse;
import com.ich.file.pojo.FileResource;

import java.util.List;

public interface FileResourceService {
    /**
     * 添加文件信息
     * @param fileResource 文件信息
     * @return 是否完成
     */
    public HttpResponse addFileResource(FileResource fileResource);
    /**
     * 修改文件信息
     * @param fileResource 文件信息
     * @return 是否完成
     */
    public HttpResponse editFileResource(FileResource fileResource);

    /**
     * 批量删除
     * @param ids ID集
     * @return 是否完成
     */
    public HttpResponse deleteFileByIds(String ids);

    /**
     * 按来源删除
     * @param source 来源
     * @param sourceid 来源ID
     * @return 是否完成
     */
    public HttpResponse deleteFileBySource(Integer source, String sourceid);

    /**
     * 按ID查询
     * @param id
     * @return 数据
     */
    public FileResource findById(String id);

    /**
     * 按来源查询
     * @param source 来源
     * @param sourceid 来源ID
     * @return 列表
     */
    public List<FileResource> findBySource(Integer source, String sourceid);
}
