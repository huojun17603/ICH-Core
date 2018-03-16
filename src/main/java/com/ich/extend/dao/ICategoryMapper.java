package com.ich.extend.dao;

import com.ich.extend.pojo.ICategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ICategoryMapper {

    public int insert(ICategory category);

    public int update(ICategory category);

    public int updateStatus(@Param("id")Long id, @Param("status")Boolean status);

    public int delete(@Param("id")Long id);

    public ICategory selectById(@Param("id")Long id);

    public List<ICategory> selectListOfSource(@Param("source")Integer source, @Param("status")Boolean status);

    public List<ICategory> selectListOfPid(@Param("pid")Long pid, @Param("source")Integer source, @Param("status")Boolean status);
}
