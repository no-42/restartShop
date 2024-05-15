package org.restart.shop.domain.query;

import lombok.Getter;
import lombok.Setter;
import org.restart.shop.annotation.QueryField;
import org.restart.shop.core.doamin.BaseQuery;

@Setter
@Getter
public class MemberAccountQuery extends BaseQuery {

    @QueryField(type = QueryField.CompareType.EQ)
    private String id;

    /**
     * 登录类型：phone：手机号登录；email：邮箱登录
     */
    private String loginType;
    @QueryField(type = QueryField.CompareType.EQ)
    private String name;
    @QueryField(type = QueryField.CompareType.EQ)
    private String email;
    @QueryField(type = QueryField.CompareType.EQ)
    private String phone;
    @QueryField(type = QueryField.CompareType.EQ)
    private String password;
}
