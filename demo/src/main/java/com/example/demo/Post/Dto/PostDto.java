package com.example.demo.Post.Dto;

import com.example.demo.Post.Post;
import com.example.demo.common.BaseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto extends BaseDto {
    private String id; 
    private String postTitle;
    private String postContent;
    private String postWriter;
    
    // VO -> DTO
    public static PostDto fromVo(Post post) {
        if (post == null) return null;
        PostDto dto = PostDto.builder()
                .id(post.getId() != null ? String.valueOf(post.getId()) : null)
                .postTitle(post.getPostTitle())
                .postContent(post.getPostContent())
                .postWriter(post.getPostWriter())
                .build();

        if (post.getCreatedAt() != null) dto.setCreatedAt(post.getCreatedAt().toString());
        if (post.getUpdatedAt() != null) dto.setUpdatedAt(post.getUpdatedAt().toString());

        return dto;
    }

    // DTO -> VO
    public Post toVo() {
        Post vo = new Post();
        if (this.id != null) vo.setId(Long.parseLong(this.id));
        vo.setPostTitle(this.postTitle);
        vo.setPostContent(this.postContent);
        vo.setPostWriter(this.postWriter);
        return vo;
    }
}
