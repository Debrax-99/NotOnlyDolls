package com.notonlydolls.nodwebsite.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.notonlydolls.nodwebsite.repository.BlogPost;
import com.notonlydolls.nodwebsite.repository.GalleryPicture;
import com.notonlydolls.nodwebsite.services.BlogPostServiceI;
import com.notonlydolls.nodwebsite.services.GalleryPictureServiceI;

/**
 * Clase "Controlador" que controla las peticiones generales a la aplicación
 * 
 * @author Ana Blanco Escudero
 * @since 22-04-21
 */
@Controller
@RequestMapping("*")
public class NotOnlyDollsController {
	/** Servicio de consultas y gestión de noticias del blog */
	@Autowired
	private BlogPostServiceI blogPostService;

	/** Servicio de consultas y gestión de fotos del blog */
	@Autowired
	private GalleryPictureServiceI galleryPictureService;
	
	/**
	 * Gestión inicial de peticiones que no estén mapeadas por otra ruta.
	 * 
	 * @return String
	 */
	@GetMapping
	public String showIndex() {
		return "index";
	}

	/**
	 * Redirección al controlador del Backoffice
	 * 
	 * @return String
	 */
	@GetMapping("/admin")
	public String redirectToAdminController() {
		return "redirect:showAdminView";
	}
	
	/**
	 * Redirección al controlador de la tienda
	 * 
	 * @return String
	 */
	@GetMapping("/shop")
	public String redirectToShopController() {
		return "redirect:showShopView";
	}
	
	/**
	 * Mostrar la sección del Blog.
	 * 
	 * @param model
	 * @return String
	 */
	@GetMapping("/showBLogView")
	public String showBlog(Model model) {

		// Obtención del listado de noticias de la BBDD.
		final List<BlogPost> blogPostList = blogPostService.searchAll();

		// Carga de datos al modelo.
		model.addAttribute("blogPostListView", blogPostList);

		return "blog";
	}
	
	/**
	 * Mostrar la sección de la Galería.
	 * 
	 * @param model
	 * @return String
	 */
	@GetMapping("/showGalleryView")
	public String showGallery(Model model) {

		// Obtención del listado de fotos de la BBDD.
		final List<GalleryPicture> galleryPictureList = galleryPictureService.searchAll();

		// Carga de datos al modelo.
		model.addAttribute("galleryPictureListView", galleryPictureList);
		
		return "gallery";
	}
	
	/**
	 * Mostrar la sección Acerca de Nosotros
	 * 
	 * @return String
	 */
	@GetMapping("/about-us")
	public String showAboutUsView() {
		return "about-us";
	}

}
