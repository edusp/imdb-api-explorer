package com.au.code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
    //System.out.println(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(""));
    SpringApplication.run(Application.class, args);
	}

}
