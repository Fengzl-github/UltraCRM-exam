package com.cn.exam.dao.user;

import com.cn.exam.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 *@Author fengzhilong
 *@Date 2020/12/21 11:44
 *@Desc
 **/
@Repository
public interface UserDao extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    User findByGhidAndPass(String ghid, String pass);
}
