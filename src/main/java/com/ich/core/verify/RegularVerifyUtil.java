package com.ich.core.verify;

import com.ich.core.base.ObjectHelper;

import java.util.regex.Pattern;

/**
 * 正则表达式验证工具类
 * 提供通用且固定的验证规则
 * PS：每个系统针对于一些用户的账号个人信息有其自有的标准规则，通用的验证规则无法满足所有
 * 而这些规则在系统已上线后的改动也是可能存在极高的系统风险的。
 * 所以在我看来系统开发之初就应该对这些信息设计并固定于代码中，如果必须要改变也需要对改变的风险进行评估包括：
 * 与其他规则之间是否存在影响，以往数据的处理。
 * @author 霍俊
 */
public class RegularVerifyUtil {

    /**
     * 正则表达式：验证用户名
     * 说明：5-17位字母数字下划线
     */
    public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,17}$";

    /**
     * 正则表达式：验证密码
     */
    public static final String REGEX_PASSWORD = "^([A-Z]|[a-z]|[0-9]){8,}$";

    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^(1[3-8])\\d{9}$";

    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$";

    /**
     * 正则表达式：验证汉字
     */
    public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5]{0,}$";

    /**
     * 正则表达式：验证身份证
     */
    public static final String REGEX_ID_CARD = "(^\\d{17}[a-z0-9A-Z]{1}$)|(^\\d{14}[a-z0-9A-Z]{1}$)";

    /**
     * 正则表达式：验证URL
     */
    public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

    /**
     * 正则表达式：验证IP地址
     */
    public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";

    /**
     * 正则表达式：验证银行卡
     */
    public static final String REGEX_BANK_CARD = "^(\\d{15,30})$";

    /**
     * 判断是否是用户名
     * 规则：5-17位字母数字下划线
     */
    public static boolean isUserName(String value){
        if(ObjectHelper.isEmpty(value))return false;
        return Pattern.matches(REGEX_USERNAME, value);
    }

    /**
     * 判断是否是登录密码
     * 规则：8位以上字母及数字
     */
    public static boolean isPassWord(String value){
        if(ObjectHelper.isEmpty(value))return false;
        return Pattern.matches(REGEX_PASSWORD, value);
    }

    /**
     * 判断是否是手机号码（中国）
     */
    public static boolean isPhone(String value){
        if(ObjectHelper.isEmpty(value))return false;
        return Pattern.matches(REGEX_MOBILE, value);
    }

    /**
     * 判断是否是邮箱
     */
    public static boolean isEmail(String value){
        if(ObjectHelper.isEmpty(value))return false;
        return Pattern.matches(REGEX_EMAIL, value);
    }

    /**
     * 判断是否是身份证（中国）
     */
    public static boolean isIDCard(String value){
        if(ObjectHelper.isEmpty(value))return false;
        return Pattern.matches(REGEX_ID_CARD, value);
    }

    /**
     * 判断是否是URL
     */
    public static boolean isURL(String value){
        if(ObjectHelper.isEmpty(value))return false;
        return Pattern.matches(REGEX_URL, value);
    }

    /**
     * 判断是否是汉字
     */
    public static boolean isChinese(String value){
        if(ObjectHelper.isEmpty(value))return false;
        return Pattern.matches(REGEX_CHINESE, value);
    }

    /**
     * 判断是否是IP
     */
    public static boolean isIP(String value){
        if(ObjectHelper.isEmpty(value))return false;
        return Pattern.matches(REGEX_IP_ADDR, value);
    }

    /**
     * 判断是否是整数
     */
    public static boolean isNum(String value){
        if(ObjectHelper.isEmpty(value))return false;
        return Pattern.matches("^[0-9]*$", value);
    }

    /**
     * 判断是否是小数
     */
    public static boolean isReal(String value){
        if(ObjectHelper.isEmpty(value))return false;
        return Pattern.matches("^[0-9]+\\.{0,1}[0-9]{0,10}$", value);
    }

    /**
     * 判断是否是银行卡（中国）
     */
    public static boolean isBankCard(String value){
        if(ObjectHelper.isEmpty(value))return false;
        return Pattern.matches(REGEX_BANK_CARD, value);
    }
}
