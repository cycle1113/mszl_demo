package com.cycle.demo01.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cycle.demo01.dao.mapper.ArticleMapper;
import com.cycle.demo01.dao.pojo.Article;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ThreadService {
    @Async("taskExecutor")
    public void updateArticleViewCount(ArticleMapper articleMapper, Article article){
        Integer viewCount = article.getViewCounts();
        Article articleUpdate = new Article();
        articleUpdate.setViewCounts(viewCount + 1);
        LambdaQueryWrapper<Article> updateWrapper = new LambdaQueryWrapper<>();
        updateWrapper.eq(Article::getId,article.getId());
        updateWrapper.eq(Article::getViewCounts,article.getViewCounts());
        articleMapper.update(articleUpdate,updateWrapper);
        try {
            Thread.sleep(5000);
            System.out.println("更新阅读完成");

        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
