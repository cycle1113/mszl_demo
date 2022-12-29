package com.cycle.demo01.service;

import com.cycle.demo01.Vo.Result;
import com.cycle.demo01.Vo.UserVo;
import com.cycle.demo01.dao.pojo.SysUser;

public interface SysUserService {
    UserVo findUserVoById(Long id);
    SysUser findUserById(Long id);

    SysUser findUser(String account, String password);

    Result findUserByToken(String token);

    SysUser findUserByAccount(String account);

    void save(SysUser sysUser);
}
