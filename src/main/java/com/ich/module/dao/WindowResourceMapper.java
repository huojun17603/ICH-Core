package com.ich.module.dao;


import com.ich.module.pojo.WindowResource;

import java.util.List;

/**
 * TODO 
 * @author 霍俊
 */
public interface WindowResourceMapper {
	
	public int insertWindowResource(WindowResource windowResource);

	public void clearWindowResource();

	public List<WindowResource> selectAllResource();

	public WindowResource selectResourceByCode(String windowCode);

    void updateWindowResource(WindowResource resource);
}
