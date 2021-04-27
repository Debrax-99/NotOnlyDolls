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

import com.notonlydolls.nodwebsite.repository.GalleryPicture;
import com.notonlydolls.nodwebsite.services.GalleryPictureServiceI;

/**
 * Clase "Controlador" que controla las peticiones relativas a la gestión de
 * fotos de la galería
 * 
 * @author Ana Blanco Escudero
 * @since 24-04-21
 */
@Controller
public class GalleryPictureController {

	/** Servicio de consultas y gestión de fotos de la galería */
	@Autowired
	private GalleryPictureServiceI galleryPictureService;

	/**
	 * Mostrar el listado de todas las fotos de la galería.
	 * 
	 * @param model
	 * @return String
	 */
	@GetMapping("/showGalleryPicturesView")
	public String showGalleryPictures(Model model) {

		// Obtención del listado de fotos de la BBDD.
		final List<GalleryPicture> galleryPictureList = galleryPictureService.searchAll();

		// Carga de datos al modelo.
		model.addAttribute("galleryPictureListView", galleryPictureList);
		model.addAttribute("btnDropGalleryPictureEnabled", Boolean.FALSE);

		return "photos";
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
	private String submitInsertGalleryPictureForm(@Valid @ModelAttribute GalleryPicture newGalleryPicture, BindingResult result)
			throws Exception {

		if (result.hasErrors()) {

			// Manejo de errores de validación
			System.out.println(result);
			throw new Exception("Parámetros de inserción erróneos.");

		} else {

			// Inserción de la nueva foto
			galleryPictureService.insert(newGalleryPicture);
		}

		return "redirect:showGalleryPicturesView";
	}

	/**
	 * Eliminar una foto del listado.
	 * 
	 * @param galleryPictureId
	 * @return String
	 */
	@PostMapping("/actDropGalleryPicture")
	public String deleteGalleryPicture(@RequestParam String galleryPictureId, Model model) {

		// Borrado de la foto en la BBDD.
		galleryPictureService.delete(galleryPictureId);

		return "redirect:showGalleryPicturesView";
	}
}
