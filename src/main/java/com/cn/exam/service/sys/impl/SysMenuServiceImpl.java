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
        JSONArray json = getJsonListFormat(sysMenus1, level);

        System.out.println(json);

        return ResCode.OK.msg("获取成功")
                .putData("content", json);
    }


    /**
     * @Desc 转换成json数组格式
     * @param menuData
     * @param level
     **/
    public JSONArray getJsonListFormat(List<SysMenu> menuData, Integer level) {

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
            String strMenu2 = "";
            if (menuByPIdData != null) {
                strMenu2 = getJsonFormatMenu2(menuByPIdData);
            }
            sb.append("\"children\": [" + strMenu2 + "]");

            sb.append("}");
        }

        sb.append("]");
        JSONArray jsonArray = JSONArray.parseArray(sb.toString());
        return jsonArray;
    }


    /**
     * @Desc 获取2级菜单
     * @param menu2Data
     **/
    public String getJsonFormatMenu2(List<SysMenu> menu2Data) {
        StringBuilder sb = new StringBuilder();
        SysMenu menu = null;
        String menuId = "";

        for (int i = 0; i < menu2Data.size(); i++) {
            menu = menu2Data.get(i);
            menuId = menu.getMId();

            if (i > 0) sb.append(",");
            sb.append("{");
            sb.append("\"id\": \"" + menuId + "\", ");
            sb.append("\"title\": \"" + menu.getTitle() + "\", ");
            sb.append("\"icon\": \"" + menu.getIcon() + "\", ");
            sb.append("\"url\": \"" + menu.getUrl() + "\"");


            sb.append("}");
        }

        return sb.toString();
    }
}
