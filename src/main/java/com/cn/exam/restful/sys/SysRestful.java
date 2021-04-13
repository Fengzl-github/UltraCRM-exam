package com.cn.exam.restful.sys;

import com.cn.common.vo.ResResult;
import com.cn.exam.service.sys.SysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *@Author fengzhilong
 *@Date 2021/1/5 13:38
 *@Desc
 **/
@Slf4j
@RestController
@RequestMapping("/sys")
public class SysRestful {

    @Autowired
    private SysMenuService sysMenuService;


    /**
     * @Author fengzhilong
     * @Desc 获取系统菜单
     * @Date 2021/1/5 14:04
     * @param level 账号权限
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/getSysMenuData")
    public ResResult getSysMenuData(Integer level) {

        return sysMenuService.getSysMenuData(level);
    }


}
