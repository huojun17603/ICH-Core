package com.ich.extend.dao;

import com.ich.extend.pojo.IWitnesses;
import com.ich.extend.pojo.IWitnessesExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IWitnessesMapper {
    
    int countByExample(IWitnessesExample example);

    int deleteByExample(IWitnessesExample example);

    int deleteByPrimaryKey(String id);

    int insert(IWitnesses record);

    int insertSelective(IWitnesses record);

    List<IWitnesses> selectByExample(IWitnessesExample example);

    IWitnesses selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") IWitnesses record, @Param("example") IWitnessesExample example);

    int updateByExample(@Param("record") IWitnesses record, @Param("example") IWitnessesExample example);

    int updateByPrimaryKeySelective(IWitnesses record);

    int updateByPrimaryKey(IWitnesses record);
    
    public List<IWitnesses> selectByRepeat(@Param("aid")String aid, @Param("wid")String wid);

    public void updateWitnessesOfHandle(@Param("wid")String wid);

    public List<IWitnesses> selectWitnessesGourpByWid(@Param("status")Integer status);

    public List<IWitnesses> selectWitnessesWidList(@Param("wid")String wid);

}
