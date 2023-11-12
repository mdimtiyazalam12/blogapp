package com.blog5.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private List<PostDto> postDto;

    private int pageNo;

    private int pageSize;

    private Long totalElement;

    private int totalPages;

    private boolean last;
}
