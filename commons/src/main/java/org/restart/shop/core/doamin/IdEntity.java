package org.restart.shop.core.doamin;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@ToString
public class IdEntity extends DateEntity {

    /**
     * 用户id
     * -- GETTER --
     * 获取用户id
     * -- SETTER --
     * 设置用户id
     *
     * @return id - 用户id
     * @param id 用户id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private String id;
}
