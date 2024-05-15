package org.restart.shop.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.restart.shop.domain.entities.MemberAccount;
import org.restart.shop.domain.query.MemberAccountQuery;
import org.restart.shop.error.MemberAccountErrorCode;
import org.restart.shop.exception.CommonAssert;
import org.restart.shop.exception.RsException;
import org.restart.shop.resp.ResultData;
import org.restart.shop.service.MemberAccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LoginRegisterCtrl {
    @Resource
    private MemberAccountService memberAccountService;


    @GetMapping("/login")
    public Object login(MemberAccountQuery accountQuery) {
        CommonAssert.isNotNullOrEmpty(accountQuery.getName(), MemberAccountErrorCode.NAME_IS_EMPTY.getCode(), MemberAccountErrorCode.NAME_IS_EMPTY.getMessage());
        CommonAssert.isNotNullOrEmpty(accountQuery.getPassword(), MemberAccountErrorCode.PASSWORD_IS_EMPTY.getCode(), MemberAccountErrorCode.PASSWORD_IS_EMPTY.getMessage());
//        if ("phone".equalsIgnoreCase(accountQuery.getLoginType())) {
//            CommonAssert.isNotNullOrEmpty(accountQuery.getPhone(), MemberAccountErrorCode.PHONE_IS_EMPTY.getCode(), MemberAccountErrorCode.PHONE_IS_EMPTY.getMessage());
//        } else {
//            CommonAssert.isNotNullOrEmpty(accountQuery.getPhone(), MemberAccountErrorCode.EMAIL_IS_EMPTY.getCode(), MemberAccountErrorCode.EMAIL_IS_EMPTY.getMessage());
//        }
        MemberAccount memberAccount = memberAccountService.getMemberAccountByQuery(accountQuery);
        if (memberAccount == null) {
            throw new RsException(MemberAccountErrorCode.MEMBER_NOT_EXITS.getCode(),"账号或密码错误");
        }
        return ResultData.success(memberAccount);
    }


}
