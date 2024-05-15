package org.restart.shop.utils;

import java.util.UUID;

public class OpenIdUtil {
    /**
     * 通过uuid生成openId
     *
     * @return
     */
    public static String generateOpenIdByUUID() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        return uuid;
    }

    /**
     * 生成对象Id
     *
     * @return 对象Id
     */
    public static String generateObjectId() {
        return ObjectId.shortObjectId();
    }
}
