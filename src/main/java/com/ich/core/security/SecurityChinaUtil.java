package com.ich.core.security;

import com.ich.core.verify.RegularVerifyUtil;

/**
 * 字符信息，安全处理工具
 * 此工具为RegularVerifyUtil类的附属工具类
 * 主要针对：中国的姓名、身份证、手机号码等做字符替换功能
 * @author 霍俊
 */
public class SecurityChinaUtil {

    /** 加密银行卡 */
    public static String securityBankCard(String card){
        if(RegularVerifyUtil.isBankCard(card)){
            return card.substring(card.length()-4);
        }else{
            return "";
        }
    }

    /** 加密手机号 */
    public static String securityPhone(String phone){
        if(RegularVerifyUtil.isPhone(phone)){
            return phone.substring(0, 3)+"*****"+phone.substring(8, 11);
        }else{
            return "";
        }
    }
    /** 加密邮箱 */
    public static String securityEmail(String email){
        if(RegularVerifyUtil.isEmail(email)){
            String str = email.substring(0,email.indexOf("@"));
            if(str.length()>4){
                return str.substring(0,4)+"****"+email.substring(email.indexOf("@"));
            }else{
                return str.substring(0,1)+"****"+email.substring(email.indexOf("@"));
            }
        }else{
            return "";
        }
    }

    /** 加密身份证 */
    public static String securityIdcard(String idcard){
        if(RegularVerifyUtil.isIDCard(idcard)){
            return idcard.substring(0, 4)+"**********"+idcard.substring(14, 18);
        }else{
            return "";
        }
    }

    /** 加密姓名 */
    public static String securityIdName(String idname){
        if(RegularVerifyUtil.isChinese(idname)){
            return "*"+idname.substring(1);
        }else{
            return "";
        }
    }

}
