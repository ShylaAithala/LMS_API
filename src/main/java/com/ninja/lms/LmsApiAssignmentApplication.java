package com.ninja.lms;

import java.util.function.Predicate;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.MergedAnnotationPredicates;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = "com.ninja.lms")
@EnableSwagger2
public class LmsApiAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmsApiAssignmentApplication.class, args);
	}
	

		@Bean
		public Docket apiToSwagger() {
			return new Docket(DocumentationType.SWAGGER_2)
					.select()
					.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
					.build();
		
		}
}
