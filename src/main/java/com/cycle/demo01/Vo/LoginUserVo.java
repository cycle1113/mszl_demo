package com.cycle.demo01.Vo;

import lombok.Data;

@Data
public class LoginUserVo {
    private Long id;
    private String account;
    private String nickname;
    private String avatar;
}
