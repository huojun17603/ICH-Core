package com.ich.core.base;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 对象工具类
 * 功能：对象空判断、对象比较
 * Created by 霍俊 on 2017/7/2 0002.
 */
public class ObjectHelper {

    /**
     *
     * 判断这个Object是否为Null或长度为0
     * @param obj  需要进行判断的对象
     * @return boolean 返回是否为空
     * @变更记录 2015-6-24 上午9:24:30  XX 创建
     *
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null)
            return true;

        if (obj instanceof Collection) {
            return ((Collection<?>) obj).isEmpty();
        }

        if (obj instanceof String) {
            return ((String) obj).equalsIgnoreCase("null")
                    | ((String) obj).trim().toString().equals("");
        }

        if (obj instanceof StringBuffer) {
            return ((StringBuffer) obj).length() == 0;
        }

        if (obj.getClass().isArray()) {
            try {
                Object[] a = (Object[]) obj;

                boolean b = true;
                for (Object o : a) {
                    b = b & isEmpty(o);

                    if (!b)
                        break;
                }

                return b;
            } catch (ClassCastException e) {
            }
        }
        return false;
    }

    /**
     * 判断这个Object是否不为Null或长度不为0
     * @param obj
     * @return
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    /**
     * 比较两个BEAN或MAP对象的值是否相等
     * 如果是BEAN与MAP对象比较时MAP中的key值应与BEAN的属性值名称相同且字段数目要一致
     * @param source 源对象
     * @param target 目标对象
     * @return 是否一致
     */
    public static boolean domainEquals(Object source, Object target) {
        if (source == null || target == null) {
            return false;
        }
        boolean rv = true;
        if (source instanceof Map) {
            rv = mapOfSrc(source, target, rv);
        } else {
            rv = classOfSrc(source, target, rv);
        }
        return rv;
    }

    /**
     * 源目标为MAP类型时
     * @param source
     * @param target
     * @param rv
     * @return
     */
    private static boolean mapOfSrc(Object source, Object target, boolean rv) {
        HashMap<String, String> map = (HashMap) source;
        for (String key : map.keySet()) {
            if (target instanceof Map) {
                HashMap<String, String> tarMap = (HashMap) target;
                if(tarMap.get(key)==null){
                    rv = false;
                    break;
                }
                if (!map.get(key).equals(tarMap.get(key))) {
                    rv = false;
                    break;
                }
            } else {
                String tarValue = getClassValue(target, key) == null ? "" : getClassValue(target, key).toString();
                if (!tarValue.equals(map.get(key))) {
                    rv = false;
                    break;
                }
            }
        }
        return rv;
    }

    /**
     * 源目标为非MAP类型时
     * @param source
     * @param target
     * @param rv
     * @return
     */
    private static boolean classOfSrc(Object source, Object target, boolean rv) {
        Class<?> srcClass = source.getClass();
        Field[] fields = srcClass.getDeclaredFields();
        for (Field field : fields) {
            String nameKey = field.getName();
            if (target instanceof Map) {
                HashMap<String, String> tarMap;
                tarMap = (HashMap) target;
                String srcValue = getClassValue(source, nameKey) == null ? "" : getClassValue(source, nameKey)
                        .toString();
                if(tarMap.get(nameKey)==null){
                    rv = false;
                    break;
                }
                if (!tarMap.get(nameKey).equals(srcValue)) {
                    rv = false;
                    break;
                }
            } else {
                String srcValue = getClassValue(source, nameKey) == null ? "" : getClassValue(source, nameKey)
                        .toString();
                String tarValue = getClassValue(target, nameKey) == null ? "" : getClassValue(target, nameKey)
                        .toString();
                if (!srcValue.equals(tarValue)) {
                    rv = false;
                    break;
                }
            }
        }
        return rv;
    }

    /**
     * 根据字段名称取值
     * @param obj
     * @param fieldName
     * @return
     */
    public static Object getClassValue(Object obj, String fieldName) {
        if (obj == null) {
            return null;
        }
        try {
            Class beanClass = obj.getClass();
            Method[] ms = beanClass.getMethods();
            for (int i = 0; i < ms.length; i++) {
                // 非get方法不取
                if (!ms[i].getName().startsWith("get")) {
                    continue;
                }
                Object objValue ;
                try {
                    objValue = ms[i].invoke(obj, new Object[] {});
                } catch (Exception e) {
                    continue;
                }
                if (objValue == null) {
                    continue;
                }
                if (ms[i].getName().toUpperCase().equals(fieldName.toUpperCase())
                        || ms[i].getName().substring(3).toUpperCase().equals(fieldName.toUpperCase())) {
                    return objValue;
                } else if (fieldName.toUpperCase().equals("SID")
                        && (ms[i].getName().toUpperCase().equals("ID") || ms[i].getName().substring(3).toUpperCase()
                        .equals("ID"))) {
                    return objValue;
                }
            }
        } catch (Exception e) {
            // logger.info("取方法出错！" + e.toString());
        }
        return null;
    }

//    public static void main(String[] args) {
//        List<String> list = new ArrayList<String>();
//        System.out.println(isEmpty(list));
//        System.out.println(isNotEmpty(list));
//        list.add("1");
//        System.out.println(isEmpty(list));
//        System.out.println(isNotEmpty(list));
//        Map<String,String> map1 = new HashMap<String, String>();
//        Map<String,String> map2 = new HashMap<String, String>();
//        map1.put("x","x");
//        map2.put("y","y");
//        System.out.println(domainEquals(map1, map2));
//        map2.put("x","x");
//        map1.put("y","y");
//        System.out.println(domainEquals(map1, map2));
//    }

}
