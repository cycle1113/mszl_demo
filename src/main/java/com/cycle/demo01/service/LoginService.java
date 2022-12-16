package com.cycle.demo01.service;

import com.cycle.demo01.Vo.Result;
import com.cycle.demo01.Vo.params.LoginParam;

public interface LoginService {
    Result login(LoginParam loginParam);
}
