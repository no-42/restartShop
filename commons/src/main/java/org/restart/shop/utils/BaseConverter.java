/*
 *  Copyright (c) 2014-2017. 墨博云舟 All Rights Reserved.
 */

package org.restart.shop.utils;

import com.fasterxml.jackson.databind.JavaType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Des:基础转换类
 * Created by UlverYang[yang.jian@mobcb.com] on 2017-01-23 20:55.
 */
public class BaseConverter implements Serializable {


    public static <T> T dtoToDo(Object obj, Class<T> c) {

        if (null == obj) {
            return null;
        }
        String json = JsonUtils.toJson(obj);
        T t = JsonUtils.toObject(json, c);
        return t;
    }

    public static <T> List<T> dtoToDo(List list, Class<T> c) {

        if (null == list) {
            return null;
        }
        String json = JsonUtils.toJson(list);

        JavaType javaType = JsonUtils.getCollectionType(List.class, c);
        List<T> t = JsonUtils.toObject(json, javaType);
        return t;
    }

    public static <T> T doToResp(Object obj, Class<T> c) {

        if (null == obj) {
            return null;
        }
        String json = JsonUtils.toJson(obj);
        T t = JsonUtils.toObject(json, c);
        return t;
    }

    public static <T> List<T> doToResp(List list, Class<T> c) {

        if (null == list) {
            return null;
        }
        String json = JsonUtils.toJson(list);

        JavaType javaType = JsonUtils.getCollectionType(List.class, c);
        List<T> t = JsonUtils.toObject(json, javaType);
        return t;
    }

    public static <T> T toObject(Object obj, Class<T> c) {
        if (null == obj) {
            return null;
        }
        String json = JsonUtils.toJson(obj);
        T t = JsonUtils.toObject(json, c);
        return t;
    }

    public static <T, R> R convertToObject(T t, Function<T, R> function) {
        if (t == null) {
            return null;
        } else {
            return function.apply(t);
        }
    }

