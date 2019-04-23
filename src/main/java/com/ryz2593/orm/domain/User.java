package com.ryz2593.orm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ryz2593.orm.jpa.Column;
import org.ryz2593.orm.jpa.Table;

import java.util.Date;

/**
 * @author ryz2593
 * @date 2019/4/23
 * @desc
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("t_user")
public class User {
    @Column("user_id")
    private Integer id;
    @Column("user_name")
    private String name;
    @Column("user_age")
    private Integer age;
    @Column("user_birth")
    private Date birth;

}
