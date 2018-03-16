package com.ich.module.dao;

import com.ich.module.pojo.Modular;

import java.util.List;

public interface ModularMapper {
	
	public void cleanModular();
	
	public void addModular(Modular modular);

	public List<Modular> selectAllModular();


}
