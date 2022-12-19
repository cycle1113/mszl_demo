package com.cycle.demo01.service;

import com.cycle.demo01.Vo.Result;
import com.cycle.demo01.Vo.params.LoginParam;
import com.cycle.demo01.dao.pojo.SysUser;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface LoginService {
    Result login(LoginParam loginParam);

    SysUser checkToken(String token);

    Result logout(String token);

    Result register(LoginParam loginParam);
}
