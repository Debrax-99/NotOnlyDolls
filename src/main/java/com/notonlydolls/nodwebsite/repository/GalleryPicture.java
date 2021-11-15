package com.notonlydolls.nodwebsite.repository;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
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
public class GalleryPicture implements Persistable<String> {

	// ATTRIBUTES:
	/** Picture id */
	@Id
	private String id;
	
	/** Creation date */
	@CreatedDate
	private Date creationDate;

	/** Last modification date */
	@LastModifiedDate
	private Date lastDate;

	/** Header image URL */
	@Field(value = "image")
	private String image;

	/** Picture title */
	@Field(value = "title")
	private String title;
	
	/** Persisted flag */
	private boolean persisted;

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

	// PERSISTED FLAG
	/**
	 * @return the persisted
	 */
	public boolean isPersisted() {
		return persisted;
	}

	/**
	 * @param persisted the persisted to set
	 */
	public void setPersisted(boolean persisted) {
		this.persisted = persisted;
	}

	@Override
	public boolean isNew() {
		return !persisted;
	}

	// TO STRING
	@Override
	public String toString() {
		return "GalleryPicture [id=" + id + ", creationDate=" + creationDate + ", lastDate=" + lastDate + ", image="
				+ image + ", title=" + title + "]";
	}

}
