package com.cycle.demo01.service;

import com.cycle.demo01.Vo.Result;
import com.cycle.demo01.Vo.params.CommentParam;
import org.springframework.stereotype.Service;


public interface CommentsService {
    /**
     * 根据文章id查询所有的评论列表
     * @param id
     * @return
     */
    Result commentsByArticleId(Long id);

    /**
     * 新增评论
     * @param commentParam
     * @return
     */
    Result comment(CommentParam commentParam);
}
