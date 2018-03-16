package com.ich.core.base;

import java.util.Random;

/**
 * 随机数生成类
 * Created by 霍俊 on 2017/7/2 0002.
 */
public class RandomInt {

    /**
     * 按参数生成对应个数的随机字符串
     * @param num 随机字符串长度
     * @return 随机字符串
     */
    public static String findNum(Integer num){
        Random random = new Random();
        Integer max = 1;
        for (int i=0;i<num;i++) max = max * 10;
        Integer rint = random.nextInt(max);
        if(rint<(max/10)) rint += (max/10);
        return rint.toString();
    }

//    public static void main(String[] args) {
//        for (int i=0;i<100;i++) {
//            System.out.println(findNum(5));
//        }
//    }
}
