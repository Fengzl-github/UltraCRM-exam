package com.cn.exam.entity.user;

import com.cn.common.jpa.BaseIntIdEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 *@Author fengzhilong
 *@Date 2020/12/21 10:59
 *@Desc
 **/
@Data
@Entity
@Table(name = "t_user")
@DynamicInsert //设置为true，表示insert对象的时候，生成动态的insert语句，如果字段为null时不加入insert语句中，默认为false
@DynamicUpdate //设置为true，表示update 。。。
public class User extends BaseIntIdEntity {

    @Column(columnDefinition = "varchar(30) NOT NULL COMMENT '工号'", unique = true)
    private String ghid;

    @Column(columnDefinition = "varchar(64) NOT NULL COMMENT '姓名'")
    private String name;

    @Column(columnDefinition = "varchar(64) NULL DEFAULT NULL COMMENT '昵称'")
    private String nickName;

    @Column(columnDefinition = "varchar(64) NOT NULL COMMENT '密码'")
    private String pass;

    @Column(columnDefinition = "varchar(15) NOT NULL COMMENT '手机号'")
    private String mobile;

    @Column(columnDefinition = "varchar(30) NULL DEFAULT NULL COMMENT '邮箱'")
    private String email;

    @Column(columnDefinition = "varchar(100) NULL DEFAULT NULL COMMENT '地址'")
    private String addr;

    @Column(columnDefinition = "int(3) NOT NULL DEFAULT 18 COMMENT '年龄'")
    private Integer age;

    @Column(columnDefinition = "tinyint(1) NULL DEFAULT 0 COMMENT '性别：0：男；1：女'")
    private Integer sex;

    @Column(columnDefinition = "tinyint(1) NULL DEFAULT 1 COMMENT '权限：1：最大权限；2：管理员；3：普通账号'")
    private Integer level;

    @Column(columnDefinition = "varchar(100) NULL DEFAULT NULL COMMENT '头像'")
    private String heardUrl;

    @Column(columnDefinition = "tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态：0：正常；1：删除'")
    private Integer isDel;

}
