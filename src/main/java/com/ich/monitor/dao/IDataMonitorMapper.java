package com.ich.monitor.dao;

import com.ich.monitor.pojo.IDataMonitor;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IDataMonitorMapper {

    List<IDataMonitor> selectAll();

    IDataMonitor selectById(@Param("code")String code);

    void updateIsnotice(@Param("code")String code, @Param("isnotice") Integer isnotice);

    int updateLatestTime(@Param("code")String code);

}
