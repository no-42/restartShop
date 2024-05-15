package org.restart.shop.business;

import org.restart.shop.core.doamin.BaseQuery;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public interface BaseBusiness<T> {
    int insert(T entity);

    T selectOneByExample(Example example);

    List<T> selectListByQuery(BaseQuery query);

    T selectOneByQuery(BaseQuery query);
}
