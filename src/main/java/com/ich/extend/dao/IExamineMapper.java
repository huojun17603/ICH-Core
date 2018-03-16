package com.ich.extend.dao;

import com.ich.extend.pojo.IExamine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IExamineMapper {

    void insert(IExamine examine);

    Integer selectMaxGroup(@Param("source")Integer source,@Param("sourceid")String sourceid);

    List<IExamine> selectListOfGroup(@Param("source")Integer source, @Param("sourceid")String sourceid, @Param("sourcegroup")Integer sourcegroup);

}
