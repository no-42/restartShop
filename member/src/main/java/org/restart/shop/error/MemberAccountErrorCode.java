package org.restart.shop.error;

import lombok.Getter;
import lombok.Setter;
import org.restart.shop.resp.BaseCodeEnum;

import java.util.Arrays;

@Getter
public enum MemberAccountErrorCode {
    NAME_IS_EMPTY("1000", "用户名为空"),
    PHONE_IS_EMPTY("1001", "手机号为空"),
    EMAIL_IS_EMPTY("1002", "邮箱为空"),
    PASSWORD_IS_EMPTY("1003", "密码为空"),
    PASSWORD_WRONG("1004", "密码错误"),
    MEMBER_NOT_EXITS("1005", "会员不存在");
    /**
     * 编码
     */
    private final String code;

    /**
     * 信息
     */
    private final String message;

    MemberAccountErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static MemberAccountErrorCode getReturnCodeEnum(String code) {
        return Arrays.stream(MemberAccountErrorCode.values()).filter(obj -> obj.getCode().equalsIgnoreCase(code)).findFirst().orElse(null);
    }

}
