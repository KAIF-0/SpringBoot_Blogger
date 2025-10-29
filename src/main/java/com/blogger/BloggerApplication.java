package com.blogger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BloggerApplication {

	public static void main(String[] args) {
		try {
			ConfigurableApplicationContext context = SpringApplication.run(BloggerApplication.class, args);
			ConfigurableEnvironment env = context.getEnvironment();
			System.out.println(
					"\n\n" +
							"Application: " + env.getProperty("spring.application.name") + "\n" +
							"Local: http://localhost:" + env.getProperty("server.port") + "\n" + "Environment: "
							+ env.getActiveProfiles()[0] + "\n\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
