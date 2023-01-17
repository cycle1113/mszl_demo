package com.cycle.demo01.controller;

import com.cycle.demo01.Vo.Result;
import com.cycle.demo01.common.cache.Cache;
import com.cycle.demo01.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tags")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping
    public Result findAll(){
        return tagService.findAll();
    }

    @GetMapping("hot")
    @Cache(expire = 5*60*1000,name = "hot_article")
    public Result hot(){
        int limit = 6;
        return tagService.hots(limit);
    }

    @GetMapping("detail")
    public Result findAllDetail(){
        return tagService.findAllDetail();
    }

    @GetMapping("detail/{id}")
    public Result findAllDetailById(@PathVariable Long id){
        return tagService.findAllDetailById(id);
    }
}
