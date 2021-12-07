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
import org.springframework.web.multipart.MultipartFile;

import com.notonlydolls.nodwebsite.repository.BlogPost;
import com.notonlydolls.nodwebsite.repository.GalleryPicture;
import com.notonlydolls.nodwebsite.services.BlogPostServiceI;
import com.notonlydolls.nodwebsite.services.GalleryPictureServiceI;
import com.notonlydolls.nodwebsite.services.storage.StorageServiceI;

/**
 * Clase "Controlador" que controla las peticiones relativas a la administración
 * del sitio web y gestión de noticias y fotos
 * 
 * @author Ana Blanco Escudero
 * @since 22-04-21
 */
@Controller
public class AdminController {

	/** Servicio de consultas y gestión de noticias del blog */
	@Autowired
	private BlogPostServiceI blogPostService;

	/** Servicio de consultas y gestión de fotos de la galería */
	@Autowired
	private GalleryPictureServiceI galleryPictureService;

	/** Servicio de guardado y recuperación de archivos */
	@Autowired
	private StorageServiceI storageService;

	/**
	 * Mostrar el panel de administración del Backoffice
	 * 
	 * @param model
	 * @return String
	 */
	@GetMapping("/admin/showAdminView")
	public String showAdminView(Model model) {

		return "admin";
	}

	/**
	 * ====================================== BLOG POSTS
	 * ======================================
	 */

	/**
	 * Mostrar el listado de todas las noticias del blog.
	 * 
	 * @param model
	 * @return String
	 */
	@GetMapping("admin/posts")
	public String showBlogPosts(Model model) {

		// Obtención del listado de noticias de la BBDD.
		final List<BlogPost> blogPostList = blogPostService.searchAll();

		// Carga de datos al modelo.
		model.addAttribute("blogPostListView", blogPostList);
		model.addAttribute("btnDropBlogPostEnabled", Boolean.FALSE);

		return "admin/posts";
	}

