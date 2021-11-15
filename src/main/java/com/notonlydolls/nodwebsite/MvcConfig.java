package com.notonlydolls.nodwebsite;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Configuration;
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
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// Register resource handler for images
		registry.addResourceHandler("/pictures/**")
				.addResourceLocations("file:/home/debrax/Escritorio/NoD/images/posts/",
						"file:/home/debrax/Escritorio/NoD/images/photos/")
				.setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic()).resourceChain(true)
				.addResolver(new PathResourceResolver());
	}
	
}
