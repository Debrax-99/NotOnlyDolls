package com.notonlydolls.nodwebsite.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Clase "Controlador" que controla las peticiones relativas a la tienda
 * del sitio web
 * 
 * @author Ana Blanco Escudero
 * @since 01-05-21
 */
@Controller
public class ShopController {

	/**
	 * Mostrar la página de la tienda
	 * 
	 * @param model
	 * @return String
	 */
	@GetMapping("/showShopView")
	public String showShopView(Model model) {

		return "shop";
	}
}
