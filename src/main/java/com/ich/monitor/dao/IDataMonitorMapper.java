package com.ich.monitor.dao;

import com.ich.monitor.pojo.IDataMonitor;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IDataMonitorMapper {

    void insertInit(IDataMonitor monitor);

    void updateInit(IDataMonitor monitor);

    int updateLatestTime(@Param("servername")String servername, @Param("servercode")String servercode);

    void updateServerstatus(IDataMonitor iDataMonitor);

    IDataMonitor selectByPrimarykeys(@Param("servername")String servername, @Param("servercode")String servercode);

    List<IDataMonitor> selectAll();

    List<IDataMonitor> selectEffectiveByServercode(@Param("servercode")String servercode);

    List<IDataMonitor> selectDisEffectiveByServercode(@Param("servercode")String servercode);
}
