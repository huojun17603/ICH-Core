package com.ich.core.base;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ObjMap {

    /**
     * 把对象转换成MAP
     */
    public static Map<String, Object> ObjectToMap(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        Map<String, Object> result = new HashMap<String, Object>();
        for (Field field : fields) {
            if(field.getModifiers()==2){//修饰符->private;由Modifier类解释
                result.put(field.getName(), getFieldValue(obj,field.getName()));
            }
        }
        return result;
    }

    /**
     * 把MAP转化为指定类型的对象
     */
    public static void MapToObject(Map<String, Object> map, Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            if(field.getModifiers()==2){//修饰符->private;由Modifier类解释
                String fieldName = field.getName();
                Object filedValue = map.get(fieldName);
                if(null != filedValue){
                    setFieldValue(obj,fieldName,filedValue,field.getType());
                }
            }
        }
    }

    private static Object getFieldValue(Object thisClass, String fieldName) {
        Object value = new Object();
        Method method = null;
        try {
            String methodName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            method = thisClass.getClass().getMethod("get" + methodName);
            value = method.invoke(thisClass);
        } catch (Exception e) {
            return null;
        }
        return value;
    }

    private static void setFieldValue(Object thisClass, String fieldName, Object filedValue, Class<?> classType) {
        Method method = null;
        try {
            String methodName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            method = thisClass.getClass().getMethod("set" + methodName,classType);
            method.invoke(thisClass,filedValue);
        } catch (Exception e) {
            //如果方法调用是吧，这就不对这个值进行修改了
        }

    }

//    public static void main(String[] args) {
//        HttpResponse dto = new HttpResponse(HttpResponse.HTTP_OK, "??");
//        Map<String, Object> rsu = ObjectToMap(dto);
//        System.out.println("xx");
//        HttpResponse x = new HttpResponse(0, "");
//        MapToObject(rsu,x);
//        System.out.println(x.getMsg());
//    }

}
