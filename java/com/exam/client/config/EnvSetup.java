package com.exam.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EnvSetup {
	@Value("${api.alluser}")
	private String allgetApi;
	@Value("${api.byid}")
	private String getUserByIdApi;
	private String createUserApi;
	private String updateUserApi;
	private String deleteUserApi;

}
