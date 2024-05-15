package org.restart.shop.exception;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonAssert {
    /**
     * 判断map中指定key的值是否存在，并且不能为null，和空字符串
     *
     * @param map       map
     * @param key       key
     * @param errorCode 错误码
     * @param message   错误提示
     * @throws RsException 异常
     */
    public static void isNotNullOrEmptyInMap(Map map, String key,
                                             String errorCode, String message) throws RsException {
        Object value = map.get(key);
        if (null != value && !"".equals(value.toString())) {
        } else {
            throw new RsException(errorCode, message);
        }
    }

    /**
     * 判断map中指定key的值是否存在，并且不能为null，和空字符串
     *
     * @param map       map
     * @param key       key
     * @param errorCode 错误码
     * @throws RsException 异常
     */
    public static void isNotNullOrEmptyInMap(Map map, String key, String errorCode)
            throws RsException {
        isNotNullOrEmptyInMap(map, key, errorCode, "");
    }

    /**
     * 描述：判断不等于null或者空字符串;等于空就抛出异常
     *
     * @param obj       对象
     * @param errorCode 错误码
     * @param message   错误提示
     * @throws RsException 异常
     * @data 2015年12月26日 下午1:27:08
     * @author 杨建 yang.jian@mobcb.com
     */
    public static void isNotNullOrEmpty(Object obj, String errorCode,
                                        String message) throws RsException {
        if (obj != null) {
            if (obj instanceof String) {
                if ("".equals(obj)) {
                    throw new RsException(errorCode, message);
                }
            } else if (obj instanceof Collection) {
                if (((Collection) obj).isEmpty()) {
                    throw new RsException(errorCode, message);
                }
            } else if (obj instanceof Map) {
                if (((Map) obj).isEmpty()) {
                    throw new RsException(errorCode, message);
                }
            } else if (obj instanceof Object[]) {
                if (((Object[]) obj).length == 0) {
                    throw new RsException(errorCode, message);
                }
            }
        } else {
            throw new RsException(errorCode, message);
        }
    }

    /**
     * 描述：判断不等于null或者空字符串;等于空就抛出异常
     *
     * @param obj       对象
     * @param errorCode 错误码
     * @throws RsException 异常
     * @data 2015年12月26日 下午1:27:08
     * @author 杨建 yang.jian@mobcb.com
     */
    public static void isNotNullOrEmpty(Object obj, String errorCode)
            throws RsException {
        isNotNullOrEmpty(obj, errorCode, "");
    }


    /**
     * 描述：判断是不是整型，不是整型就抛出异常
     *
     * @param obj       对象
     * @param errorCode 错误码
     * @param message   错误提示
     * @return
     * @throws RsException 异常
     * @data 2016年2月25日 上午10:50:09
     * @author 薛泽文 xue.zewen@mobcb.com
     */
    public static int isInt(String obj, String errorCode, String message)
            throws RsException {
        try {
            int num = Integer.valueOf(obj);
            return num;
        } catch (NumberFormatException ex) {
            throw new RsException(errorCode, message, ex);
        }
    }

    /**
     * 描述：判断是不是整型，不是整型就抛出异常
     *
     * @param obj       对象
     * @param errorCode 错误码
     * @return
     * @throws RsException 异常
     * @data 2016年2月25日 上午10:50:09
     * @author 薛泽文 xue.zewen@mobcb.com
     */
    public static int isInt(String obj, String errorCode) throws RsException {
        return isInt(obj, errorCode, "");
    }

    /**
     * 描述：判断输入的是不是数字，不是就抛出异常
     *
     * @param obj       对象
     * @param errorCode 错误码
     * @param message   错误提示
     * @return
     * @throws RsException 异常
     * @data 2016年2月25日 上午10:50:52
     * @author 薛泽文 xue.zewen@mobcb.com
     */
    public static Double isNumber(String obj, String errorCode, String message)
            throws RsException {
        try {
            Double num = Double.valueOf(obj);
            return num;
        } catch (NumberFormatException ex) {
            throw new RsException(errorCode, message, ex);
        }
    }

    /**
     * 描述：判断输入的是不是数字，不是就抛出异常
     *
     * @param obj       对象
     * @param errorCode 错误码
     * @return
     * @throws RsException 异常
     * @data 2016年2月25日 上午10:50:52
     * @author 薛泽文 xue.zewen@mobcb.com
     */
    public static Double isNumber(String obj, String errorCode)
            throws RsException {
        return isNumber(obj, errorCode, "");
    }

    /**
     * 描述：判断是否为空，如果不为空，就抛出异常； 前提不能为null
     *
     * @param obj       不能为null
     * @param errorCode 错误码
     * @param message   错误提示
     * @throws RsException 异常
     * @data 2015年12月26日 下午1:56:54
     * @author 杨建 yang.jian@mobcb.com
     */
    public static void isEmpty(Object obj, String errorCode, String message)
            throws RsException {
        if (obj instanceof Object[]) {
            if (((Object[]) obj).length > 0) {
                throw new RsException(errorCode, message);
            }
        } else if (obj instanceof String) {
            if (!((String) obj).isEmpty()) {
                throw new RsException(errorCode, message);
            }
        } else if (obj instanceof Collection) {
            if (!((Collection) obj).isEmpty()) {
                throw new RsException(errorCode, message);
            }
        } else if (obj instanceof Map) {
            if (!((Map) obj).isEmpty()) {
                throw new RsException(errorCode, message);
            }
        }
    }

    /**
     * 描述：判断是否为空，如果不为空，就抛出异常； 前提不能为null
     *
     * @param obj       不能为null
     * @param errorCode 错误码
     * @throws RsException 异常
     * @data 2015年12月26日 下午1:56:54
     * @author 杨建 yang.jian@mobcb.com
     */
    public static void isEmpty(Object obj, String errorCode) throws RsException {
        isEmpty(obj, errorCode, "");
    }

    /**
     * 描述：判断lt是否小于rt,不小于就抛出异常
     *
     * @param lt        左值
     * @param rt        右值
     * @param errorCode 错误码
     * @param message   错误提示
     * @throws RsException 异常
     * @data 2015年12月26日 下午1:14:32
     * @author 杨建 yang.jian@mobcb.com
     */
    public static void lt(Integer lt, Integer rt, String errorCode, String message)
            throws RsException {
        if (null != lt && null != rt && lt < rt) {
        } else {
            throw new RsException(errorCode, message);
        }
    }

    /**
     * 描述：判断lt是否小于rt,不小于就抛出异常
     *
     * @param lt        左值
     * @param rt        右值
     * @param errorCode 错误码
     * @throws RsException 异常
     * @data 2015年12月26日 下午1:14:32
     * @author 杨建 yang.jian@mobcb.com
     */
    public static void lt(Integer lt, Integer rt, String errorCode)
            throws RsException {
        lt(lt, rt, errorCode, "");
    }

    /**
     * 描述：判断lt是否小于rt,不小于就抛出异常
     *
     * @param lt        左值
     * @param rt        右值
     * @param errorCode 错误码
     * @param message   错误提示
     * @throws RsException 异常
     * @data 2015年12月26日 下午1:14:32
     * @author 杨建 yang.jian@mobcb.com
     */
    public static void lt(Long lt, Long rt, String errorCode, String message)
            throws RsException {
        if (null != lt && null != rt && lt < rt) {
        } else {
            throw new RsException(errorCode, message);
        }
    }

    /**
     * 描述：判断lt是否小于等于rt,不小于就抛出异常
     *
     * @param lt        左值
     * @param rt        右值
     * @param errorCode 错误提示
     * @throws RsException 异常
     * @data 2015年12月26日 下午1:14:32
     * @author 杨建 yang.jian@mobcb.com
     */
    public static void le(Long lt, Long rt, String errorCode)
            throws RsException {
        if (null != lt && null != rt && lt <= rt) {
        } else {
            throw new RsException(errorCode, "");

        }
    }

    /**
     * 描述：判断lt是否小于rt,不小于就抛出异常
     *
     * @param lt        左值
     * @param rt        右值
     * @param errorCode 错误码
     * @throws RsException 异常
     * @data 2015年12月26日 下午1:14:32
     * @author 杨建 yang.jian@mobcb.com
     */
    public static void lt(Long lt, Long rt, String errorCode) throws RsException {
        lt(lt, rt, errorCode, "");
    }

    /**
     * 描述：判断lt是否大于rt,不大于就抛出异常
     *
     * @param lt        左值
     * @param rt        右值
     * @param errorCode 错误码
     * @param message   错误提示
     * @throws RsException 异常
     * @data 2015年12月26日 下午1:14:32
     * @author 杨建 yang.jian@mobcb.com
     */
    public static void gt(Integer lt, Integer rt, String errorCode, String message)
            throws RsException {
        if (null != lt && null != rt && lt > rt) {
        } else {
            throw new RsException(errorCode, message);
        }
    }

    /**
     * 描述：判断lt是否大于rt,不大于就抛出异常
     *
     * @param lt        左值
     * @param rt        右值
     * @param errorCode 错误码
     * @throws RsException 异常
     * @data 2015年12月26日 下午1:14:32
     * @author 杨建 yang.jian@mobcb.com
     */
    public static void gt(Integer lt, Integer rt, String errorCode)
            throws RsException {
        gt(lt, rt, errorCode, "");
    }


    /**
     * 描述：判断lt是否大于rt,不大于就抛出异常
     *
     * @param lt        左值
     * @param rt        右值
     * @param errorCode 错误码
     * @param message   错误提示
     * @throws RsException 异常
     * @data 2015年12月26日 下午1:14:32
     * @author 杨建 yang.jian@mobcb.com
     */
    public static void gt(Long lt, Long rt, String errorCode, String message)
            throws RsException {
        if (null != lt && null != rt && lt > rt) {
        } else {
            throw new RsException(errorCode, message);
        }
    }

    /**
     * 描述：判断lt是否大于rt,不大于就抛出异常
     *
     * @param lt        左值
     * @param rt        右值
     * @param errorCode 错误码
     * @throws RsException 异常
     * @data 2015年12月26日 下午1:14:32
     * @author 杨建 yang.jian@mobcb.com
     */
    public static void gt(Long lt, Long rt, String errorCode) throws RsException {
        gt(lt, rt, errorCode, "");
    }


    /**
     * 描述：是否相等，不等就抛出异常
     *
     * @param lt        左值
     * @param rt        右值
     * @param errorCode 错误码
     * @param message   错误提示
     * @throws RsException 异常
     * @data 2015年12月26日 下午1:14:32
     * @author 杨建 yang.jian@mobcb.com
     */
    public static void eq(Integer lt, Integer rt, String errorCode, String message)
            throws RsException {
        if (null != lt && lt.equals(rt)) {
        } else {
            throw new RsException(errorCode);
        }
    }

    /**
     * 描述：是否相等，不等就抛出异常
     *
     * @param lt        左值
     * @param rt        右值
     * @param errorCode 错误码
     * @throws RsException 异常
     * @data 2015年12月26日 下午1:14:32
     * @author 杨建 yang.jian@mobcb.com
     */
    public static void eq(Integer lt, Integer rt, String errorCode)
            throws RsException {
        eq(lt, rt, errorCode, "");
    }


    /**
     * 不等于断言，相等就抛出异常
     *
     * @param lt        左值
     * @param rt        右值
     * @param errorCode 错误码
     * @throws RsException 异常
     */
    public static void notEqual(Integer lt, Integer rt, String errorCode) {
        notEqual(lt, rt, errorCode, "");
    }

    public static void notEqual(Integer lt, Integer rt, String errorCode,
                                String message) {
        if (null != lt && null != rt && lt.equals(rt)) {
            throw new RsException(errorCode, message);
        }
    }


    public static void notEqual(String lt, String rt, String errorCode) {
        notEqual(lt, rt, errorCode, "");
    }

    public static void notEqual(String lt, String rt, String errorCode,
                                String message) {
        if (null != lt && null != rt && lt.equals(rt)) {
            throw new RsException(errorCode, message);
        }
    }

    /**
     * 描述：是否相等，不等就抛出异常
     *
     * @param lt        左值
     * @param rt        右值
     * @param errorCode 错误码
     * @param message   错误提示
     * @throws RsException 异常
     * @data 2015年12月26日 下午1:14:32
     * @author 杨建 yang.jian@mobcb.com
     */
    public static void eq(String lt, String rt, String errorCode, String message)
            throws RsException {
        if (null != lt && null != rt && lt.equals(rt)) {
        } else {
            throw new RsException(errorCode, message);
        }
    }

    /**
     * 描述：是否相等，不等就抛出异常
     *
     * @param lt        左值
     * @param rt        右值
     * @param errorCode 错误码
     * @throws RsException 异常
     * @data 2015年12月26日 下午1:14:32
     * @author 杨建 yang.jian@mobcb.com
     */
    public static void eq(String lt, String rt, String errorCode)
            throws RsException {
        eq(lt, rt, errorCode, "");
    }


    /**
     * 描述：下标
     *
     * @param array 数组
     * @param item  对象
     * @return
     * @data 2015年12月26日 下午1:05:23
     * @author 杨建 yang.jian@mobcb.com
     */
    private static int indexOf(Object[] array, Object item) {
        int index = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(item)) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * 描述：判断元素必须在数组中
     *
     * @param item      对象
     * @param array     数组
     * @param errorCode 错误码
     * @param message   错误提示 如果不存在，就提示的消息
     * @throws RsException 异常
     * @data 2015年12月26日 下午1:03:33
     * @author 杨建 yang.jian@mobcb.com
     */
    public static void inArray(Object item, Object[] array, String errorCode,
                               String message) throws RsException {
        if (null != item && null != array && indexOf(array, item) != -1) {

        } else {
            throw new RsException(errorCode, message);

        }
    }

    /**
     * 描述：判断元素必须在数组中
     *
     * @param item      对象
     * @param array     数组
     * @param errorCode 错误码
     * @throws RsException 异常
     * @data 2015年12月26日 下午1:03:33
     * @author 杨建 yang.jian@mobcb.com
     */
    public static void inArray(Object item, Object[] array, String errorCode)
            throws RsException {
        inArray(item, array, errorCode, "");
    }


    /**
     * 等于null不抛出异常，不等与null抛出异常
     *
     * @param item      对象
     * @param errorCode 错误码
     * @throws RsException 异常
     */
    public static void isNull(Object item, String errorCode) throws RsException {
        isNull(item, errorCode, null);
    }

    public static void isNull(Object item, String errorCode, String message) throws RsException {
        if (null == item) {
        } else {
            throw new RsException(errorCode, message);
        }
    }

    /**
     * 判断是等于true
     *
     * @param item      对象
     * @param message   错误提示
     * @param errorCode 错误码
     * @throws RsException 异常
     */
    public static void isTrue(boolean item, String errorCode, String message)
            throws RsException {
        if (!item) {
            throw new RsException(errorCode, message);
        }
    }

    public static void isTrue(boolean item, String errorCode)
            throws RsException {
        isTrue(item, errorCode, null);
    }

    /**
     * 判断是等于false
     *
     * @param item      对象
     * @param message   错误提示
     * @param errorCode 错误码
     * @throws RsException 异常
     */
    public static void isFalse(boolean item, String errorCode, String message)
            throws RsException {
        if (!item) {
            throw new RsException(errorCode, message);
        }
    }

    public static void isFalse(boolean item, String errorCode)
            throws RsException {
        isFalse(item, errorCode, null);
    }

    /**
     * 描述：是否相等，不等就抛出异常
     *
     * @param lt        左值
     * @param rt        右值
     * @param errorCode 错误码
     * @param message   错误提示
     * @throws RsException 异常
     * @author ypp
     */
    public static void eq(Long lt, Long rt, String errorCode, String message)
            throws RsException {
        if (null != lt && null != rt && lt.equals(rt)) {
        } else {
            throw new RsException(errorCode, message);
        }
    }

    public static void eq(Long lt, Long rt, String errorCode)
            throws RsException {
        eq(lt, rt, errorCode, "");
    }

    /**
     * 手机格式验证
     *
     * @param mobile    手机号
     * @param errorCode 错误码
     * @throws RsException 异常
     */
    public static void isMobile(String mobile, String errorCode) throws RsException {
        Pattern p = Pattern.compile("^134[0-8]\\d{7}$|^13[^4]\\d{8}$|^14[5-9]\\d{8}$|^15[^4]\\d{8}$|^16[6]\\d{8}$|^17[0-8]\\d{8}$|^18[\\d]{9}$|^19[8,9]\\d{8}$");//验证手机号
        Matcher m = p.matcher(mobile);
        if (!m.matches()) {
            throw new RsException(errorCode, "");
        }
    }
}
