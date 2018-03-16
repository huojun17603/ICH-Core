package com.ich.config.processor;

import com.ich.config.pojo.IConfig;
import com.ich.config.service.IConfigService;
import com.ich.core.file.FileUtil;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

public class IConfigProcessor implements ApplicationListener<ContextRefreshedEvent> {

    protected final Logger logger = Logger.getLogger(IConfigProcessor.class);

    /** 动态参数XML文档路径 */
    private static String CONFIG_PATH = "properties";
    /** .xml后缀 */
    private final static String suffix = ".xml";

    //手动选择注入方式
    private IConfigService iConfigService;

    public IConfigService getiConfigService() {
        return iConfigService;
    }

    public void setiConfigService(IConfigService iConfigService) {
        this.iConfigService = iConfigService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0){
        logger.info("开始配置全局动态系统参数！");
        try {
            List<String> list = FileUtil.getResourceFile(CONFIG_PATH, suffix);
            for(String path : list){
                logger.info("读取文件路径：[" + path + "]");
                InputStream is = FileUtil.loader.getResourceAsStream(path);
                if(is == null){
                    is = new FileInputStream(new File(path));
                }
                if(is != null){
                    SAXReader reader = new SAXReader();
                    Document document = reader.read(is);
					/* 读取并写入配置信息 */
                    Element groupElement = document.getRootElement();
                    String groupname = groupElement.attributeValue("name"); // 取得分组名称
                    List<?> configElements = groupElement.elements("config");
                    for (Object o : configElements) {
                        Element configElement = (Element) o;
                        String key = configElement.attributeValue("key"); // 取得模块名称
                        String value = configElement.attributeValue("value");
                        String driver = configElement.attributeValue("driver");
                        String docs = configElement.attributeValue("docs");
                        IConfig config = new IConfig();
                        config.setIkey(key);
                        config.setIvalue(value);
                        config.setDriver(driver);
                        config.setGroupname(groupname);
                        config.setDocs(docs);
                        iConfigService.initConfig(config);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        logger.info("完成配置全局动态系统参数！");
    }
}
