package com.cycle.demo01.controller;

import com.cycle.demo01.Vo.Result;
import com.cycle.demo01.Vo.params.PageParams;
import com.cycle.demo01.Vo.params.ArticleParam;
import com.cycle.demo01.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cycle.demo01.common.aop.LogAnnotation;

@RestController
@RequestMapping("articles")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @PostMapping
    @LogAnnotation(module="文章",operation="获取文章列表")
    public Result articles(@RequestBody PageParams pageParams){
        Result articles = articleService.listArticlePage(pageParams);
        return Result.success(articles);
    }
    @PostMapping("hot")
    public Result hotArticles(){
        int limit = 5;

        return articleService.hotArticles(limit);
    }
    @PostMapping("new")
    public Result newArticles(){
        int limit = 5;

        return articleService.newArticles(limit);
    }
    @PostMapping("listArchives")
    public Result listArchives(){

        return articleService.listArchives();
    }

    @PostMapping("view/{id}")
    public Result findArticleById(@PathVariable("id") Long articleId){
        return articleService.findArticleById(articleId);
    }

    @PostMapping("publish")
    public Result publish(@RequestBody ArticleParam articleParam){
        return articleService.publish(articleParam);
    }

}
