package com.blog5.controller;

import com.blog5.payload.CommentDto;
import com.blog5.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId,
                                                    @RequestBody CommentDto commentDto){
        CommentDto dto = commentService.createComment(postId, commentDto);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable(value = "postId") long postId){
        return commentService.getCommentsByPostId(postId);
    }

    @GetMapping("/posts/{postId}/comments{commentId}")
    public CommentDto getCommentByCommentId(@PathVariable(value = "postId")long postId,
                                            @PathVariable(value = "commentId") long commentId){
        return commentService.getCommentByCommentId(postId,commentId);
    }

    @DeleteMapping("/posts/{postId}/comments{commentId}")
    public ResponseEntity<String> deleteCommentByCommentId(@PathVariable(value = "postId")long postId,
                                            @PathVariable(value = "commentId") long commentId){
        commentService.deleteCommentByCommentId(postId,commentId);
        return new ResponseEntity<>("Comment is deleted",HttpStatus.OK);
    }
    @GetMapping("/comments")
    public List<CommentDto> getAllComment(){
        return commentService.getAllComment();
    }
}
