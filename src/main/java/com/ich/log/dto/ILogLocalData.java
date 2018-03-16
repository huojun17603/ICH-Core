package com.ich.log.dto;

import com.ich.core.base.ThreadLocalUtil;

import java.util.HashMap;
import java.util.Map;

public class ILogLocalData {

    private static Map<Integer,Map<String,Object>> data = new HashMap<>();

    public static int getDataSize(){
        return data.size();
    }

    public synchronized static void setData(String key ,Object value){
        Integer id = ThreadLocalUtil.getID();
//        System.out.println("开始："+id);
        Map<String,Object> kv = new HashMap<>();
        if(!data.containsKey(id)){
            data.put(id,kv);
        }else{
            kv = data.get(id);
        }
        kv.put(key,value);
    }

    public static Object getData(String key){
        Integer id = ThreadLocalUtil.getID();
        if(data.containsKey(id)){
            Map<String,Object> kv = data.get(id);
            return kv.get(key);
        }
        return null;
    }

    public static void clear(){
        Integer id = ThreadLocalUtil.getID();
//        System.out.println("结束："+id);
        data.remove(id);
    }
}
