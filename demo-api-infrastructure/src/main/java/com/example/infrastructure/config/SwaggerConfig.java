/**
 * 
 */
package com.example.infrastructure.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 
 */
@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

	@Bean
	GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder().group("public-api").pathsToMatch("/products/**").build();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/swagger-ui/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/springdoc-openapi-ui/")
				.resourceChain(false);
	}
}