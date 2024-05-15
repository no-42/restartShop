package org.restart.shop.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberAccountDTO implements Serializable {
    private String id;

    /**
     * 用户真实姓名
     */
    private String name;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 用户手机号
     */
    private String phone;
    /**
     * 用户密码
     */
    private String password;
}
