package com.notonlydolls.nodwebsite.services;

import java.util.List;
import java.util.Optional;

import com.notonlydolls.nodwebsite.repository.GalleryPicture;

/**
 * Service class that manages gallery pictures
 * 
 * @author Ana Blanco Escudero
 * @since 17-04-21
 */
public interface GalleryPictureServiceI {
	/**
	 * Método que inserta una foto nueva en la base de datos
	 * 
	 * @param entity
	 */
	public void insert(final GalleryPicture entity);

	/**
	 * Método que consulta todas las fotos en la base de datos
	 * 
	 * @return List<GalleryPicture>
	 */
	public List<GalleryPicture> searchAll();

	/**
	 * Método que consulta una foto en la base de datos a partir de su ID
	 * 
	 * @param id
	 */
	public Optional<GalleryPicture> searchById(final String id);

	/**
	 * Método que borra una foto en la base de datos a partir de su ID
	 * 
	 * @param id
	 */
	public void delete(final String id);

	/**
	 * Método que actualiza los datos de una foto en la base de datos
	 * 
	 * @param entity
	 */
	public void update(final GalleryPicture entity);	

}
