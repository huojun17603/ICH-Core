package com.ich.module.service.impl;

import com.ich.module.dao.ModularMapper;
import com.ich.module.pojo.Modular;
import com.ich.module.service.ModularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class ModularServiceImpl implements ModularService {
	
	private Boolean isBegin = true;
	
	@Autowired
	private ModularMapper modularDao;

	@Override
	public void addModular(String name, String code, String version) {
		if(isBegin){
			isBegin = false;
			this.modularDao.cleanModular();
		}
		Modular modular = new Modular();
		modular.setModName(name);
		modular.setModCode(code);
		modular.setModVersion(version);
		modular.setRegTime(new Date());
		this.modularDao.addModular(modular);
		
	}

	@Override
	public List<Modular> getAllModular() {
		return this.modularDao.selectAllModular();
	}

}
