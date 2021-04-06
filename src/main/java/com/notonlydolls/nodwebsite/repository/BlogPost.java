package com.notonlydolls.nodwebsite.repository;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Clase "Entidad" ORM que representa a una noticia en la tabla blog_posts de la
 * base de datos de MongoDB
 * 
 * @author Ana Blanco Escudero
 * @since 06-04-21
 */
@Document(collection = "blog_posts")
public class BlogPost {
		
//	@Id
	private String id;
	private Date creationDate;
	private Date lastDate;
	private boolean isPublished;
	private String image;
	private String title;
	private String slug;
	private String category;
	private String author;
	private String text;
}
