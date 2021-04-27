package com.notonlydolls.nodwebsite.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.notonlydolls.nodwebsite.repository.BlogPost;
import com.notonlydolls.nodwebsite.services.BlogPostServiceI;

/**
 * Clase "Controlador" que controla las peticiones relativas a la gestión de
 * noticias del blog
 * 
 * @author Ana Blanco Escudero
 * @since 24-04-21
 */
@Controller
public class BlogPostController {

	/** Servicio de consultas y gestión de noticias del blog */
	@Autowired
	private BlogPostServiceI blogPostService;

	/**
	 * Mostrar el listado de todas las noticias del blog.
	 * 
	 * @param model
	 * @return String
	 */
	@GetMapping("/showBLogPostsView")
	public String showBlogPosts(Model model) {

		// Obtención del listado de noticias de la BBDD.
		final List<BlogPost> blogPostList = blogPostService.searchAll();

		// Carga de datos al modelo.
		model.addAttribute("blogPostListView", blogPostList);
		model.addAttribute("btnDropBlogPostEnabled", Boolean.FALSE);

		return "posts";
	}
	
	/**
	 * Añadir una nueva noticia con todos sus datos validados.
	 * 
	 * @param newBlogPost
	 * @param result
	 * @return String
	 * @throws Exception
	 */
	@PostMapping("/actInsertBlogPost")
	private String submitInsertBlogPostForm(@Valid @ModelAttribute BlogPost newBlogPost, BindingResult result)
			throws Exception {

		if (result.hasErrors()) {

			// Manejo de errores de validación
			System.out.println(result);
			throw new Exception("Parámetros de inserción erróneos.");

		} else {

			// Inserción de la nueva noticia
			blogPostService.insert(newBlogPost);
		}

		return "redirect:showBlogPostsView";
	}

	/**
	 * Eliminar una noticia del listado.
	 * 
	 * @param BlogPostId
	 * @return String
	 */
	@PostMapping("/actDropBlogPost")
	public String deleteBlogPost(@RequestParam String blogPostId, Model model) {

		// Borrado de la noticia en la BBDD.
		blogPostService.delete(blogPostId);

		return "redirect:showBLogPostsView";
	}

}
