package com.blog5.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class PostDto {

    private Long id;
    @NotEmpty
    @Size(min = 2,message = "Post title should have at least 2 character")
    private String title;
    @NotEmpty
    @Size(min = 5,message = "post description should have atleast 5 character")
    private String description;
    @NotEmpty
    @Size(min = 7,message = "Post content should have atleast 7 character")
    private String content;
}
