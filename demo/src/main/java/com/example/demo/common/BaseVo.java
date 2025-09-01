package com.example.demo.common;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseVo {
	private LocalDateTime createdAt = LocalDateTime.now();
	private LocalDateTime updatedAt;
	public void setUpdatedAt() {
		this.updatedAt = LocalDateTime.now();
	}
}
