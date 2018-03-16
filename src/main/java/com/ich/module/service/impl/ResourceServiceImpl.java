package com.ich.module.service.impl;

import com.ich.core.base.ObjectHelper;
import com.ich.module.annotation.Link;
import com.ich.module.annotation.Window;
import com.ich.module.dao.MenuResourceMapper;
import com.ich.module.dao.WindowResourceMapper;
import com.ich.module.pojo.MenuResource;
import com.ich.module.pojo.WindowResource;
import com.ich.module.service.ResourceService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ResourceServiceImpl implements ResourceService {
	
	protected final Logger logger = Logger.getLogger(ResourceServiceImpl.class);

	@Autowired
	private MenuResourceMapper menuResourceDao;
	@Autowired
	private WindowResourceMapper windowResourceDao;

	@Override
	public void addTempLinkResource(Link link, String url) {
		MenuResource resource = new MenuResource();
		resource.setCode(link.code());
		resource.setParent(link.parent());
		resource.setName(link.name());
		resource.setLevel(link.level());
		resource.setDoc(link.doc());
		resource.setType(Link.TYPE_LINK);
		resource.setUrl(url);
		resource.setSequence(-1);
		MenuResource.tempList.add(resource);
	}
	
	@Override
	public void addTempMenuResource(String name, String code, String parentCode, String view,String icon,Integer sequence,String mcode) {
		MenuResource resource = new MenuResource();
		resource.setCode(code);
		resource.setParent(parentCode);
		resource.setName(name);
		resource.setView(view);
		resource.setLevel(Link.LEVEL_READ);
		resource.setType(Link.TYPE_MENU);
		resource.setIcon(icon);
		resource.setModular(mcode);
		resource.setSequence(sequence);
		MenuResource.tempList.add(resource);
	}

	@Override
	public void addTempCatalogResource(String name, String code, String parentCode,String icon,Integer sequence,String mcode) {
		MenuResource resource = new MenuResource();
		resource.setCode(code);
		resource.setParent(parentCode);
		resource.setName(name);
		resource.setLevel(Link.LEVEL_READ);
		resource.setType(Link.TYPE_CATALOG);
		resource.setIcon(icon);
		resource.setModular(mcode);
		resource.setSequence(sequence);
		MenuResource.tempList.add(resource);
	}

	@Override
	public void addTempWindowResource(Window window, String url) {
		WindowResource windowResource = new WindowResource();
		windowResource.setCode(window.code());
		windowResource.setName(window.name());
		windowResource.setInclude(window.include());
		windowResource.setModular(window.modular());
		windowResource.setUrl(url);
		WindowResource.tempList.add(windowResource);
	}

	@Override
	public void saveTempResource() {
		//注意：多余的注册信息需要手动清理
		for(MenuResource resource : MenuResource.tempList){
			MenuResource entity = this.menuResourceDao.selectResourceByCode(resource.getCode());
			if(ObjectHelper.isEmpty(entity)){
				logger.info("注册<Menu>:【"+resource.getName()+"（"+resource.getCode()+"）】");
				this.menuResourceDao.insertMenuResource(resource);
			}else{
				logger.info("更新<Menu>:【"+resource.getName()+"（"+resource.getCode()+"）】");
				this.menuResourceDao.updateMenuResource(resource);
			}
		}
		for(WindowResource resource : WindowResource.tempList){
			WindowResource entity = this.windowResourceDao.selectResourceByCode(resource.getCode());
			if(ObjectHelper.isEmpty(entity)) {
				logger.info("注册<Window>:【" + resource.getName() + "（" + resource.getCode() + "）】");
				this.windowResourceDao.insertWindowResource(resource);
			}else{
				logger.info("更新<Window>:【" + resource.getName() + "（" + resource.getCode() + "）】");
				this.windowResourceDao.updateWindowResource(resource);
			}
		}
		MenuResource.tempList.clear();
		WindowResource.tempList.clear();
	}

	@Override
	public List<MenuResource> getChildMenuResourceByCode(String code) {
		return this.menuResourceDao.selectCResourceByCode(code);
	}

	@Override
	public MenuResource getMenuResourceByCode(String code) {
		return this.menuResourceDao.selectResourceByCode(code);
	}

	

}
