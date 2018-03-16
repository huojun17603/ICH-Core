package com.ich.version.service;

import com.ich.core.http.entity.HttpResponse;
import com.ich.core.http.entity.PageView;
import com.ich.version.pojo.Versionapp;

import java.util.List;

public interface VersionappService {

    /**
     * 新增或修改，有效状态：待发布；版本号不允许重复
     * @param versionapp 数据
     * @return 是否完成
     */
    public HttpResponse addOrEdit(Versionapp versionapp);

    /**
     * 更新一个待发布版本至当前版本，发布一个版本
     * @param id 版本ID
     * @return 是否完成
     */
    public HttpResponse editToNewest(Long id);

    /**
     * 删除一个待发布版本
     * @param id 版本ID
     * @return 是否完成
     */
    public HttpResponse deleteVersion(Long id);

    public Versionapp findById(Long id);

    /**
     * 查询最新版本
     * @param equipment 设备
     * @param appname 应用名称
     * @return
     */
    public Versionapp findByNewest(String equipment,String appname);
    /**
     * 查询历史版本
     * @param equipment 设备
     * @param appname 应用名称
     * @return
     */
    public List<Versionapp> findByHistory(String equipment, String appname);
    /**
     * 分页查询
     * @param equipment 设备
     * @param appname 应用名称
     * @return
     */
    public List<Versionapp> queryVersion(PageView view,String equipment,String appname);
}
