package com.ich.international.processor;

import com.ich.core.base.ObjectHelper;
import com.ich.core.file.FileUtil;
import com.ich.core.listener.SystemConfig;
import com.ich.international.pojo.ILocaleMessage;
import com.ich.module.processor.ModularInitProcessor;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class ILocaleInitProcessor implements ApplicationListener<ContextRefreshedEvent> {

    protected final Logger logger = Logger.getLogger(ModularInitProcessor.class);

    private static String CONFIG_PATH = "language";

    private static String SUFFIX = ".properties";

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
        //获取语言
        String LANGUAGE = SystemConfig.getParams("LANGUAGE");
        if(ObjectHelper.isEmpty(LANGUAGE)){
            logger.warn("LANGUAGE load failure!");
            return;
        }
        logger.info("开始载入本地语言："+LANGUAGE);
        try{
            List<String> list = FileUtil.getResourceFile(CONFIG_PATH,SUFFIX);
            for(String path : list){
                if(path.indexOf(LANGUAGE + SUFFIX) != -1){
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
                            ILocaleMessage.DATAMAP.put(String.valueOf(key),String.valueOf(value));
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("本地语言载入完成");
    }

}