    /**
     * 对象属性拷贝
     *
     * @param source      源对象
     * @param targetClass 目标对象类型
     * @return 目标对象
     * @since 2017年9月12日 by shen.zhibing
     */
    public static <T> T copyProperties(Object source, Class<T> targetClass) {
        Log log = LogFactory.getLog("BaseConverter"); // 日志
        T target; // 目标对象
        if (source == null) {
            target = null;
        } else {

            Field[] declaredFields = targetClass.getDeclaredFields();
            List<Field> ignoreFields = new ArrayList<>(); // 忽略Field列表(即Filed类型为集合类，一般为List)
            for (Field field : declaredFields) {
                Class<?> type = field.getType();
                if (!BeanUtils.isSimpleProperty(type)) {
                    ignoreFields.add(field);
                }
            }

            try {
                target = targetClass.newInstance();
                BeanUtils.copyProperties(source, target, ignoreFields.stream().map(Field::getName).toArray(String[]::new));
                if (!ignoreFields.isEmpty()) {
                    // 拷贝列表类型属性
                    for (Field field : ignoreFields) {
                        PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(source.getClass(), field.getName());
                        if (sourcePd == null) {
                            continue;
                        }
                        Method readMethod = sourcePd.getReadMethod(); // 源对象read方法
                        if (readMethod == null) {
                            continue;
                        }
                        PropertyDescriptor targetPd = BeanUtils.getPropertyDescriptor(targetClass, field.getName());
                        if (targetPd == null) {
                            continue;
                        }
                        Method writeMethod = targetPd.getWriteMethod(); // 目标对象write方法
                        if (writeMethod == null) {
                            continue;
                        }

                        if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                            readMethod.setAccessible(true);
                        }
                        Object value = readMethod.invoke(source);

                        if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                            writeMethod.setAccessible(true);
                        }

                        if (value instanceof List) {
                            writeMethod.invoke(target,
                                    copyList(
                                            (List<?>) value,
                                            (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0]));
                        } else if (value instanceof Set) {
                            writeMethod.invoke(target,
                                    copySet(
                                            (Set<?>) value,
                                            (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0]));

                        } else if (value instanceof Map) {
                            try {
                                writeMethod.invoke(target,
                                        copyMap(
                                                (Map<?, ?>) value,
                                                (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0],
                                                (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[1]));
                            } catch (ClassCastException e) {
                                log.warn("Cast simple value type failure...");
                            }
                        }
                    }
                }
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                log.error("Object value deep copy failure...", e);
                target = null;
            }
        }
        return target;
    }

    /**
     * 列表拷贝
     *
     * @param source             源列表
     * @param targetElementClass 目标列表元素类型
     * @return 目标列表
     * @since 2017年9月14日 by shen.zhibing
     */
    public static <T> List<T> copyList(List<?> source, Class<T> targetElementClass) {
        return Optional.ofNullable(source)
                .orElse(Collections.emptyList())
                .stream()
                .map(item -> copyProperties(item, targetElementClass))
                .collect(Collectors.toList());
    }

    /**
     * Set拷贝.
     *
     * @param source             源set
     * @param targetElementClass 目标set元素类型
     * @return 目标set
     */
    public static <T> Set<T> copySet(Set<?> source, Class<T> targetElementClass) {
        return Optional.ofNullable(source)
                .orElse(Collections.emptySet())
                .stream()
                .map(item -> copyProperties(item, targetElementClass))
                .collect(Collectors.toSet());
    }

    /**
     * Map拷贝.
     *
     * @param source           源map
     * @param targetKeyClass   目标map键类型
     * @param targetValueClass 目标map值类型
     * @return 目标map
     * @throws ClassCastException 参见 {@link ClassCastException}.
     */
    public static <K, V> Map<K, V> copyMap(
            Map<?, ?> source, Class<K> targetKeyClass, Class<V> targetValueClass) throws ClassCastException {
        return Optional.ofNullable(source)
                .orElse(Collections.emptyMap())
                .entrySet()
                .stream()
                .collect(
                        Collectors.toMap(
                                entry -> {
                                    Object key = entry.getKey();
                                    if (BeanUtils.isSimpleProperty(targetKeyClass)) {
                                        return targetKeyClass.cast(key);
                                    }

                                    return copyProperties(key, targetKeyClass);
                                },
                                entry -> {
                                    Object value = entry.getValue();
                                    if (BeanUtils.isSimpleProperty(targetValueClass)) {
                                        return targetValueClass.cast(value);
                                    }

                                    return copyProperties(value, targetValueClass);
                                }));
    }

    public static <T, R> List<R> convertToObjectList(List<T> list, List<R> returnList, Function<T, R> function) {
        if (returnList == null) {
            returnList = new ArrayList<>();
        }
        if (list == null) {
            return returnList;
        }
        final List<R> finalReturnList = returnList;
        list.forEach(t -> finalReturnList.add(function.apply(t)));
        return returnList;
    }

    // / <summary>
    // / 将一个大数字符串从M进制转换成N进制
    // / </summary>
    // / <param name="sourceValue">M进制数字字符串</param>
    // / <param name="sourceBaseChars">M进制字符集</param>
    // / <param name="newBaseChars">N进制字符集</param>
    // / <returns>N进制数字字符串</returns>
    public static String baseConvert(String sourceValue,
                                     String sourceBaseChars, String newBaseChars) {

        // M进制
        int sBase = sourceBaseChars.length();
        // N进制
        int tBase = newBaseChars.length();
        char[] sourceBaseCharArray = sourceBaseChars.toCharArray();
        char[] newBaseCharArray = newBaseChars.toCharArray();
        // M进制数字字符串合法性判断（判断M进制数字字符串中是否有不包含在M进制字符集中的字符）
        for (char c : sourceValue.toCharArray()) {

            if (contains(sourceBaseCharArray, c)) {
                continue;
            } else {
                return null;
            }

        }

        // 将M进制数字字符串的每一位字符转为十进制数字依次存入到LIST中
        List<Integer> intSource = new ArrayList();// [sourceValue.length()];
        int i = 0;
        for (char c : sourceValue.toCharArray()) {
            intSource.add(sourceBaseChars.indexOf(c));
        }

        // 余数列表
        List<Integer> res = new ArrayList<Integer>();
        // 开始转换（判断十进制LIST是否为空或只剩一位且这个数字小于N进制）
        while (!((intSource.size() == 1 && intSource.get(0) < tBase) || intSource
                .size() == 0)) {
            // 每一轮的商值集合
            List<Integer> ans = new ArrayList<Integer>();
            int y = 0;
            // 十进制LIST中的数字逐一除以N进制（注意：需要加上上一位计算后的余数乘以M进制）
            for (int t : intSource) {
                // 当前位的数值加上上一位计算后的余数乘以M进制
                y = y * sBase + t;

                // 保存当前位与N进制的商值
                ans.add(y / tBase);

                // 计算余值
                y %= tBase;
            }

            // 将此轮的余数添加到余数列表
            res.add(y);

            // 将此轮的商值（去除0开头的数字）存入十进制LIST做为下一轮的被除数
            boolean flag = false;

            intSource.clear();

            for (int a : ans) {
                if (a == 0 && !flag) {
                    continue;
                }
                flag = true;
                intSource.add(a);
            }
        }

        // 如果十进制LIST还有数字，需将此数字添加到余数列表后
        if (intSource.size() > 0) {
            res.add(intSource.get(0));
        }

        // 将余数列表反转，并逐位转换为N进制字符
        StringBuffer nValue = new StringBuffer();
        for (int j = res.size() - 1; j >= 0; j--) {
            nValue.append(newBaseCharArray[res.get(j)]);
        }
        return nValue.toString();

    }

    private static boolean contains(char[] chars, char c) {
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == c) {
                return true;
            }
        }
        return false;
    }
}
