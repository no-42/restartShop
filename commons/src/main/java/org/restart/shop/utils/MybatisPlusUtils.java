package org.restart.shop.utils;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.micrometer.common.util.StringUtils;
import org.restart.shop.annotation.QueryField;
import org.restart.shop.core.doamin.BaseEntity;
import org.restart.shop.core.doamin.BaseQuery;

import java.lang.reflect.Field;
import java.util.*;

/**
 * mybatis工具
 *
 * @author bowen.tao
 */
public class MybatisPlusUtils {

    public static <T extends BaseEntity, V extends BaseQuery> QueryWrapper<T> buildQueryWrapper(V query) {
        List<Field> fields = getAllFields(query.getClass());
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        for (Field field : fields) {
            QueryField queryField = field.getAnnotation(QueryField.class);
            if (queryField == null) {
                continue;
            }
            if (queryField.condition() != QueryField.ActiveCondition.ALL) {
                // 判断是否满足查询条件
                Object value = getFieldValue(field, query);
                if (queryField.condition() == QueryField.ActiveCondition.NOT_NULL) {
                    if (value == null) {
                        continue;
                    }
                } else if (queryField.condition() == QueryField.ActiveCondition.NOT_EMPTY) {
                    if (value == null) {
                        continue;
                    }
                    if (value instanceof String && StringUtils.isEmpty((String) value)) {
                        continue;
                    }
                    if (value instanceof Collection && ((Collection<?>) value).isEmpty()) {
                        continue;
                    }
                }
            }
            switch (queryField.type()) {
                case EQ:
                    eq(wrapper, queryField, field, query);
                    break;
                case NEQ:
                    neq(wrapper, queryField, field, query);
                    break;
                case GT:
                    gt(wrapper, queryField, field, query);
                    break;
                case LT:
                    lt(wrapper, queryField, field, query);
                    break;
                case LIKE:
                    like(wrapper, queryField, field, query);
                    break;
                case GE:
                    ge(wrapper, queryField, field, query);
                    break;
                case LE:
                    le(wrapper, queryField, field, query);
                    break;
                case IN:
                    in(wrapper, queryField, field, query);
                    break;
                case NIN:
                    notIn(wrapper, queryField, field, query);
                    break;
                default:
                    throw new RuntimeException("数据查询错误，未知的查询类型");
            }
        }
        return wrapper;
    }

    private static <T> T getFieldValue(Field field, Object obj) {
        field.setAccessible(true);
        try {
            return (T) field.get(obj);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("获取 ’" + field.getName() + "‘的值异常", e);
        }
    }

    private static <V extends BaseQuery> void eq(QueryWrapper<?> wrapper, QueryField queryField, Field field, V query) {
        wrapper.eq(
                StrUtil.isNotEmpty(queryField.column()) ? queryField.column() : StrUtil.toUnderlineCase(field.getName()),
                getFieldValue(field, query));
    }

    private static <V extends BaseQuery> void neq(QueryWrapper<?> wrapper, QueryField queryField, Field field,
                                                  V query) {
        wrapper.ne(
                StrUtil.isNotEmpty(queryField.column()) ? queryField.column() : StrUtil.toUnderlineCase(field.getName()),
                getFieldValue(field, query));
    }

    private static <V extends BaseQuery> void gt(QueryWrapper<?> wrapper, QueryField queryField, Field field, V query) {
        wrapper.gt(
                StrUtil.isNotEmpty(queryField.column()) ? queryField.column() : StrUtil.toUnderlineCase(field.getName()),
                getFieldValue(field, query));
    }

    private static <V extends BaseQuery> void lt(QueryWrapper<?> wrapper, QueryField queryField, Field field, V query) {
        wrapper.lt(
                StrUtil.isNotEmpty(queryField.column()) ? queryField.column() : StrUtil.toUnderlineCase(field.getName()),
                getFieldValue(field, query));
    }

    private static <V extends BaseQuery> void like(QueryWrapper<?> wrapper, QueryField queryField, Field field,
                                                   V query) {
        wrapper.like(
                StrUtil.isNotEmpty(queryField.column()) ? queryField.column() : StrUtil.toUnderlineCase(field.getName()),
                getFieldValue(field, query));
    }

    private static <V extends BaseQuery> void ge(QueryWrapper<?> wrapper, QueryField queryField, Field field, V query) {
        wrapper.ge(
                StrUtil.isNotEmpty(queryField.column()) ? queryField.column() : StrUtil.toUnderlineCase(field.getName()),
                getFieldValue(field, query));
    }

    private static <V extends BaseQuery> void le(QueryWrapper<?> wrapper, QueryField queryField, Field field, V query) {
        wrapper.le(
                StrUtil.isNotEmpty(queryField.column()) ? queryField.column() : StrUtil.toUnderlineCase(field.getName()),
                getFieldValue(field, query));
    }

    private static <V extends BaseQuery> void in(QueryWrapper<?> wrapper, QueryField queryField, Field field, V query) {
        if (Collection.class.isAssignableFrom(field.getType())) {
            Collection<?> collection = getFieldValue(field, query);
            wrapper.in(StrUtil.isNotEmpty(queryField.column()) ? queryField.column()
                    : StrUtil.toUnderlineCase(field.getName()), collection);
        } else if (CharSequence.class.isAssignableFrom(field.getType())) {
            String s = getFieldValue(field, query);
            List<String> list = Arrays.asList(s.split(","));
            wrapper.in(StrUtil.isNotEmpty(queryField.column()) ? queryField.column()
                    : StrUtil.toUnderlineCase(field.getName()), list);
        } else {
            throw new RuntimeException(field.getName() + " 错误的数据类型,使用in必须为Collection或者CharSequence的子类");
        }
    }

    private static <V extends BaseQuery> void notIn(QueryWrapper<?> wrapper, QueryField queryField, Field field,
                                                    V query) {
        if (Collection.class.isAssignableFrom(field.getType())) {
            Collection<?> collection = getFieldValue(field, query);
            wrapper.notIn(StrUtil.isNotEmpty(queryField.column()) ? queryField.column()
                    : StrUtil.toUnderlineCase(field.getName()), collection);
        } else if (CharSequence.class.isAssignableFrom(field.getType())) {
            String s = getFieldValue(field, query);
            List<String> list = Arrays.asList(s.split(","));
            wrapper.notIn(StrUtil.isNotEmpty(queryField.column()) ? queryField.column()
                    : StrUtil.toUnderlineCase(field.getName()), list);
        } else {
            throw new RuntimeException(field.getName() + " 错误的数据类型,使用notIn必须为Collection或者CharSequence的子类");
        }
    }

    private static final Map<Class<?>, List<Field>> CACHE = new Hashtable<>();

    private static List<Field> getAllFields(Class<?> query) {
        // 生产环境缓存数据
        if (CACHE.containsKey(query)) {
            return CACHE.get(query);
        }
        Class<?> superClass = query;
        List<Field> queryFields = new ArrayList<>();
        while (superClass != Object.class) {
            queryFields.addAll(Arrays.asList(superClass.getDeclaredFields()));
            superClass = superClass.getSuperclass();
        }
        CACHE.put(query, queryFields);
        return queryFields;
    }
}
