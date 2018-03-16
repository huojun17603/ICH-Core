package com.ich.extend.dao;

import com.ich.extend.pojo.IFeedback;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IFeedbackMapper {

    public int insert(IFeedback feedback);

    public int updateStatus(@Param("id")String id,@Param("status")Integer status);

    public int delete(@Param("id")String id);

    public List<IFeedback> selectOfList(String searchkey);

}
