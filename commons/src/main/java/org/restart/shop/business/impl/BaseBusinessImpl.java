package org.restart.shop.business.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.restart.shop.business.BaseBusiness;
import org.restart.shop.core.doamin.*;
import org.restart.shop.utils.OpenIdUtil;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Slf4j
public class BaseBusinessImpl<T extends BaseEntity> implements BaseBusiness<T> {

    protected BaseMapper<T> baseMapper;

    public BaseMapper<T> getBaseMapper() {
        return baseMapper;
    }

    public void setBaseMapper(BaseMapper<T> baseMapper) {
        this.baseMapper = baseMapper;
    }


    @Override
    public int insert(T entity) {
        if (entity instanceof IdEntity idEntity) {
            if (StringUtils.isEmpty(idEntity.getId())) {
                idEntity.setId(OpenIdUtil.generateObjectId());
            }
        }
        if (entity instanceof DateEntity dateEntity) {
            if (dateEntity.getCreateTime() == null) {
                dateEntity.setCreateTime(System.currentTimeMillis() / 1000);
            }
            if (dateEntity.getModifyTime() == null) {
                dateEntity.setModifyTime(System.currentTimeMillis() / 1000);
            }
        }
        return baseMapper.insert(entity);
    }

    @Override
    public T selectOneByExample(Example example) {
        return baseMapper.selectOneByExample(example);
    }

    @Override
    public List<T> selectListByQuery(BaseQuery query) {
        return baseMapper.selectListByQuery(query);
    }

    @Override
    public T selectOneByQuery(BaseQuery query) {
        return baseMapper.selectOneByQuery(query);
    }
}
