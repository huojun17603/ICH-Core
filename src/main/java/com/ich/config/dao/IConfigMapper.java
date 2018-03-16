package com.ich.config.dao;

import com.ich.config.pojo.IConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IConfigMapper {

    void insert(IConfig config);

    void updateByPrimaryKey(IConfig config);

    void deleteExcess(@Param("keys")String keys);

    IConfig selectByPrimaryKey(String key);

    List<IConfig> selectAllList();

}
