package com.ich.module.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * 记录系统上拥有的所有资源信息
 * @author IChameleon
 *
 */
public class MenuResource {
	
	/** 菜单资源零时注册列表 */
	public static List<MenuResource> tempList = new ArrayList<MenuResource>();
	
	/** 菜单唯一编码，由系统自动录入，外部不可以修改 */
	private String code;
	/** 菜单名称 */
	private String name;
	/** 菜单链接地址唯一，由系统自动录入，外部不可以修改  */
	private String url;
	/** 菜单父节点编码 【menuCode】 顶级菜单父级为"root" */
	private String parent;
	/** 菜单类别  */
	private Integer type;
	/** 菜单级别   */
	private Integer level;
	/** 菜单图标信息 */
	private String icon;
	/** 菜单说明 */
	private String doc;
	/** 资源菜单指向地址 */
	private String view;
	/**排序 */
	private Integer sequence;
	/**Modular*/
	private String modular;

	public String getModular() {
		return modular;
	}

	public void setModular(String modular) {
		this.modular = modular;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getDoc() {
		return doc;
	}
	public void setDoc(String doc) {
		this.doc = doc;
	}
	public String getIcon() {
		return icon;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	
	
	
	
}
