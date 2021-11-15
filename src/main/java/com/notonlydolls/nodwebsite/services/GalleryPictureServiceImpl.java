package com.notonlydolls.nodwebsite.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.notonlydolls.nodwebsite.repository.GalleryPicture;
import com.notonlydolls.nodwebsite.repository.GalleryPictureRepositoryI;

/**
 * Service class that manages gallery pictures
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
		galleryPictureRepository.save(entity);
		
	}

	@Override
	public List<GalleryPicture> searchAll() {
		final List<GalleryPicture> galleryPictureList = galleryPictureRepository.findAll();

		if (!CollectionUtils.isEmpty(galleryPictureList)) {
			for (GalleryPicture galleryPicture : galleryPictureList) {
				System.out.println(galleryPicture.toString());
			}
		} else {
			System.out.println("No existen fotos en la base de datos.");
		}
		return galleryPictureList;
	}

	@Override
	public Optional<GalleryPicture> searchById(String id) {
		final Optional<GalleryPicture> galleryPicture = galleryPictureRepository.findById(id);

		if (galleryPicture != null) {
			System.out.println("Se ha localizado la foto con ID " + id);
			System.out.println(galleryPicture.toString());
		} else {
			System.out.println("No existen fotos con ID " + id + " en la base de datos.");
		}
		return galleryPicture;
	}

	@Override
	public void delete(String id) {
		galleryPictureRepository.deleteById(id);
		
	}

	@Override
	public void update(GalleryPicture entity) {
		galleryPictureRepository.save(entity);
		
	}

}
