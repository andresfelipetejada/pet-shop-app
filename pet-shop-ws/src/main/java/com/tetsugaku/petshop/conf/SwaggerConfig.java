package com.tetsugaku.petshop.conf;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Habilitando la configuracion para swagger
 * 
 * http://localhost:8080/swagger-ui.html
 * http://localhost:8080/v2/api-docs
 * 
 */
@Configuration
@EnableSwagger2
@PropertySource({ "classpath:apidoc.properties"	})
public class SwaggerConfig {

	/**
	 * Tipos de informacion API.
	 */
	private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<>(
			Arrays.asList("application/json"));

	/**
	 * Metadata API
	 * @return
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(metaData())
				.produces(DEFAULT_PRODUCES_AND_CONSUMES)
				.consumes(DEFAULT_PRODUCES_AND_CONSUMES)				
                .select().apis(RequestHandlerSelectors.basePackage("com.tetsugaku.petshop.controller"))                
                .build();		
	}

	/**
	 * Base Metadata
	 * @return
	 */
	private ApiInfo metaData() {
		return new ApiInfo("Spring Boot REST API", "Spring Boot REST API para Pet Shop", "1.0",
				"Terms of service", new Contact("Andres Felipe Tejada", "", "andresfelipetejada@hotmail.com"),
				"Apache License Version 2.0", "https://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());
	}
	
}