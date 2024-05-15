package org.restart.shop.annotation;

import java.lang.annotation.*;

/**
 * 用在BaseQuery的字段上，声明这个字段为一个查询字段，后续调用 BaseMapper的selectListByQuery会自动生成查询sql 使用此注解声明当前字段为一个查询字段
 * 
 * @author bowen.tao
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface QueryField {

    /**
     * 当前查询字段对应数据库，默认取字段名称
     */
    String column() default "";

    /**
     * 当前字段对比类型
     */
    CompareType type() default CompareType.EQ;

    /**
     * 触发查询的条件
     */
    ActiveCondition condition() default ActiveCondition.NOT_EMPTY;

    /**
     * sql拼接的判断条件
     */
    enum CompareType {
        /**
         * EQ=相等,NEQ=不相等,GT=大于,LT=小于,LIKE=模糊查询,GE=大于等于,LE=小于等于,IN=in(字段需要为集合类型),NIN=not in
         */
        EQ, NEQ, GT, LT, LIKE, GE, LE, IN, NIN
    }

    /**
     * 当前字段的值为什么条件时添加where查询
     */
    enum ActiveCondition {
        /**
         * ALL=无论什么情况,NOT_EMPTY=值不为空的情况(字符或集合),NOT_NULL=值不为空的情况
         */
        ALL, NOT_EMPTY, NOT_NULL
    }
}
