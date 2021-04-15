package com.notonlydolls.nodwebsite;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.notonlydolls.nodwebsite.repository.BlogPost;
import com.notonlydolls.nodwebsite.services.BlogPostServiceI;

/**
 * Clase "Aplicación Spring Boot" que consume servicios de gestión de Backoffice
 * 
 * @author Ana Blanco Escudero
 * @since 06-04-21
 */
@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.notonlydolls.nodwebsite.repository")
public class NodwebsiteApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(NodwebsiteApplication.class, args);
	}

	/** Servicio de consultas y gestión de noticias del blog */
	@Autowired
	private BlogPostServiceI blogPostService;

	/**
	 * Método principal
	 * 
	 * @param args
	 */
	@Override
	public void run(String... args) throws Exception {
		// Creación de 3 noticias e inserción de las mismas en la base de datos
		System.out.println("****************** INSERT BLOG POSTS ******************");
		BlogPost blogPost1 = new BlogPost();
		blogPost1.setPublished(false);
		blogPost1.setTitle("Hello World 1 from NoD!");
		blogPost1.setCategory("Curiosidades");
		blogPost1.setAuthor("Debrax");
		blogPost1.setText("Esto es un texto de prueba 1.");
		blogPostService.insert(blogPost1);

		BlogPost blogPost2 = new BlogPost();
		blogPost2.setPublished(false);
		blogPost2.setTitle("Hello World 2 from NoD!");
		blogPost2.setCategory("Curiosidades");
		blogPost2.setAuthor("Debrax");
		blogPost2.setText("Esto es un texto de prueba 2.");
		blogPostService.insert(blogPost2);

		BlogPost blogPost3 = new BlogPost();
		blogPost3.setPublished(false);
		blogPost3.setTitle("Hello World 3 from NoD!");
		blogPost3.setCategory("Curiosidades");
		blogPost3.setAuthor("Debrax");
		blogPost3.setText("Esto es un texto de prueba 3.");
		blogPostService.insert(blogPost3);

		System.out.println("*****************************************************");

		// Búsqueda de todas las noticias existentes
		List<BlogPost> blogPostList = blogPostService.searchAll();

		// Búsqueda de una noticia por su ID
		blogPostService.searchById(blogPostList.get(1).getId());

		// Eliminación de la noticia 2
		blogPostService.delete(blogPostList.get(1).getId());

		// Modificación del texto de la noticia 3
		blogPost3.setText("Esto es otro texto de prueba 3.");
		blogPostService.update(blogPost3);

	}

}
