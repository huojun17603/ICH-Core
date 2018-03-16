package com.ich.module.pojo;

import java.util.Date;

/**
 * 记录系统上拥有的模块信息
 * 新增、修改、删除规则：
 * 	系统在启动完成后清空所有模块及资源信息
 * 	然后从资源文件<modular>中注册信息
 * 查询规则：
 * 	系统可以取得其模块列表
 * 	系统可以向平台验证模块版本
 * @author IChameleon
 *
 */
public class Modular {
	/** 
	 * 模块编码：一个模块只有一个唯一编码但可以有多个版本号 
	 * 模块命名规则：<公司代码>-<模块代码>
	 * */ 
	private String modCode;
	/** 版本名称 */
	private String modName;
	/** 模块版本号 */
	private String modVersion;
	/** 注册时间 */
	private Date regTime;

	public String getModCode() {
		return modCode;
	}
	public void setModCode(String modCode) {
		this.modCode = modCode;
	}
	public String getModName() {
		return modName;
	}
	public void setModName(String modName) {
		this.modName = modName;
	}
	public String getModVersion() {
		return modVersion;
	}
	public void setModVersion(String modVersion) {
		this.modVersion = modVersion;
	}
	public Date getRegTime() {
		return regTime;
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

}
