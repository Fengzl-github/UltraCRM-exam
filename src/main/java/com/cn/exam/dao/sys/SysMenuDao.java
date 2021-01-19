package com.cn.exam.dao.sys;

import com.cn.exam.entity.sys.SysMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *@Author fengzhilong
 *@Date 2021/1/5 13:49
 *@Desc
 **/
@Repository
public interface SysMenuDao extends JpaRepository<SysMenu, Integer>, JpaSpecificationExecutor<SysMenu> {

    /*获取一级菜单*/
    @Query("select sm from SysMenu sm where sm.visible=1 and sm.pId= '0' and sm.level >= :level order by sm.menuSort asc ")
    List<SysMenu> getMenu1Data(@Param("level") Integer level);

    /*获取下级菜单*/
    @Query("select sm from SysMenu sm where sm.visible=1 and sm.pId= :pId and sm.level >= :level order by sm.menuSort asc ")
    List<SysMenu> getNextMenuData(@Param("pId") String pId, @Param("level") Integer level);
}
