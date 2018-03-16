package com.ich.config.pojo;

import com.ich.core.base.ObjectHelper;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 动态全局配置
 * 注意：
 * 除了值意外的其他信息有开发人员填写，并保证其正确性
 * 正确的配置正则表达式，可以保证管理员不会因为填写错误而导致系统崩溃！
 */
public class IConfig {

    /** 键：主键*/
    private String ikey;
    /** 值 */
    private String ivalue;
    /** 验证正则表达式 */
    private String driver;
    /** 配置分组名称 */
    private String groupname;
    /** 配置说明*/
    private String docs;

    private static Map<String,IConfig> configMap = new HashMap<>();

    public static final String CHANGER_TIME = "CHANGER_TIME";

    public static boolean vry(String driver,String value){
        if(ObjectHelper.isEmpty(value))return false;
        return Pattern.matches(driver, value);
    }

    /** 验证失败存在配置项（不兼容分布式） */
    public static Boolean containsKey(String key) {
        return configMap.containsKey(key);
    }

    /** 获取配置项的值（不兼容分布式） */
    public static String getParams(String key) {
        String value = null;
        if(configMap.containsKey(key)){
            IConfig config = configMap.get(key);
            value = config.getIvalue();
        }
        return value;
    }

    /** 配置值（不兼容分布式） */
    public static void setParams(String key,IConfig value) {
        configMap.put(key, value);
    }


    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getIkey() {
        return ikey;
    }

    public void setIkey(String ikey) {
        this.ikey = ikey;
    }

    public String getIvalue() {
        return ivalue;
    }

    public void setIvalue(String ivalue) {
        this.ivalue = ivalue;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getDocs() {
        return docs;
    }

    public void setDocs(String docs) {
        this.docs = docs;
    }

}
