package com.cycle.demo01.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cycle.demo01.dao.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TagMapper extends BaseMapper<Tag> {
    List<Tag> findTagsByArticleId(Long articleId);

    List<Long> findHotTagId(int limit);

    List<Tag> findTagsByTagId(List<Long> tagIds);
}
