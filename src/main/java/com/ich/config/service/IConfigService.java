package com.ich.config.service;

import com.ich.config.pojo.IConfig;
import com.ich.core.http.entity.HttpResponse;

import java.util.List;

public interface IConfigService {

    /** 初始化配置 */
    public void initConfig(IConfig value);
    /** 配置值 */
    public HttpResponse setParams(String key, String value);
    /** 获取配置项的值 */
    public String getParams(String key);
    /** 验证配置是否存在 */
    public Boolean containsKey(String key);
    /** 获取所有配置 */
    public List<IConfig> findAllList();

}
