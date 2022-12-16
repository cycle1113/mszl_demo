package com.cycle.demo01.controller;

import com.cycle.demo01.Vo.Result;
import com.cycle.demo01.Vo.params.LoginParam;
import com.cycle.demo01.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class LoginController {
    private LoginService loginService;
    @PostMapping
    public Result login(@RequestBody LoginParam loginParam){
        //登录要验证用户 需要访问用户表 不使用userService 直接使用业务操作
        return loginService.login(loginParam);
    }
}
