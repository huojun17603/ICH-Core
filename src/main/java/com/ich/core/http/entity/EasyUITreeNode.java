package com.ich.core.http.entity;

import java.util.List;

/**
 * easyUI树形控件节点格式
 * <p>Title: EUTreeNode</p>
 * <p>Description: </p>
 * @version 1.0
 */
public class EasyUITreeNode {

	private String id;			//节点id
	private String text;		//节点的内容
	private String state;		//节点的状态(open：打开;closed)
	private String parent_id;	//节点的父id
	private Integer checked;	//1 表示选中 0表示不选中
	private List<?> children; //其子内容
	private String iconCls;  //图标
	private List<String> attributes;  //属性

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public Integer getChecked() {
		return checked;
	}

	public void setChecked(Integer checked) {
		this.checked = checked;
	}

	public List<?> getChildren() {
		return children;
	}

	public void setChildren(List<?> children) {
		this.children = children;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public List<String> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<String> attributes) {
		this.attributes = attributes;
	}
}
