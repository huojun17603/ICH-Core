package com.ich.extend.dao;

import com.ich.extend.pojo.ICategory;
import com.ich.extend.pojo.ICategoryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ICategoryMapper {

    int countByExample(ICategoryExample example);

    int deleteByExample(ICategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ICategory record);

    int insertSelective(ICategory record);

    List<ICategory> selectByExample(ICategoryExample example);

    ICategory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ICategory record, @Param("example") ICategoryExample example);

    int updateByExample(@Param("record") ICategory record, @Param("example") ICategoryExample example);

    int updateByPrimaryKeySelective(ICategory record);

    int updateByPrimaryKey(ICategory record);

    public int updateStatus(@Param("id")Long id, @Param("status")Boolean status);

    public List<ICategory> selectListOfSource(@Param("source")Integer source, @Param("status")Boolean status);

    public List<ICategory> selectListOfPid(@Param("pid")Long pid, @Param("source")Integer source, @Param("status")Boolean status);
}
