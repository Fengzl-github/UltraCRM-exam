package com.cn.exam.service.user.impl;

import com.cn.common.jpa.util.JpaUtil;
import com.cn.exam.dao.user.UserDao;
import com.cn.exam.dto.login.LoginDTO;
import com.cn.exam.entity.user.User;
import com.cn.exam.service.user.UserService;
import org.springframework.data.domain.Page;
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

    /**
     * @Author fengzhilong
     * @Desc 获取参考人员列表
     * @Date 2021/2/22 13:46
     * @param planId
     * @return org.springframework.data.domain.Page<com.cn.exam.entity.user.User>
     **/
    @Override
    public List<User> findTestUserList(String planId) {
        String hql = "";
        Map<String, Object> params = new HashMap<>();
        hql = "select u from User u where u.ghid not in " +
                "(select e.ghid from ExamTestPerson e where e.planId = :planId) ";
        params.put("planId", planId);

        hql += "order by u.ghid asc ";

        List<User> list = jpaUtil.list(hql, params, User.class);

        return list;
    }
}
