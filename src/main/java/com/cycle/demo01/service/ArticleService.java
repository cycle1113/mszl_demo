package com.cycle.demo01.service;

import com.cycle.demo01.Vo.Result;
import com.cycle.demo01.Vo.params.PageParams;

public interface ArticleService {

    Result listArticlePage(PageParams pageParams);

    Result hotArticles(int limit);

    Result newArticles(int limit);

    Result listArchives();

    /**
     *
     * @param articleId
     * @return
     */
    Result findArticleById(Long articleId);
}
