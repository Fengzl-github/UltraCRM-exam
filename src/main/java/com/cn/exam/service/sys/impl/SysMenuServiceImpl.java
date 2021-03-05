package com.cn.exam.service.sys.impl;

import com.alibaba.fastjson.JSONArray;
import com.cn.common.vo.ResCode;
import com.cn.common.vo.ResResult;
import com.cn.exam.dao.sys.SysMenuDao;
import com.cn.exam.entity.sys.SysMenu;
import com.cn.exam.service.sys.SysMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *@Author fengzhilong
 *@Date 2021/1/5 13:59
 *@Desc
 **/
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Resource
    private SysMenuDao sysMenuDao;

    /**
     * 获取系统菜单
     * @param level 账号权限 1-全部，2-管理员 3-普通账号
     **/
    @Override
    public ResResult getSysMenuData(Integer level) {

        List<SysMenu> sysMenus1 = sysMenuDao.getMenu1Data(level);
        StringBuilder sb = getJsonListFormat(sysMenus1, level);

        System.out.println(sb);
        JSONArray json = JSONArray.parseArray(sb.toString());

        return ResCode.OK.msg("获取成功")
                .putData("content", json);
    }


    /**
     * @Desc 递归生成菜单数据
     * @param menuData
     * @param level
     **/
    public StringBuilder getJsonListFormat(List<SysMenu> menuData, Integer level) {

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        SysMenu menu = null;
        String menuId = "";
        for (int i = 0; i < menuData.size(); i++) {
            menu = menuData.get(i);
            menuId = menu.getMId();

            if (i > 0) sb.append(",");
            sb.append("{");
            sb.append("\"id\": \"" + menuId + "\", ");
            sb.append("\"title\": \"" + menu.getTitle() + "\", ");
            sb.append("\"icon\": \"" + menu.getIcon() + "\", ");
            sb.append("\"url\": \"" + menu.getUrl() + "\", ");
            //是否有下一级菜单
            List<SysMenu> menuByPIdData = sysMenuDao.getNextMenuData(menuId, level);
            if (menuByPIdData.size() > 0) {
                sb.append("\"children\": " + getJsonListFormat(menuByPIdData, level));
            }

            sb.append("}");
        }

        sb.append("]");
        return sb;
    }

}
