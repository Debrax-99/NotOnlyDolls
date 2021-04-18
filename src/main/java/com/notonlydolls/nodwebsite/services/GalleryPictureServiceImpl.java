package com.notonlydolls.nodwebsite.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.notonlydolls.nodwebsite.repository.GalleryPicture;
import com.notonlydolls.nodwebsite.repository.GalleryPictureRepositoryI;

/**
 * Clase "Servicio" de consultas y gestión de fotos de la galería
 * 
 * @author Ana Blanco Escudero
 * @since 17-04-21
 */
@Service
public class GalleryPictureServiceImpl implements GalleryPictureServiceI {
	
	/** Repositorio: gallery_picture */
	@Autowired
	private GalleryPictureRepositoryI galleryPictureRepository;

	/** Métodos CRUD */
	@Override
	public void insert(GalleryPicture entity) {
		System.out.println("********************** INSERT **********************");
		galleryPictureRepository.save(entity);
		System.out.println("****************************************************");
		
	}

	@Override
	public List<GalleryPicture> searchAll() {
		System.out.println("******************** SEARCH ALL ********************");
		final List<GalleryPicture> galleryPictureList = galleryPictureRepository.findAll();

		if (!CollectionUtils.isEmpty(galleryPictureList)) {
			for (GalleryPicture galleryPicture : galleryPictureList) {
				System.out.println(galleryPicture.toString());
			}
		} else {
			System.out.println("No existen fotos en la base de datos.");
		}
		System.out.println("****************************************************");
		return galleryPictureList;
	}

	@Override
	public Optional<GalleryPicture> searchById(String id) {
		System.out.println("******************* SEARCH BY ID *******************");
		final Optional<GalleryPicture> galleryPicture = galleryPictureRepository.findById(id);

		if (galleryPicture != null) {
			System.out.println("Se ha localizado la foto con ID " + id);
			System.out.println(galleryPicture.toString());
			System.out.println("****************************************************");
		} else {
			System.out.println("No existen fotos con ID " + id + " en la base de datos.");
		}
		return galleryPicture;
	}

	@Override
	public void delete(String id) {
		System.out.println("********************** DELETE **********************");
		galleryPictureRepository.deleteById(id);
		System.out.println("****************************************************");
		
	}

	@Override
	public void update(GalleryPicture entity) {
		System.out.println("********************** UPDATE **********************");
		galleryPictureRepository.save(entity);
		System.out.println("****************************************************");
		
	}

}
