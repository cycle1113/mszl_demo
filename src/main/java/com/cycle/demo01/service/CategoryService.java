package com.cycle.demo01.service;

import com.cycle.demo01.Vo.CategoryVo;
import com.cycle.demo01.Vo.Result;

public interface CategoryService {
    CategoryVo findCategoryById(Long categoryId);

    Result findAll();
}
