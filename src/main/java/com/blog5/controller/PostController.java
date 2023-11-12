package com.blog5.controller;

import com.blog5.payload.PostDto;
import com.blog5.payload.PostResponse;
import com.blog5.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    @PostMapping
    public ResponseEntity<?> savePost(@Valid @RequestBody PostDto postDto ,BindingResult result){
        if (result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PostDto dto = postService.savePost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePostById(@PathVariable("id") long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post is delete by id",HttpStatus.OK);

    }

    @PutMapping("{id}")
    public ResponseEntity<PostDto> updatePostById(@PathVariable("id") long id, @RequestBody PostDto postDto){
        PostDto dto = postService.updatePostById(id, postDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    //http://localhost:8080/api/1
    @GetMapping("{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id")long id,@RequestBody PostDto postDto){
        PostDto dto = postService.getPostById(id, postDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    // http://localhost:8080/api/post?pagaNo=0&pageSize=3&sortBy=title
    @GetMapping
    public PostResponse getAllPost(
            @RequestParam(name = "pageNo",defaultValue = "0",required = false) int pageNo,
            @RequestParam(name = "pageSize",defaultValue = "3",required = false) int pageSize,
            @RequestParam(name = "sortBy",defaultValue = "id",required = false) String sortBy,
            @RequestParam(name = "sortDir",defaultValue = "asc",required = false) String sortDir
    ){
        PostResponse postResponse = postService.getAllPost(pageNo,pageSize,sortBy,sortDir);
        return postResponse;

    }
}
