package com.cycle.demo01.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cycle.demo01.dao.dos.Archives;
import com.cycle.demo01.dao.pojo.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
    List<Archives> listArchives();
}
