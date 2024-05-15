package org.restart.shop.service.impl;

import jakarta.annotation.Resource;
import org.restart.shop.business.MemberAccountBusiness;
import org.restart.shop.domain.entities.MemberAccount;
import org.restart.shop.domain.query.MemberAccountQuery;
import org.restart.shop.mapper.MemberAccountMapper;
import org.restart.shop.service.MemberAccountService;
import org.restart.shop.utils.BaseConverter;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class MemberAccountServiceImpl implements MemberAccountService {

    @Resource
    private MemberAccountBusiness memberAccountBusiness;

    @Override
    public MemberAccount getMemberAccountById(String id) {
        Example example = new Example(MemberAccount.class);
        example.createCriteria().andEqualTo("id", id);
        return memberAccountBusiness.selectOneByExample(example);
    }

    @Override
    public int insertMemberAccount(MemberAccountQuery memberAccount) {
        return memberAccountBusiness.insert(BaseConverter.doToResp(memberAccount, MemberAccount.class));
    }

    @Override
    public List<MemberAccount> getMemberAccountListByQuery(MemberAccountQuery query) {
        return memberAccountBusiness.selectListByQuery(query);
    }

    @Override
    public MemberAccount getMemberAccountByQuery(MemberAccountQuery accountQuery) {
        return memberAccountBusiness.selectOneByQuery(accountQuery);
    }
}
