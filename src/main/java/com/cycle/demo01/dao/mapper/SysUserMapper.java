package com.cycle.demo01.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cycle.demo01.dao.pojo.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
}
