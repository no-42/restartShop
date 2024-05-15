package org.restart.shop.business.impl;

import org.restart.shop.business.MemberAccountBusiness;
import org.restart.shop.domain.entities.MemberAccount;
import org.restart.shop.mapper.MemberAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberAccountBusinessImpl extends BaseBusinessImpl<MemberAccount> implements MemberAccountBusiness  {

    @SuppressWarnings("unused")
    private MemberAccountMapper accountMapper;

    @Autowired
    public void setBaseMapper(MemberAccountMapper mapper) {
        super.setBaseMapper(mapper);
        this.accountMapper = mapper;
    }
}
