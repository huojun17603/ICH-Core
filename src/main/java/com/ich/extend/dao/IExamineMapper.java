package com.ich.extend.dao;

import com.ich.extend.pojo.IExamine;
import com.ich.extend.pojo.IExamineExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IExamineMapper {
    
    int countByExample(IExamineExample example);

    int deleteByExample(IExamineExample example);

    int deleteByPrimaryKey(String id);

    int insert(IExamine record);

    int insertSelective(IExamine record);

    List<IExamine> selectByExample(IExamineExample example);

    IExamine selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") IExamine record, @Param("example") IExamineExample example);

    int updateByExample(@Param("record") IExamine record, @Param("example") IExamineExample example);

    int updateByPrimaryKeySelective(IExamine record);

    int updateByPrimaryKey(IExamine record);
    
    Integer selectMaxGroup(@Param("source")Integer source,@Param("sourceid")String sourceid);

    List<IExamine> selectListOfGroup(@Param("source")Integer source, @Param("sourceid")String sourceid, @Param("sourcegroup")Integer sourcegroup);

}
