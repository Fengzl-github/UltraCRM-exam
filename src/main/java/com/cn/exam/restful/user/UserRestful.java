package com.cn.exam.restful.user;

import com.cn.exam.entity.user.User;
import com.cn.exam.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *@Author fengzhilong
 *@Date 2020/12/21 11:50
 *@Desc
 **/
@RestController
public class UserRestful {

    @Autowired
    private UserService userService;


    @GetMapping("/getUserList")
    public List<User> getUserList() {
        List<User> list = userService.findAllUser();
        return list;
    }
}
