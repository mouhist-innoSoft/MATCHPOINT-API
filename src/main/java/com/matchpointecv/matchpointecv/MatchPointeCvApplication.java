package com.matchpointecv.matchpointecv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
//@Profile("swagger")
public class MatchPointeCvApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatchPointeCvApplication.class, args);
	}

}
