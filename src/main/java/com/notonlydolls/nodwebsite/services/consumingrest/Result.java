package com.notonlydolls.nodwebsite.services.consumingrest;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
	
	// ATTRIBUTES:
	private int count;
	private List<Product> results;
	private String type;
	
	// GETTERS & SETTERS
	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}
	/**
	 * @return the results
	 */
	public List<Product> getResults() {
		return results;
	}
	/**
	 * @param results the results to set
	 */
	public void setResults(List<Product> results) {
		this.results = results;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	// TO STRING
	@Override
	public String toString() {
		return "Results [count=" + count + ", results=" + results + ", type=" + type + "]";
	}
	
}
