package com.ich.core.listener;

import com.ich.core.file.FileUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 系统配置监听器：在系统启动时读取配置文件的信息，提供给系统使用
 * 文件要求：资源路径根目录、文件名称<config\*.properties>
 * Created by 霍俊 on 2017/7/2 0002.
 */
public class SystemConfigListener implements ServletContextListener {


    protected final Logger logger = Logger.getLogger(SystemConfigListener.class);
    /** 配置文件名称 */
    private static String CONFIG_PATH = "properties";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("开始读取系统配置【"+CONFIG_PATH+"】");
        try{
            List<String> list = FileUtil.getResourceFile(CONFIG_PATH,".properties");
            for(String path : list){
                logger.info("读取文件路径：[" + path + "]");
                InputStream is = FileUtil.loader.getResourceAsStream(path);
                if(is == null){
                    is = new FileInputStream(new File(path));
                }
                if(is != null){
                    Properties properties=new Properties();
                    properties.load(is);
                    Iterator<Map.Entry<Object, Object>> it = properties.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry<Object, Object> entry = it.next();
                        Object key = entry.getKey();
                        Object value = entry.getValue();
                        logger.info("系统配置===<"+key+">:<"+value+">");
                        SystemConfig.setParams((String)key, (String)value);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("完成读取系统配置");
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {}
}
