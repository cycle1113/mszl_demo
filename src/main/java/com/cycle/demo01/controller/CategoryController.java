package com.cycle.demo01.controller;

import com.cycle.demo01.Vo.Result;
import com.cycle.demo01.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("categorys")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public Result listCategory(){
        return categoryService.findAll();
    }

    @GetMapping("detail")
    public Result categoriesDetail(){
        return categoryService.findAllDetail();
    }
    @GetMapping("detail/{id}")
    public Result categoriesDetailById(@PathVariable Long id){
        return categoryService.categoriesDetailById(id);
    }
}
