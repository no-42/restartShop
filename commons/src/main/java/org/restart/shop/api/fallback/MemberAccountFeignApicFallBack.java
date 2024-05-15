package org.restart.shop.api.fallback;

import org.restart.shop.api.MemberAccountFeignApi;
import org.restart.shop.entities.MemberAccountDTO;
import org.restart.shop.resp.ResultData;
import org.springframework.stereotype.Component;

@Component
public class MemberAccountFeignApicFallBack implements MemberAccountFeignApi {
    @Override
    public ResultData getMemberAccountById(String id) {
        return null;
    }

    @Override
    public ResultData addMemberAccount(MemberAccountDTO accountDTO) {
        return null;
    }
}
