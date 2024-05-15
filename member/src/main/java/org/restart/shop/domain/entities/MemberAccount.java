package org.restart.shop.domain.entities;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import org.restart.shop.core.doamin.BaseEntity;
import org.restart.shop.core.doamin.DateEntity;
import org.restart.shop.core.doamin.IdEntity;

import javax.persistence.*;

/**
 * 表名：account
 */
@Setter
@Getter
@TableName(value = "account",schema = "member")
public class MemberAccount extends IdEntity {

    /**
     * 用户真实姓名
     * -- GETTER --
     * 获取用户真实姓名
     * -- SETTER --
     * 设置用户真实姓名
     *
     * @return name - 用户真实姓名
     * @param name 用户真实姓名
     */
    private String name;

    /**
     * 用户昵称
     * -- GETTER --
     * 获取用户昵称
     * -- SETTER --
     * 设置用户昵称
     *
     * @return nickName - 用户昵称
     * @param nickName 用户昵称
     */
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 用户手机号
     * -- GETTER --
     * 获取用户手机号
     * -- SETTER --
     * 设置用户手机号
     *
     * @return phone - 用户手机号
     * @param phone 用户手机号
     */
    private String phone;

    /**
     * 用户密码
     * -- GETTER --
     * 获取用户密码
     * -- SETTER --
     * 设置用户密码
     *
     * @return password - 用户密码
     * @param password 用户密码
     */
    private String password;

    /**
     * 用户邮箱
     */
    private String email;

}
