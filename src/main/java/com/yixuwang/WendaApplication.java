package com.yixuwang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WendaApplication {
	/*
}
public class WendaApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(WendaApplication.class);
	}
*/
	public static void main(String[] args) {
		SpringApplication.run(WendaApplication.class, args);
	}
}
