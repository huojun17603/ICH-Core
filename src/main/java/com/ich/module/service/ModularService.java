package com.ich.module.service;

import com.ich.module.pojo.Modular;

import java.util.List;


public interface ModularService {
	
	/**
	 * 添加模块信息
	 * @param name 模块名称
	 * @param code 模块编码
	 * @param version 模块版本号
	 */
	void addModular(String name, String code, String version);
	/**
	 * 获取系统所有的模块信息
	 */
	List<Modular> getAllModular();

}
