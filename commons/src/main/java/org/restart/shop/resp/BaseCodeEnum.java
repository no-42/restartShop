package org.restart.shop.resp;


import lombok.Getter;

import java.util.Arrays;

/**
 * 返回code枚举类
 */
@Getter
public enum BaseCodeEnum {
    SUCCESS("0", "成功");
    /**
     * 编码
     */
    private final String code;

    /**
     * 信息
     */
    private final String message;

    BaseCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static BaseCodeEnum getReturnCodeEnum(String code) {
        return Arrays.stream(BaseCodeEnum.values()).filter(obj -> obj.getCode().equalsIgnoreCase(code)).findFirst().orElse(null);
    }
}
