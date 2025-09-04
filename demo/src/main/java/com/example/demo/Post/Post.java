package com.example.demo.Post;

import com.example.demo.common.BaseVo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Post extends BaseVo {
	private Long id;
	private String postTitle;
	private String postContent;
	private int postView;
	private String postWriter;
}
