package org.restart.shop.core.doamin;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.restart.shop.utils.MybatisPlusUtils;
import tk.mybatis.mapper.common.Mapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface BaseMapper<T extends BaseEntity> extends com.baomidou.mybatisplus.core.mapper.BaseMapper<T>,Mapper<T> {

    /**
     * 根据查询条件查询列表是, 查询条件带有 {@link org.restart.shop.annotation.QueryField} 的注解可以自动生成查询字段
     *
     * @param query 查询条件
     * @return 列表
     */
    default List<T> selectListByQuery(BaseQuery query) {
        if (query == null) {
            throw new RuntimeException("查询条件不能为空");
        } else {
            return selectList(MybatisPlusUtils.buildQueryWrapper(query));
        }
    }

    default Long selectCountByQuery(BaseQuery query) {
        if (query == null) {
            throw new RuntimeException("查询条件不能为空");
        } else {
            return selectCount(MybatisPlusUtils.buildQueryWrapper(query));
        }
    }

    /**
     * 根据查询条件查询一条数据
     *
     * @param query 查询条件
     * @return 结果
     */
    default T selectOneByQuery(BaseQuery query) {
        if (query == null) {
            throw new RuntimeException("查询条件不能为空");
        } else {
            return selectOne(MybatisPlusUtils.buildQueryWrapper(query));
        }
    }

    @Override
    T selectById(Serializable id);

    @Override
    List<T> selectBatchIds(@Param(Constants.COLL) Collection<? extends Serializable> idList);

    @Override
    List<T> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

    @Override
    default T selectOne(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper) {
        return com.baomidou.mybatisplus.core.mapper.BaseMapper.super.selectOne(queryWrapper);
    }

    @Override
    default boolean exists(Wrapper<T> queryWrapper) {
        return com.baomidou.mybatisplus.core.mapper.BaseMapper.super.exists(queryWrapper);
    }

    @Override
    Long selectCount(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    @Override
    List<T> selectList(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    @Override
    List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    @Override
    List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    @Override
    <P extends IPage<T>> P selectPage(P page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    @Override
    <P extends IPage<Map<String, Object>>> P selectMapsPage(P page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    @Override
    int insert(T entity);
}
