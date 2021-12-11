package com.notonlydolls.nodwebsite.repository;

import java.util.Arrays;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Clase entidad que representa a una noticia en el documento blog_posts de la base
 * de datos de MongoDB
 * 
 * @author Ana Blanco Escudero
 * @since 06-04-21
 */
@Document(collection = "blog_posts")
public class BlogPost implements Persistable<String>{

	// ATTRIBUTES:
	/** Post id */	
	@Id
	private String id;

	/** Creation date */
	@CreatedDate
	private Date creationDate;

	/** Last modification date */
	@LastModifiedDate
	private Date lastDate;

	/** Published flag */
	@Field(value = "published")
	private boolean published;

	/** Header image */
	@Field(value = "image")
	private String image;

	/** Post title */
	@Field(value = "title")
	private String title;

	/** URL slug */
	@Field(value = "slug")
	private String slug;

	/** Post category */
	@Field(value = "category")
	private String category;

	/** Author name */
	@Field(value = "author")
	private String author;

	/** Post text */
	@Field(value = "text")
	private String text;
	
	/** Persisted flag */
	@Field(value = "persisted")
	private boolean persisted;

	/** LastNew flag */
	@Transient
	private boolean lastNew = false;
	
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
	 * @return the published
	 */
	public boolean isPublished() {
		return published;
	}

	/**
	 * @param published the published to set
	 */
	public void setPublished(boolean published) {
		this.published = published;
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

	/**
	 * @return the slug
	 */
	public String getSlug() {
		return slug;
	}

	/**
	 * @param slug the slug to set
	 */
	public void setSlug(String slug) {
		this.slug = slug;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

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

	/**
	 * @return the lastNew
	 */
	public boolean isLastNew() {
		return lastNew;
	}

	/**
	 * @param lastNew the lastNew to set
	 */
	public void setLastNew(boolean lastNew) {
		this.lastNew = lastNew;
	}
		
	@Override
	public boolean isNew() {
		return !persisted;
	}

	// TO STRING
	@Override
	public String toString() {
		return "BlogPost [id=" + id + ", creationDate=" + creationDate + ", lastDate=" + lastDate + ", published="
				+ published + ", image=" + image + ", title=" + title + ", slug=" + slug + ", category=" + category
				+ ", author=" + author + ", text=" + text + "]";
	}
	
	// SLUG CONSTRUCTION METHOD
	/**
	 * 
	 * @param title
	 * @return
	 */
	public String constructSlug(String title) {
		title = title.replaceAll("[^A-Za-z0-9 ]", "");
		String[] slugWords = Arrays.copyOfRange(title.toLowerCase().trim().split(" "), 0, 5);
		String slug = "";
		for (int i = 0; i < slugWords.length; i++) {
			slug += slugWords[i];
			if (i < slugWords.length-2) {				
				slug += "-";
			}
			i++;
		}
		return slug;
	}

}
