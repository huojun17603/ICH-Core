package com.ich.config.service.impl;

import com.ich.config.dao.IConfigMapper;
import com.ich.config.pojo.IConfig;
import com.ich.config.service.IConfigService;
import com.ich.core.base.ObjectHelper;
import com.ich.core.http.entity.HttpResponse;
import com.ich.core.plug.jedis.JedisClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * 使用Redis管理动态配置项
 * 支持：分布式应用
 */
public class IRedisConfigServiceImpl implements IConfigService {

    protected final Logger logger = Logger.getLogger(IRedisConfigServiceImpl.class);

    @Autowired
    private IConfigMapper iConfigMapper;
    @Autowired
    protected JedisClient jedisClient;
    @Value("${JEDIS_CONFIG}")
    private Integer JEDIS_CONFIG;

    private static String PREFIX = "ICONFIG@";


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
        jedisClient.set(JEDIS_CONFIG,PREFIX + value.getIkey(),value.getIvalue());
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
        jedisClient.set(JEDIS_CONFIG,PREFIX + config.getIkey(),config.getIvalue());
        return new HttpResponse(HttpResponse.HTTP_OK,HttpResponse.HTTP_MSG_OK);
    }

    @Override
    public String getParams(String key) {
        return jedisClient.get(JEDIS_CONFIG,PREFIX + key);
    }

    @Override
    public Boolean containsKey(String key) {
        String value = jedisClient.get(JEDIS_CONFIG,PREFIX + key);
        return ObjectHelper.isNotEmpty(value);
    }

    @Override
    public List<IConfig> findAllList() {
        return iConfigMapper.selectAllList();
    }
}
