package org.restart.shop.api;

import org.restart.shop.api.fallback.MemberAccountFeignApicFallBack;
import org.restart.shop.entities.MemberAccountDTO;
import org.restart.shop.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "cloud-gateway",path = "api_v2",fallback = MemberAccountFeignApicFallBack.class)
public interface MemberAccountFeignApi {
    @GetMapping(value = "/get/member/account/by/id/{id}")
    ResultData getMemberAccountById(@PathVariable("id") String id);

    @PostMapping(value = "member/account/add")
    ResultData addMemberAccount(@RequestBody MemberAccountDTO accountDTO);
}
