package com.ich.monitor.dao;

import com.ich.monitor.pojo.IDataTask;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IDataTaskMapper {

    void insert(IDataTask record);

    void update(IDataTask record);

    void updateStatus(@Param("id") Long id,@Param("handlestatus") Integer handlestatus);

    List<IDataTask> selectNINGByNameAndCode(@Param("servername")String servername, @Param("servercode")String servercode);

    List<IDataTask> selectINGByCode(@Param("servercode")String servercode);

    List<IDataTask> selectByCodeAndHandleid(@Param("servercode")String servercode, @Param("handleid")String handleid);

}
