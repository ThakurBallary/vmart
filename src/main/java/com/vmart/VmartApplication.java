package com.vmart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class VmartApplication {

	public static void main(String[] args) {
		SpringApplication.run(VmartApplication.class, args);
	}

}
