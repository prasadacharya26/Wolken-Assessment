package com.wolken.wolkenassessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.wolken.wolkenassessment")
public class WolkenAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(WolkenAssessmentApplication.class, args);
	}

}
