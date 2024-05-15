package org.restart.shop.service;

import org.restart.shop.domain.entities.MemberAccount;
import org.restart.shop.domain.query.MemberAccountQuery;

import java.util.List;

public interface MemberAccountService {
    /**
     * 根据id获取会员账户信息
     *
     * @param id
     * @return
     */
    MemberAccount getMemberAccountById(String id);

    int insertMemberAccount(MemberAccountQuery memberAccount);

    /**
     * 查询会员列表
     * @param query
     * @return
     */
    List<MemberAccount> getMemberAccountListByQuery(MemberAccountQuery query);

    /**
     * 查询单个会员信息
     * @param accountQuery
     * @return
     */
    MemberAccount getMemberAccountByQuery(MemberAccountQuery accountQuery);
}
