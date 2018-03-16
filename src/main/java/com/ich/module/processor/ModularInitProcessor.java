package com.ich.module.processor;

import com.ich.core.base.ObjectHelper;
import com.ich.core.file.FileUtil;
import com.ich.module.service.ModularService;
import com.ich.module.service.ResourceService;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * 模块信息及目录信息初始化
 * 
 * @author 霍俊
 */
public class ModularInitProcessor implements ApplicationListener<ContextRefreshedEvent> {

	protected final Logger logger = Logger.getLogger(ModularInitProcessor.class);

	
	/** 资源模块XML文档路径 */
	private final static String MODULAR_PATH = "modular";
	/** .xml后缀 */
	private final static String suffix = ".xml";
	
	@Autowired
	ResourceService resourceService;
	@Autowired
	ModularService modularService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		logger.info("开始读取<modular>目录模块信息XML文档");
		try{
			List<String> list = FileUtil.getResourceFile(MODULAR_PATH,suffix);
			for(String path : list){
				logger.info("读取文件路径：[" + path + "]");
				InputStream is = FileUtil.loader.getResourceAsStream(path);
				if(is == null){
					is = new FileInputStream(new File(path));
				}
				if(is != null){
					SAXReader reader = new SAXReader();
					Document document = reader.read(is);
					/* 读取并写入模块信息 */
					Element modularElement = document.getRootElement();
					String name = modularElement.attributeValue("name"); // 取得模块名称
					String code = modularElement.attributeValue("code"); // 模块唯一编码
					String version = modularElement.attributeValue("version"); // 取得版本
					if (ObjectHelper.isNotEmpty(name)&& ObjectHelper.isNotEmpty(code)&& ObjectHelper.isNotEmpty(version)) {
						logger.info("注册模块：" + name + "："+ code + "：" + version);
						this.modularService.addModular(name, code, version);
					}
					/* 读取并写入目录信息 */
					List<?> catalogElements = modularElement.elements("catalog");
					for (Object o : catalogElements) {
						Element catalogElement = (Element) o;
						String catalogName = catalogElement.attributeValue("name"); // 取得模块名称
						String catalogCode = catalogElement.attributeValue("code"); 
						String catalogParentCode = code;
						Integer catalogSequence = Integer.valueOf(catalogElement.attributeValue("sequence")); 
						String catalogIcon = catalogElement.attributeValue("icon");
						this.resourceService.addTempCatalogResource(catalogName,catalogCode,catalogParentCode,catalogIcon,catalogSequence,code);
						List<?> menuElements = catalogElement.elements("menu");
						int sequence = 0;
						for (Object o2 : menuElements) {
							Element menuElement = (Element) o2;
							String menuName = menuElement.attributeValue("name"); // 取得模块名称
							String menuCode = menuElement.attributeValue("code"); 
							String menuView = menuElement.attributeValue("view"); 
							String menuIcon = menuElement.attributeValue("icon");
							sequence ++;
							this.resourceService.addTempMenuResource(menuName, menuCode, catalogCode, menuView,menuIcon,sequence,code);
						}
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("完成读取<modular>目录模块信息XML文档");
		
		logger.info("开始注册<Menu&&Window>信息");
		resourceService.saveTempResource();
		logger.info("完成注册<Menu&&Window>信息");
	}

}
