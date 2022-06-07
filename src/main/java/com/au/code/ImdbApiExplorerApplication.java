package com.au.code;

import com.au.code.repository.security.AuthUserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = {AuthUserRepository.class})
public class ImdbApiExplorerApplication {

	public static void main(String[] args) {
    //System.out.println(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(""));
    SpringApplication.run(ImdbApiExplorerApplication.class, args);
	}

}
