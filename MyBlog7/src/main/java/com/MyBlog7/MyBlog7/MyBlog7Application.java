package com.MyBlog7.MyBlog7;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyBlog7Application {


	public static void main(String[] args) {

		SpringApplication.run(MyBlog7Application.class, args);
				}
	@Bean//in my project  i wanted the copy the data to dto to entity for which config external java libraries.
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
}
