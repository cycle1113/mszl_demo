package com.cycle.demo01;

import com.cycle.demo01.dao.pojo.SysUser;
import com.cycle.demo01.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;

public class testFindUser {
    static SysUserService sysUserService;
    public static void main(String[] args) {
        SysUser userInfo =  sysUserService.findUser("admin","15f08f86435b060236fa9ccea751e9e5");
        System.out.println(userInfo);

    }
}
