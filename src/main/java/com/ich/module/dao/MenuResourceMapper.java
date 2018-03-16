package com.ich.module.dao;

import com.ich.module.pojo.MenuResource;

import java.util.List;

public interface MenuResourceMapper {
	
	public MenuResource selectResourceByCode(String code);

	public void clearMenuResource();

	public void insertMenuResource(MenuResource resource);

	public List<MenuResource> selectAllResource();

	public List<MenuResource> selectCResourceByCode(String code);

    void updateMenuResource(MenuResource resource);
}
