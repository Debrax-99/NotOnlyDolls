package com.notonlydolls.nodwebsite.services.consumingrest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Image {
	
	// ATTRIBUTES:
	@JsonProperty("listing_image_id")
	private String id;
	private int rank;
	@JsonProperty("url_570xN")
	private String url;
	
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
	 * @return the rank
	 */
	public int getRank() {
		return rank;
	}
	/**
	 * @param rank the rank to set
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	//TO STRING
	@Override
	public String toString() {
		return "Image [id=" + id + ", rank=" + rank + ", url=" + url + "]";
	}
	
}