package com.ich.monitor.processor;

import com.ich.core.file.FileUtil;
import com.ich.monitor.pojo.IDataMonitor;
import com.ich.monitor.service.IDataMonitorService;
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

public class IDataMonitorProcessor implements ApplicationListener<ContextRefreshedEvent> {

    protected final Logger logger = Logger.getLogger(IDataMonitorProcessor.class);

    /** 动态参数XML文档路径 */
    private static String CONFIG_PATH = "task";
    /** .xml后缀 */
    private final static String suffix = ".xml";

    public static String ServiceName = "";

    @Autowired
    IDataMonitorService iDataMonitorService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0){
        logger.info("开始获取任务监控文档");
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
                    Element serverElement = document.getRootElement();

                    String servername = serverElement.attributeValue("servername");
                    ServiceName = servername;
                    List<?> taskElements = serverElement.elements("task");
                    for (Object o : taskElements) {
                        Element configElement = (Element) o;
                        IDataMonitor monitor = new IDataMonitor();
                        String code = configElement.attributeValue("code"); // 取得模块名称


                        iDataMonitorService.init(monitor);
                    }
                }
            }
        }catch (Exception e){
            logger.error("任务监控文档初始化失败！");
            e.printStackTrace();
        }
        logger.info("完成任务监控文档初始化！");
    }
}
