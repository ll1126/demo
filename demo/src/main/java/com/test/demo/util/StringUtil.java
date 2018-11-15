package com.test.demo.util;

import com.alibaba.fastjson.JSON;

/**
 * 字符串操作类
 */
public class StringUtil {

    /**
     * 将String 类型 转换成 自定义类型
     *
     * @param str   要转换的字符串
     * @param clazz 自定义类型  例如: int.class
     * @return
     */
    public static <T> T StringToBean(String str, Class<T> clazz) {
        if (str == null || str.length() <= 0 || clazz == null) {
            return null;
        }
        if (clazz == int.class || clazz == Integer.class) {
            return (T) Integer.valueOf(str);
        } else if (clazz == String.class) {
            return (T) str;
        } else if (clazz == long.class || clazz == Long.class) {
            return (T) Long.valueOf(str);
        } else {
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }

    /**
     * 将 T 类型转换成 String 类型
     *
     * @param value
     * @return
     */
    public static <T> String beanToString(T value) {
        if (value == null) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if (clazz == int.class || clazz == Integer.class) {
            return "" + value;
        } else if (clazz == String.class) {
            return (String) value;
        } else if (clazz == long.class || clazz == Long.class) {
            return "" + value;
        } else {
            return JSON.toJSONString(value);
        }
    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmply(String str) {
        if (str == null || str.trim().isEmpty()) {
            return true;
        }
        return false;
    }

}
