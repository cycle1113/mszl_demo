package com.cycle.demo01.controller;

import com.cycle.demo01.Vo.Result;
import com.cycle.demo01.Vo.params.PageParams;
import com.cycle.demo01.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("articles")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @PostMapping
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

}
