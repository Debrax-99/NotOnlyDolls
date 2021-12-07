package com.notonlydolls.nodwebsite.services.consumingrest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Service class that consumes Etsy API endpoints to charge data in shop page
 * 
 * @author Ana Blanco Escudero
 * @since 11-11-21
 */
@Service
@PropertySource("classpath:nodwebsite.properties")
public class ShopServiceImpl implements ShopServiceI {
	
	// Etsy API key
	private final String API_KEY;

	// Posts pictures folder path
	private final String SHOP_ID;
	
	// CONSTRUCTOR
	@Autowired
    public ShopServiceImpl(@Value("${etsy_api_key}") String key, @Value("${etsy_shop_id}") String id) {
        this.API_KEY = key;
        this.SHOP_ID = id;

	}
		
	// REST TEMPLATE
	@Autowired
	RestTemplate restTemplate;

	@Override
	public List<Product> getProducts() {
		ResponseEntity<Result> response = restTemplate.getForEntity("https://openapi.etsy.com/v2/shops/" + SHOP_ID + "/listings/active?api_key=" + API_KEY,
				Result.class);
		Result result = response.getBody();
		
		List<List<Product>> productsList = Arrays.asList(result.getResults());
		List<Product> products = new ArrayList<Product>();
		
		for (List<Product> list : productsList) {
			products.addAll(list);
		}
		for (Product product : products) {
			product.setImage(getImage(product.getId()).getUrl());
		}
		
		return products;
	}

	@Override
	public Image getImage(String productId) {
		ResponseEntity<ResultI> response = restTemplate.getForEntity("https://openapi.etsy.com/v2/listings/" + productId + "/images?api_key=" + API_KEY,
				ResultI.class);
		ResultI result = response.getBody();
		
		List<List<Image>> imagesList = Arrays.asList(result.getResults());
		List<Image> images = new ArrayList<Image>();
		
		for (List<Image> list : imagesList) {
			images.addAll(list);
		}
		
		return images.stream()
				  .filter(img -> img.getRank() == 1)
				  .findAny()
				  .orElse(null);
	}

}
