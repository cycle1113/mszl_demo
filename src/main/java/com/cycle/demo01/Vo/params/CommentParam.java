package com.cycle.demo01.Vo.params;

import lombok.Data;

@Data
public class CommentParam {
    private Long articleId;
    private String content;
    private Long parent;
    private Long toUserId;
}
