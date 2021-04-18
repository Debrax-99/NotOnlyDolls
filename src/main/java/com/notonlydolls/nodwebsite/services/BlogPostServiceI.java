package com.notonlydolls.nodwebsite.services;

import java.util.List;
import java.util.Optional;

import com.notonlydolls.nodwebsite.repository.BlogPost;

/**
 * Clase "Servicio" de consultas y gestión de noticias del Blog
 * 
 * @author Ana Blanco Escudero
 * @since 15-04-21
 */
public interface BlogPostServiceI {
	/**
	 * Método que inserta una noticia nueva en la base de datos
	 * 
	 * @param entity
	 */
	public void insert(final BlogPost entity);

	/**
	 * Método que consulta todas las noticias en la base de datos
	 * 
	 * @return List<BlogPost>
	 */
	public List<BlogPost> searchAll();

	/**
	 * Método que consulta una noticia en la base de datos a partir de su ID
	 * 
	 * @param id
	 */
	public Optional<BlogPost> searchById(final String id);

	/**
	 * Método que borra una noticia en la base de datos a partir de su ID
	 * 
	 * @param id
	 */
	public void delete(final String id);

	/**
	 * Método que actualiza los datos de una noticia en la base de datos
	 * 
	 * @param entity
	 */
	public void update(final BlogPost entity);

}
