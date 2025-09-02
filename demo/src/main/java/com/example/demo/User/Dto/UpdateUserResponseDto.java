package com.example.demo.User.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserResponseDto {
	private String passwordMessage;
	private String nicknameMessage;
	private String emailMessage;
}
