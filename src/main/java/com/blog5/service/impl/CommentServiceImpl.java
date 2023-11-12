package com.blog5.service.impl;

import com.blog5.entity.Comment;
import com.blog5.entity.Post;
import com.blog5.exception.ResourceNotFound;
import com.blog5.payload.CommentDto;
import com.blog5.repository.CommentRepository;
import com.blog5.repository.PostRepository;
import com.blog5.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private PostRepository postRepository;
    private ModelMapper mapper;
    private CommentRepository commentRepository;

    public CommentServiceImpl(PostRepository postRepository, ModelMapper mapper, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.mapper = mapper;
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFound("Post is not found by postId" + postId)
        );

        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);

        CommentDto dto = mapToDto(savedComment);

        return dto;
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFound("Post is not found by postId" + postId)
        );

        List<Comment> comments = commentRepository.findByPostId(postId);

       return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());

    }

    @Override
    public CommentDto getCommentByCommentId(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFound("Post is not found by postId" + postId)
        );

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFound("Comment is not found By CommentId" + commentId)
        );

        CommentDto dto = mapToDto(comment);

        return dto;
    }

    @Override
    public void deleteCommentByCommentId(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFound("Post is not found by postId" + postId)
        );

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFound("Comment is not found By CommentId" + commentId)
        );
        commentRepository.deleteById(commentId);


    }

    @Override
    public List<CommentDto> getAllComment() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());

    }


    Comment mapToEntity(CommentDto commentDto){
        Comment comment = mapper.map(commentDto, Comment.class);
        return comment;
    }

    CommentDto mapToDto(Comment savedComment){
        CommentDto dto = mapper.map(savedComment, CommentDto.class);
        return dto;
    }
}
