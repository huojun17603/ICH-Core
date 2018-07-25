package com.ich.extend.dao;

import com.ich.extend.pojo.IFeedback;
import com.ich.extend.pojo.IFeedbackExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IFeedbackMapper {

    int countByExample(IFeedbackExample example);

    int deleteByExample(IFeedbackExample example);

    int deleteByPrimaryKey(String id);

    int insert(IFeedback record);

    int insertSelective(IFeedback record);

    List<IFeedback> selectByExample(IFeedbackExample example);

    IFeedback selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") IFeedback record, @Param("example") IFeedbackExample example);

    int updateByExample(@Param("record") IFeedback record, @Param("example") IFeedbackExample example);

    int updateByPrimaryKeySelective(IFeedback record);

    int updateByPrimaryKey(IFeedback record);

    public int updateStatus(@Param("id")String id,@Param("status")Integer status);

    public List<IFeedback> selectOfList(String searchkey);

}
