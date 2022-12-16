package com.cycle.demo01.service.impl;

import com.cycle.demo01.dao.mapper.SysUserMapper;
import com.cycle.demo01.dao.pojo.SysUser;
import com.cycle.demo01.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public SysUser findUserById(Long id) {
        final SysUser sysUser = sysUserMapper.selectById(id);
        if (sysUser == null){
//            sysUser = new SysUser();
            sysUser.setNickname("cycle");
        }
        return sysUser;
    }
}
