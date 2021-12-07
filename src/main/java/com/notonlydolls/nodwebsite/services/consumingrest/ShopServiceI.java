package com.notonlydolls.nodwebsite.services.consumingrest;

import java.util.List;

/**
 * Service class that consumes Etsy API endpoints to charge data in shop page
 * 
 * @author Ana Blanco Escudero
 * @since 11-11-21
 */
public interface ShopServiceI {

	/**
	 * Retrieve all products from shop
	 * 
	 * @return List<Product>
	 */
	public List<Product> getProducts();
	
	/**
	 * Retrieve all product images from shop
	 * 
	 * @return List<Product>
	 */
	public Image getImage(String productId);

}
