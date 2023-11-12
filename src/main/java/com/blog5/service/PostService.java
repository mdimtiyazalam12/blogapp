package com.blog5.service;

import com.blog5.payload.PostDto;
import com.blog5.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto savePost(PostDto postDto);

    void deletePostById(long id);

    PostDto updatePostById(long id, com.blog5.payload.PostDto postDto);

    PostDto getPostById(long id, PostDto postDto);

    PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir);
}
