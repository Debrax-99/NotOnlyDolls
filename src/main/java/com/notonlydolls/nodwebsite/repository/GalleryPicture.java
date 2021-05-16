package com.notonlydolls.nodwebsite.repository;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Clase entidad que representa a una foto de la galería en el documento
 * gallery_pictures de la base de datos de MongoDB
 * 
 * @author Ana Blanco Escudero
 * @since 08-04-21
 */
@Document(collection = "gallery_pictures")
public class GalleryPicture {

	// ATTRIBUTES:
	/** Picture id */
	@Id
	private String id;

	@CreatedDate
	@Field(value = "creation_date")
	/** Creation date */
	private Date creationDate;

	@LastModifiedDate
	@Field(value = "last_date")
	/** Last modification date */
	private Date lastDate;

	@Field(value = "image")
	/** Header image URL */
	private String image;

	@Field(value = "title")
	/** Picture title */
	private String title;

	
	// GETTERS & SETTERS
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the lastDate
	 */
	public Date getLastDate() {
		return lastDate;
	}

	/**
	 * @param lastDate the lastDate to set
	 */
	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	
	// TO STRING
	@Override
	public String toString() {
		return "GalleryPicture [id=" + id + ", creationDate=" + creationDate + ", lastDate=" + lastDate + ", image="
				+ image + ", title=" + title + "]";
	}

}
