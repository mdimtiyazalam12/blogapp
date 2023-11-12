package com.blog5.service;

import com.blog5.payload.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(long postId,CommentDto commentDto);


    List<CommentDto> getCommentsByPostId(long postId);

    CommentDto getCommentByCommentId(long postId, long commentId);

    void deleteCommentByCommentId(long postId, long commentId);

    List<CommentDto> getAllComment();
}
