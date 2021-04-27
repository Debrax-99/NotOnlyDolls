package com.notonlydolls.nodwebsite.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.notonlydolls.nodwebsite.repository.BlogPost;
import com.notonlydolls.nodwebsite.services.BlogPostServiceI;
import com.notonlydolls.nodwebsite.services.GalleryPictureServiceI;

/**
 * Clase "Controlador" que controla las peticiones relativas a la administración
 * del sitio web
 * 
 * @author Ana Blanco Escudero
 * @since 22-04-21
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

	/**
	 * Mostrar el panel de administración del Backoffice
	 * 
	 * @param model
	 * @return String
	 */
	@GetMapping("/showAdminView")
	public String showAdminView(Model model) {

		return "";
	}

	/**
	 * Redirección al controlador del panel de gestión de noticias del blog
	 * 
	 * @return String
	 */
	@GetMapping("/posts")
	public String redirectToBlogPostController() {
		return "redirect:showBLogPostsView";
	}

	/**
	 * Redirección al controlador del panel de gestión de fotos de la galería
	 * 
	 * @return String
	 */
	@GetMapping("/photos")
	public String redirectToGalleryPictureController() {
		return "redirect:showGalleryPicturesView";
	}

}
