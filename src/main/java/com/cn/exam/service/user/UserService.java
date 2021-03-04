package com.cn.exam.service.user;

import com.cn.exam.dto.login.LoginDTO;
import com.cn.exam.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 *@Author fengzhilong
 *@Date 2020/12/21 11:46
 *@Desc
 **/
@Validated
public interface UserService {

    List<User> findAllUser();

    User findByGhidAndPass(LoginDTO loginDTO);

    List<User> findTestUserList(String planId);
}
