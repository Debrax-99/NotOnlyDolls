package com.notonlydolls.nodwebsite.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Clase de configuración de la gestión de archivos del backoffice
 * 
 * @author Ana Blanco Escudero
 * @since 06-05-21
 */
@ConfigurationProperties("storage")
public class StorageProperties {

	/**
	 * Ruta de la carpeta para almacenar las fotos de la cabecera de las noticias
	 */
	private String locationPosts = "../images/posts";
	
	/**
	 * Ruta de la carpeta para almacenar las fotos de la galería
	 */
	private String locationPhotos = "../images/photos";
	
	// GETTERS & SETTERS:
	/**
	 * @return the locationPosts
	 */
	public String getLocationPosts() {
		return locationPosts;
	}

	/**
	 * @param locationPosts the locationPosts to set
	 */
	public void setLocationPosts(String locationPosts) {
		this.locationPosts = locationPosts;
	}

	/**
	 * @return the locationPhotos
	 */
	public String getLocationPhotos() {
		return locationPhotos;
	}

	/**
	 * @param locationPhotos the locationPhotos to set
	 */
	public void setLocationPhotos(String locationPhotos) {
		this.locationPhotos = locationPhotos;
	}
	

}
