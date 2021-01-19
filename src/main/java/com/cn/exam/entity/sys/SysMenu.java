package com.cn.exam.entity.sys;

import com.cn.common.jpa.BaseIntIdEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *@Author fengzhilong
 *@Date 2021/1/5 13:43
 *@Desc 系统菜单
 **/
@Entity
@Data
@Table(name = "crm_menu_bs")
@DynamicInsert
@DynamicUpdate
public class SysMenu extends BaseIntIdEntity {

    @Column(columnDefinition = "varchar(10) DEFAULT 0 COMMENT '当前id'")
    private String mId;

    @Column(columnDefinition = "varchar(50) NOT NULL COMMENT '菜单名'")
    private String title;

    @Column(columnDefinition = "varchar(80) DEFAULT '' COMMENT '连接地址'")
    private String url;

    @Column(columnDefinition = "varchar(50) DEFAULT '' COMMENT '图标'")
    private String icon;

    @Column(columnDefinition = "varchar(10) DEFAULT 0 COMMENT '上级id'")
    private String pId;

    @Column(columnDefinition = "int(4) UNSIGNED DEFAULT 3 COMMENT '等级：1 全部展示 2 管理员账号 3 普通账号展示菜单'")
    private Integer level;

    @Column(columnDefinition = "int(4) UNSIGNED DEFAULT 1 COMMENT '是否可见：0 隐藏，1 可见'")
    private Integer visible;

    @Column(columnDefinition = "int(4) UNSIGNED DEFAULT 0 COMMENT '排序'")
    private Integer menuSort;
}
