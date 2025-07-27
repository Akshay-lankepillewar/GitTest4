package com.MyBlog7.MyBlog7.service;

import com.MyBlog7.MyBlog7.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);
    List<CommentDto> getCommentsByPostId(long postid);
}
