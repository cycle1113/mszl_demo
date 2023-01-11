package com.cycle.demo01.Vo;

import lombok.Data;

@Data
public class CategoryVo {

    //id，图标路径，图标名称
    private Long id;

    private String avatar;

    private String description;

    private String categoryName;
}