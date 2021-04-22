package com.notonlydolls.nodwebsite.repository;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
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
public class BlogPost {

	// ATTRIBUTES:
	/** Post id */	
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

	@Field(value = "is_published")
	/** Published flag */
	private boolean published;

	@Field(value = "image")
	/** Header image */
	private String image;

	@Field(value = "title")
	/** Post title */
	private String title;

	@Field(value = "slug")
	/** URL slug */
	private String slug;

	@Field(value = "category")
	/** Post category */
	private String category;

	@Field(value = "author")
	/** Author name */
	private String author;

	@Field(value = "text")
	/** Post text */
	private String text;
		

	// GETTERS & SETTERS:
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
	 * @return the isPublished
	 */
	public boolean isPublished() {
		return published;
	}

	/**
	 * @param isPublished the isPublished to set
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

	
	// TO STRING
	@Override
	public String toString() {
		return "BlogPost [id=" + id + ", creationDate=" + creationDate + ", lastDate=" + lastDate + ", published="
				+ published + ", image=" + image + ", title=" + title + ", slug=" + slug + ", category=" + category
				+ ", author=" + author + ", text=" + text + "]";
	}

}
