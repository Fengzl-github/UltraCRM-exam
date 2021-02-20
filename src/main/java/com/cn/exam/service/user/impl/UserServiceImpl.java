package com.cn.exam.service.user.impl;

import com.cn.common.jpa.util.JpaUtil;
import com.cn.exam.dao.user.UserDao;
import com.cn.exam.dto.login.LoginDTO;
import com.cn.exam.entity.user.User;
import com.cn.exam.service.user.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *@Author fengzhilong
 *@Date 2020/12/21 11:48
 *@Desc
 **/
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;
    @Resource
    private JpaUtil jpaUtil;

    @Override
    public List<User> findAllUser() {

        List<User> list = userDao.findAll();
        Map<String, Object> params = new HashMap<>();
        String hql = "";
        // 前后端 设置token有什么区别？
        // setHeader 和 cookie有什么区别？
        // 每个请求都需要验证token

        return list;
    }


    @Override
    public User findByGhidAndPass(LoginDTO loginDTO) {

        User user = userDao.findByGhidAndPass(loginDTO.getGhid(), loginDTO.getPass());

        return user;
    }
}
