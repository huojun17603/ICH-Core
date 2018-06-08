package com.ich.monitor.dao;

import com.ich.monitor.pojo.IDataLock;
import org.apache.ibatis.annotations.Param;

public interface IDataLockMapper {

    void insert(@Param("servercode") String servercode);

    void delete(@Param("servercode") String servercode);

    IDataLock selectById(@Param("servercode") String servercode);

}
