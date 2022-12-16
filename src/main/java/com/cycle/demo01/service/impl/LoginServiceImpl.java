package com.cycle.demo01.service.impl;

import com.cycle.demo01.Vo.ErrorCode;
import com.cycle.demo01.Vo.Result;
import com.cycle.demo01.Vo.params.LoginParam;
import com.cycle.demo01.dao.pojo.SysUser;
import com.cycle.demo01.service.LoginService;
import com.cycle.demo01.service.SysUserService;
import com.cycle.demo01.utils.JWTUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    private SysUserService sysUserService;
    @Override
    public Result login(LoginParam loginParam) {
        /**
         * 1.检查蟾酥是否合法
         * 2.根据用户名和密码查询是否存在
         * 3.如果不存在 登录失败
         * 4.如果存在，使用JWT生成token 返回给前端
         * 5.token放入redis当中，rides token：user信息 设置过期时间（登录认证的时候先认证token字符串是否合法，再在redis中进行认证）
         */
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();
        if(StringUtils.isBlank(account) || StringUtils.isBlank(password)){
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(),ErrorCode.PARAMS_ERROR.getMsg());
        }
        SysUser sysUser = sysUserService.findUser(account,password);
        if (sysUser == null){
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(),ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg())
        }
        JWTUtils.createToken(sysUser.getId());
        return null;
    }
}
