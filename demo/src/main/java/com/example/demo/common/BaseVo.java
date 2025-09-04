package com.example.demo.common;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseVo {
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
