package com.cycle.demo01.service;

import com.cycle.demo01.dao.pojo.SysUser;

public interface SysUserService {
    SysUser findUserById(Long id);
}
