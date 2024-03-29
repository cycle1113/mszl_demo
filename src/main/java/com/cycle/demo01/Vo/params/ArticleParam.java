package com.cycle.demo01.Vo.params;

import com.cycle.demo01.Vo.CategoryVo;
import com.cycle.demo01.Vo.TagVo;
import lombok.Data;

import java.util.List;

@Data
public class ArticleParam {
    private Long id;
    private ArticleBodyParam body;
    private CategoryVo category;
    private String summary;
    private List<TagVo> tags;
    private String title;
}
