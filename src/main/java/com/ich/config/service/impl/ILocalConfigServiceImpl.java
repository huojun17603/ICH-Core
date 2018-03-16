package com.ich.config.service.impl;

import com.ich.config.dao.IConfigMapper;
import com.ich.config.pojo.IConfig;
import com.ich.config.service.IConfigService;
import com.ich.core.base.ObjectHelper;
import com.ich.core.http.entity.HttpResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 本地内存管理动态配置信息
 * 使用场景：小型、独立项目，不支持分布式
 */
public class ILocalConfigServiceImpl implements IConfigService {

    protected final Logger logger = Logger.getLogger(ILocalConfigServiceImpl.class);

    @Autowired
    private IConfigMapper iConfigMapper;

    @Override
    public void initConfig(IConfig value) {
        IConfig config = iConfigMapper.selectByPrimaryKey(value.getIkey());
        if(ObjectHelper.isEmpty(config)){//如果为空，则添加
            if(!IConfig.vry(value.getDriver(),value.getIvalue())){
                logger.error("【全局动态配置】：新增配置失败（key:"+value.getIkey()+",value:"+value.getIvalue()+"），无法满足正则验证！");
                return;
            }
            iConfigMapper.insert(value);
            logger.info("【全局动态配置】：新增配置（key:"+value.getIkey()+",value:"+value.getIvalue()+"）");
        }else{
            value.setIvalue(config.getIvalue());
            iConfigMapper.updateByPrimaryKey(value);
            logger.info("【全局动态配置】：当前配置（key:"+value.getIkey()+",value:"+value.getIvalue()+"）");
        }
        IConfig.setParams(value.getIkey(),value);
    }

    @Override
    public HttpResponse setParams(String key, String value) {
        IConfig config = iConfigMapper.selectByPrimaryKey(key);
        if(ObjectHelper.isEmpty(config)) return new HttpResponse(HttpResponse.HTTP_ERROR,"无效的配置信息！");
        if(!IConfig.vry(config.getDriver(),value)){
            logger.error("【全局动态配置】：新增配置失败，无法满足正则验证！");
            return new HttpResponse(HttpResponse.HTTP_ERROR,"新增配置失败，无法满足正则验证！");
        }
        config.setIvalue(value);
        iConfigMapper.updateByPrimaryKey(config);
        logger.info("【全局动态配置】：修改配置（key:"+key+",value:"+value+"）");
        IConfig.setParams(key,config);
        return new HttpResponse(HttpResponse.HTTP_OK,HttpResponse.HTTP_MSG_OK);
    }

    @Override
    public String getParams(String key) {
        return IConfig.getParams(key);
    }

    @Override
    public Boolean containsKey(String key) {
        return IConfig.containsKey(key);
    }

    @Override
    public List<IConfig> findAllList() {
        return iConfigMapper.selectAllList();
    }
}
