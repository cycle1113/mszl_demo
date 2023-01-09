package com.cycle.demo01.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cycle.demo01.Vo.CommentVo;
import com.cycle.demo01.Vo.Result;
import com.cycle.demo01.Vo.UserVo;
import com.cycle.demo01.Vo.params.CommentParam;
import com.cycle.demo01.dao.mapper.CommentMapper;
import com.cycle.demo01.dao.pojo.Comment;
import com.cycle.demo01.dao.pojo.SysUser;
import com.cycle.demo01.service.CommentsService;
import com.cycle.demo01.service.SysUserService;
import com.cycle.demo01.utils.UserThreadLocal;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentsServiceImpl implements CommentsService {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public Result commentsByArticleId(Long id) {
        /**
         * 1.根据文章id查询评论列表 在comment中查询；
         * 2.根据作者id查询作者信息；
         * 3.判断如果leve==1 查询是否有子评论
         * 4.如果有 根据评论id查询子评论
         */
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getArticleId,id);
        queryWrapper.eq(Comment::getLevel,1);
        List<Comment> comments = commentMapper.selectList(queryWrapper);
        List<CommentVo> commentVos = copyList(comments);
        return Result.success(commentVos);
    }

    @Override
    public Result comment(CommentParam commentParam) {
        SysUser sysUser = UserThreadLocal.get();
        Comment comment = new Comment();
        comment.setArticleId(commentParam.getArticleId());
        comment.setAuthorId(sysUser.getId());
        comment.setContent(commentParam.getContent());
        comment.setCreateDate(System.currentTimeMillis());
        Long parent = commentParam.getParent();
        if (parent == null || parent == 0){
            comment.setLevel(1);
        }else {
            comment.setLevel(2);
        }
        comment.setParentId(parent == null ? 0 : parent);
        Long toUserId = commentParam.getToUserId();
        comment.setToUid(toUserId == null ? 0 : toUserId);
        this.commentMapper.insert(comment);
        return Result.success(null);
    }

    private List<CommentVo> copyList(List<Comment> comments) {
        List<CommentVo> commentVos = new ArrayList<>();
        for (Comment comment : comments){
            commentVos.add(copy(comment));
        }
        return commentVos;
    }

    private CommentVo copy(Comment comment) {
        CommentVo commentVo = new CommentVo();
        BeanUtils.copyProperties(comment,commentVo);
        commentVo.setId(comment.getId());
        //作者信息
        Long authorId = comment.getAuthorId();
        UserVo userVoById = this.sysUserService.findUserVoById(authorId);
        commentVo.setAuthor(userVoById);
        Integer level = comment.getLevel();
        if (level==1){
            Long id = comment.getId();
            List<CommentVo> commentVoList = findCommentsByParentId(id);
            commentVo.setChildrens(commentVoList);
        }
        if (level>1){
            Long toUid = comment.getToUid();
            System.out.println(toUid);
            UserVo toUserVo = this.sysUserService.findUserVoById(toUid);
            commentVo.setToUser(toUserVo);
        }
        return commentVo;
    }

    private List<CommentVo> findCommentsByParentId(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getParentId,id);
        queryWrapper.eq(Comment::getLevel,2);
        List<Comment> comments = this.commentMapper.selectList(queryWrapper);
        return copyList(comments);
    }
}
