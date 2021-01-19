package com.cn.exam.service.sys;

import com.cn.common.vo.ResResult;
import org.springframework.validation.annotation.Validated;

/**
 *@Author fengzhilong
 *@Date 2021/1/5 13:40
 *@Desc
 **/
@Validated
public interface SysMenuService {

    /**
     * 获取系统菜单
     * @param level
     **/
    ResResult getSysMenuData(Integer level);
}
