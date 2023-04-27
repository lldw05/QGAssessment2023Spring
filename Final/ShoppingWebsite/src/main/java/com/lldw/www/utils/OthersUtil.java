package com.lldw.www.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author
 * @date
 */
public class OthersUtil {
    /**
     * 将bean实体属性和值 存在map
     * @param o jsonStr语句
     * @return Map<String, String>
     */
    public static Map<String, Object> beantoMap(Object o) {
        Map<String,Object> map = new HashMap<>();
        try {
            //获取 属性字段 和 属性值 存放到 map 中
            for(Field field : o.getClass().getDeclaredFields()){
                //通过get方法直接获取属性值
                field.setAccessible(true);
                // field.getName() 获取 属性名 ，field.get(v1) 获取 属性数值
                map.put( field.getName(), field.get(o));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
