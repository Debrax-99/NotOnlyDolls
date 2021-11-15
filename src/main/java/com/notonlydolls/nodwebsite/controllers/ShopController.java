package com.notonlydolls.nodwebsite.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.notonlydolls.nodwebsite.services.consumingrest.Product;
import com.notonlydolls.nodwebsite.services.consumingrest.ShopServiceI;

/**
 * Controller for shop requests
 * 
 * @author Ana Blanco Escudero
 * @since 10-11-21
 */
@Controller
public class ShopController {

	/** Etsy API Shop Service */
	@Autowired
	private ShopServiceI shopService;
	
	/**
	 * Show Shop page
	 * 
	 * @param model
	 * @return String
	 */
	@GetMapping("/showShopView")
	public String showShopView(Model model) {
		// Obtención del listado de productos consumiendo la API de Etsy
		final List<Product> productList = shopService.getProducts();

		// Carga de datos al modelo.
		model.addAttribute("productListView", productList);
		return "shop";
	}


}
