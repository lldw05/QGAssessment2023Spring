package com.lldw.www.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author
 * @date
 */
public class OthersUtil {
    /**
     * 将jsonStr转为map
     * @param str jsonStr语句
     * @return Map<String, String>
     */
    public static Map<String, String> jsonStringtoMap(String str) {
        //{"messageContent":"测试发布动态","createTime":"2023-04-209:32:57","shopId":"6","goodsId":"8"}

        //去掉头尾大括号
        str = str.substring(1, str.length() - 1);

        //去掉双引号
        str.replace("\"", "");

        //以逗号分割
        String[] strs = str.split(",");
        Map<String, String> map = new HashMap<>();
        for (String s :
                strs) {
            String key = s.split(":")[0];
            String value = s.split(":")[1];

            map.put(key, value);
        }
        return map;
    }
}
