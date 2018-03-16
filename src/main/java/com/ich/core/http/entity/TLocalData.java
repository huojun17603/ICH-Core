package com.ich.core.http.entity;

import com.ich.core.base.ThreadLocalUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户线程全局静态数据
 */
@Deprecated
public class TLocalData {

    private static Map<Integer,Map<String,Object>> data = new HashMap<>();

    public static void setData(String key ,Object value){
        Integer id = ThreadLocalUtil.getID();
        Map<String,Object> kv;
        if(!data.containsKey(id)){
            kv = new HashMap<>();
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
        //什么时候清理是个问题
        Integer id = ThreadLocalUtil.getID();
        data.remove(id);
    }



}
