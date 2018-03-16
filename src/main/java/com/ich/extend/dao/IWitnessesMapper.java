package com.ich.extend.dao;

import com.ich.extend.pojo.IWitnesses;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IWitnessesMapper {

    public int insert(IWitnesses witnesses);

    public List<IWitnesses> selectByRepeat(@Param("aid")String aid, @Param("wid")String wid);

    public void updateWitnessesOfHandle(@Param("wid")String wid);

    public List<IWitnesses> selectWitnessesGourpByWid(@Param("status")Integer status);

    public List<IWitnesses> selectWitnessesWidList(@Param("wid")String wid);

}
