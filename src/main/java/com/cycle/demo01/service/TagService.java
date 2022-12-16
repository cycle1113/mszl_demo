package com.cycle.demo01.service;

import com.cycle.demo01.Vo.Result;
import com.cycle.demo01.Vo.TagVo;

import java.util.List;

public interface TagService {
    List<TagVo> findTagsByArticleId(Long articleId);

    Result hots(int limit);
}
