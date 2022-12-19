package com.cycle.demo01.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cycle.demo01.Vo.ErrorCode;
import com.cycle.demo01.Vo.LoginUserVo;
import com.cycle.demo01.Vo.Result;
import com.cycle.demo01.dao.mapper.SysUserMapper;
import com.cycle.demo01.dao.pojo.SysUser;
import com.cycle.demo01.service.LoginService;
import com.cycle.demo01.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private LoginService loginService;
    @Override
    public SysUser findUserById(Long id) {
        final SysUser sysUser = sysUserMapper.selectById(id);
        if (sysUser == null){
//            sysUser = new SysUser();
            sysUser.setNickname("cycle");
        }
        return sysUser;
    }

    @Override
    public SysUser findUser(String account, String password) {
        // TableInfoHelper.initTableInfo(new MapperBuilderAssistant(new MybatisConfiguration(), ""), SysUser.class);
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getAccount,account);
        queryWrapper.eq(SysUser::getPassword,password);
        //account id 头像 名称
        queryWrapper.select(SysUser::getAccount,SysUser::getId,SysUser::getAdmin,SysUser::getNickname);
        //增加查询效率，只查询一条
        queryWrapper.last("limit 1");
        //selectOne的坑https://www.guangmuhua.com/articleDetail/2625
        return sysUserMapper.selectOne(queryWrapper);
    }

    @Override
    public Result findUserByToken(String token) {
        /**
         * 1.token合法性校验
         *   是否为空， 解析token是否成功 redis是否存在
         * 2.如果校验失败 返回错误
         * 3.如果校验成功，返回LoginUserVo
         */
        SysUser sysUser = loginService.checkToken(token);
        if (sysUser == null){
            return Result.fail(ErrorCode.TOKEN_ERROR.getCode(), ErrorCode.TOKEN_ERROR.getMsg());
        }
        LoginUserVo loginUserVo = new LoginUserVo();
        loginUserVo.setId(sysUser.getId());
        loginUserVo.setAccount(sysUser.getAccount());
        loginUserVo.setNickname(sysUser.getNickname());
        loginUserVo.setAvatar(sysUser.getAvatar());
        return Result.success(loginUserVo);
    }

    @Override
    public SysUser findUserByAccount(String account) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getAccount,account);
        queryWrapper.last("limit 1");
        return this.sysUserMapper.selectOne(queryWrapper);
    }

    @Override
    public void save(SysUser sysUser) {
        //保存用户会自动生成id 默认生成分布式id 采用雪花算法
        this.sysUserMapper.insert(sysUser);
    }
}
