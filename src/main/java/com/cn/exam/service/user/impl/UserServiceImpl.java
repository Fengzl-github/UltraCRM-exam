package com.cn.exam.service.user.impl;

import com.cn.exam.dao.user.UserDao;
import com.cn.exam.dto.login.LoginDTO;
import com.cn.exam.entity.user.User;
import com.cn.exam.service.user.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *@Author fengzhilong
 *@Date 2020/12/21 11:48
 *@Desc
 **/
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public List<User> findAllUser() {

        List<User> list = userDao.findAll();

        return list;
    }


    @Override
    public User findByGhidAndPass(LoginDTO loginDTO) {

        User user = userDao.findByGhidAndPass(loginDTO.getGhid(), loginDTO.getPass());

        return user;
    }
}