	/**
	 * Redirección a la vista de inserción de noticias.
	 * 
	 * @return String
	 */
	@GetMapping("admin/posts/add-post")
	public String redirectToAddBlogPostView() {
		return "admin/posts/add-post";
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
	private String submitInserBlogPostForm(@Valid @ModelAttribute BlogPost newBlogPost,
			@RequestParam("file") MultipartFile file, BindingResult result) throws Exception {

		if (result.hasErrors()) {

			// Manejo de errores de validación
			System.out.println(result);
			throw new Exception("Parámetros de inserción erróneos.");

		} else {

			if (!file.isEmpty()) {
				// Asignación del nombre de archivo al atributo image de la noticia.
				newBlogPost.setImage(file.getOriginalFilename());

				// Guardado de la nueva imagen
				storageService.storePost(file);
			}
			// Creación del slug para la noticia
			newBlogPost.setSlug(newBlogPost.constructSlug(newBlogPost.getTitle()));

			// Inserción de la nueva noticia
			blogPostService.insert(newBlogPost);

		}

		return "redirect:admin/posts";
	}

	/**
	 * Redirección a la vista de edición de noticias.
	 * 
	 * @return String
	 */
	@PostMapping("admin/posts/edit-post")
	public String redirectToEditBlogPostView(@RequestParam String blogPostId, Model model) {
		// Búsqueda de la noticia en BBDD.
		final BlogPost blogPost = blogPostService.searchById(blogPostId).get();

		// Carga de datos al modelo.
		model.addAttribute("blogPost", blogPost);

		return "admin/posts/edit-post";
	}

	/**
	 * Actualizar una noticia con todos sus datos validados.
	 * 
	 * @param newBlogPost
	 * @param result
	 * @return String
	 * @throws Exception
	 */
	@PostMapping("/actEditBlogPost")
	private String submitEditBlogPostForm(@RequestParam String blogPostId,
			@Valid @ModelAttribute BlogPost editedBlogPost, @RequestParam("file") MultipartFile file,
			BindingResult result) throws Exception {

		if (result.hasErrors()) {

			// Manejo de errores de validación.
			System.out.println(result);
			throw new Exception("Parámetros de inserción erróneos.");

		} else {

			if (!file.isEmpty()) {
				// Asignación del nombre de archivo al atributo image de la noticia.
				editedBlogPost.setImage(file.getOriginalFilename());

				// Guardado de la nueva imagen
				storageService.storePost(file);
			}
			editedBlogPost.setPersisted(true);
			editedBlogPost.setId(blogPostId);
			editedBlogPost.setCreationDate(blogPostService.searchById(blogPostId).get().getCreationDate());
			editedBlogPost.setSlug(editedBlogPost.constructSlug(editedBlogPost.getTitle()));

			// Actualización de la noticia.
			blogPostService.update(editedBlogPost);
		}

		return "redirect:admin/posts";
	}

	/**
	 * Eliminar una noticia del listado.
	 * 
	 * @param BlogPostId
	 * @return String
	 */
	@PostMapping("/actDropBlogPost")
	public String deleteBlogPost(@RequestParam String blogPostId, Model model) {
		// Búsqueda de la noticia en la BBDD.
		final BlogPost blogPostToDelete = blogPostService.searchById(blogPostId).get();

		// Borrado de la noticia en la BBDD.
		blogPostService.delete(blogPostId);

		//
		storageService.deleteFilePhoto(blogPostToDelete.getImage());

		return "redirect:admin/posts";
	}

	/**
	 * ====================================== GALLERY PICTURES ======================================
	 */

	/**
	 * Mostrar el listado de todas las fotos de la galería.
	 * 
	 * @param model
	 * @return String
	 */
	@GetMapping("admin/photos")
	public String showGalleryPictures(Model model) {

		// Obtención del listado de fotos de la BBDD.
		final List<GalleryPicture> galleryPictureList = galleryPictureService.searchAll();

		// Carga de datos al modelo.
		model.addAttribute("galleryPictureListView", galleryPictureList);
		model.addAttribute("btnDropGalleryPictureEnabled", Boolean.FALSE);

		return "admin/photos";
	}

	/**
	 * Redirección a la vista de inserción de nuevas fotos.
	 * 
	 * @return String
	 */
	@GetMapping("admin/photos/add-photo")
	public String redirectToAddGalleryPictureView() {
		return "admin/photos/add-photo";
	}

	/**
	 * Añadir una nueva foto con todos sus datos validados.
	 * 
	 * @param newGalleryPicture
	 * @param result
	 * @return String
	 * @throws Exception
	 */
	@PostMapping("/actInsertGalleryPicture")
	private String submitInsertGalleryPictureForm(@Valid @ModelAttribute GalleryPicture newGalleryPicture,
			@RequestParam("file") MultipartFile file, BindingResult result) throws Exception {

		if (result.hasErrors()) {

			// Manejo de errores de validación
			System.out.println(result);
			throw new Exception("Parámetros de inserción erróneos.");

		} else {

			if (!file.isEmpty()) {
				// Asignación del nombre de archivo al atributo image de la foto.
				newGalleryPicture.setImage(file.getOriginalFilename());

				// Guardado de la nueva imagen
				storageService.storePhoto(file);
			}

			// Inserción de la nueva foto
			galleryPictureService.insert(newGalleryPicture);
		}

		return "redirect:admin/photos";
	}

	/**
	 * Redirección a la vista de edición de fotos de la galería.
	 * 
	 * @return String
	 */
	@PostMapping("admin/photos/edit-photo")
	public String redirectToEditGalleryPictureView(@RequestParam String galleryPictureId, Model model) {
		// Búsqueda de la foto en la BBDD.
		final GalleryPicture editedGalleryPicture = galleryPictureService.searchById(galleryPictureId).get();

		// Carga de datos al modelo.
		model.addAttribute("galleryPicture", editedGalleryPicture);

		return "admin/photos/edit-photo";
	}

	/**
	 * Actualizar una foto con todos sus datos validados.
	 * 
	 * @param newBlogPost
	 * @param result
	 * @return String
	 * @throws Exception
	 */
	@PostMapping("/actEditGalleryPicture")
	private String submitEditGalleryPictureForm(@RequestParam String galleryPictureId,
			@Valid @ModelAttribute GalleryPicture editedgalleryPicture, @RequestParam("file") MultipartFile file,
			BindingResult result) throws Exception {

		if (result.hasErrors()) {

			// Manejo de errores de validación
			System.out.println(result);
			throw new Exception("Parámetros de inserción erróneos.");

		} else {
			if (!file.isEmpty()) {
				// Asignación del nombre de archivo al atributo image de la foto.
				editedgalleryPicture.setImage(file.getOriginalFilename());

				// Guardado de la nueva imagen.
				storageService.storePhoto(file);
			}
			editedgalleryPicture.setPersisted(true);
			editedgalleryPicture.setId(galleryPictureId);
			editedgalleryPicture.setCreationDate(galleryPictureService.searchById(galleryPictureId).get().getCreationDate());

			// Actualización de la foto.
			galleryPictureService.update(editedgalleryPicture);

		}

		return "redirect:admin/photos";
	}

	/**
	 * Eliminar una foto del listado.
	 * 
	 * @param galleryPictureId
	 * @return String
	 */
	@PostMapping("/actDropGalleryPicture")
	public String deleteGalleryPicture(@RequestParam String galleryPictureId, Model model) {
		// Búsqueda de la foto en la BBDD.
		final GalleryPicture galleryPictureToDelete = galleryPictureService.searchById(galleryPictureId).get();

		// Borrado de la foto en la BBDD.
		galleryPictureService.delete(galleryPictureId);

		storageService.deleteFilePhoto(galleryPictureToDelete.getImage());

		return "redirect:admin/photos";
	}

}
