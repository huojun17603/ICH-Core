package com.ich.module.service;

import com.ich.module.annotation.Link;
import com.ich.module.annotation.Window;
import com.ich.module.pojo.MenuResource;

import java.util.List;


/**
 * 系统资源业务管理接口
 * @since  2016-1-26
 * @author 霍俊
 */
public interface ResourceService {
	
	/**
	 * 通过<@Link>零时注册系统的<链接>信息
	 * @param link <@Link>注册类容
	 * @param url 注册地址
	 */
	public void addTempLinkResource(Link link, String url);
	/**
	 * 通过XML文件零时注册系统的<菜单>信息
	 * @param name 菜单名称
	 * @param code 菜单编码
	 * @param parentCode 父级编码【所属目录编码】
	 */
	public void addTempMenuResource(String name, String code, String parentCode, String view, String icon, Integer sequence,String mcode);
	/**
	 * 通过XML文件零时注册系统的<目录>信息
	 * @param name 目录名称
	 * @param code 目录编码
	 * @param parentCode 父级编码【空为根级】
	 */
	public void addTempCatalogResource(String name, String code, String parentCode, String icon, Integer sequence,String mcode);
	/**
	 * 通过<@Window>零时注册系统的<窗口>信息
	 * @param window <@Window>注册类容
	 * @param url 注册地址
	 */
	public void addTempWindowResource(Window window, String url);
	/**
	 * 把所有零时资源信息保存至数据库
	 */
	public void saveTempResource();
	/**
	 * 根据编码获取此编码下的所有子级资源信息
	 * @param code 编码
	 * @return 所有子级菜单资源信息
	 */
	public List<MenuResource> getChildMenuResourceByCode(String code);
	/**
	 * 根据编码获取此编码对应资源信息
	 * @param code 编码
	 * @return 对应菜单资源信息
	 */
	public MenuResource getMenuResourceByCode(String code);
	

	
}
