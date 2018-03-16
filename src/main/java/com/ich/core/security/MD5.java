package com.ich.core.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具
 * Created by 霍俊 on 2017/7/2 0002.
 */
public class MD5 {

    /**
     * 密码加密
     * @param str 密码
     * @return 返回加密字符
     */
    public static String encryption(String str) {
        String outStr = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            // md.digest() 该函数返回值为存放哈希值结果的byte数组
            outStr = byteToString(md.digest(str.getBytes()));
        } catch (NoSuchAlgorithmException nsae) {
            nsae.printStackTrace();
        }
        return outStr;
    }

    /**
     * 解密！其实是对字符加密后 和原来的匹配
     * @param o 已有密码
     * @param i 认证密码
     * @return 返回密码是否匹配
     */
    public static Boolean decryption(String o, String i) {
        if (o.equals(encryption(i)))
            return true;
        return false;
    }

    // 全局数组
    private final static String[] strDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };


    // 返回形式为数字跟字符串
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        // System.out.println("iRet="+iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

    // 转换字节数组为16进制字串
    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }
}
