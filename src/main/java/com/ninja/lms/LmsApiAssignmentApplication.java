package com.ninja.lms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
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
					.apiInfo(getApiInformation())
					.select()
					.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
					.build();
		
		}
		private ApiInfo getApiInformation(){
			
			
			return new ApiInfoBuilder()
		              .title("API Documentation For Batch & Program API")
		              .description("This is the Batch and Program  API created using Spring Boot.")
		              .version("1.0.0")
		              .build();
		}


}
