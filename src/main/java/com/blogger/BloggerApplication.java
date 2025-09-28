package com.blogger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BloggerApplication {

	public static void main(String[] args) {
		try{
		SpringApplication.run(BloggerApplication.class, args);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
