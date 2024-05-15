package org.restart.shop.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.restart.shop.domain.query.MemberAccountQuery;
import org.restart.shop.error.MemberAccountErrorCode;
import org.restart.shop.exception.CommonAssert;
import org.restart.shop.resp.ResultData;
import org.restart.shop.service.MemberAccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/member")
public class MemberAccountCtrl {

    @Resource
    private MemberAccountService memberAccountService;

    @GetMapping(value = "get/account")
    public Object getMemberAccountListByQuery(MemberAccountQuery query) {
        return ResultData.success(memberAccountService.getMemberAccountListByQuery(query));
    }

    @PostMapping(value = "/add")
    public Object addMemberAccount(@RequestBody MemberAccountQuery account) {
        CommonAssert.isNotNullOrEmpty(account.getPassword(), MemberAccountErrorCode.PASSWORD_IS_EMPTY.getCode(), MemberAccountErrorCode.PASSWORD_IS_EMPTY.getMessage());
        return ResultData.success(memberAccountService.insertMemberAccount(account));
    }
}
