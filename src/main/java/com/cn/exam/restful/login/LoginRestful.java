package com.cn.exam.restful.login;

import com.alibaba.fastjson.JSONObject;
import com.cn.common.vo.ResCode;
import com.cn.common.vo.ResResult;
import com.cn.exam.base.pmJwtToken;
import com.cn.exam.dto.login.LoginDTO;
import com.cn.exam.entity.user.User;
import com.cn.exam.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *@Author fengzhilong
 *@Date 2020/12/22 13:49
 *@Desc
 **/
@RestController
@RequestMapping("/login")
@Slf4j
public class LoginRestful {

    @Autowired
    private UserService userService;

    @PostMapping("/verification")
    public ResResult login(@RequestBody LoginDTO loginDTO) {
        User user = userService.findByGhidAndPass(loginDTO);
        if (user != null) {
            String strJson = JSONObject.toJSONString(user);
            String token = pmJwtToken.getJwtToken(user.getGhid(), strJson);
            log.info("================>账号：" + loginDTO.getGhid() + " 登录成功,生成token：" + token);
            return ResCode.OK.msg("登录成功")
                    .putData("content", user)
                    .putData("loginToken", token);
        } else {
            log.info("================>账号：" + loginDTO.getGhid() + " 登录登录失败,原因：账号或密码错误");
            return ResCode.ERROR.msg("账号或密码错误");
        }
    }

}
