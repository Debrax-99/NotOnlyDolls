package com.notonlydolls.nodwebsite;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

/**
 * Configuration class for MVC architecture
 * 
 * @author Ana Blanco Escudero
 * @since 10-04-21
 */
@Configuration
@PropertySource("classpath:nodwebsite.properties")
public class MvcConfig implements WebMvcConfigurer {

	// Posts images folder
	@Value("${posts_folder}")
	private String posts;
		
	// Photos images folder
	@Value("${photos_folder}")
	private String photos;
		
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// Register resource handler for images
		registry.addResourceHandler("/pictures/**")
				.addResourceLocations("file:" + posts,
						"file:" + photos)
				.setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic()).resourceChain(true)
				.addResolver(new PathResourceResolver());
	}
	
}
