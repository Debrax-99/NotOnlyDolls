package com.notonlydolls.nodwebsite.services.storage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service class that manages file storage
 * 
 * @author Ana Blanco Escudero
 * @since 06-05-21
 */
@Service
@PropertySource("classpath:nodwebsite.properties")
public class StorageServiceImpl implements StorageServiceI {

	// Posts pictures folder path	
	private final String POSTS_FOLDER;

	// Posts pictures folder path	
	private final String PHOTOS_FOLDER;

	// CONSTRUCTOR
	@Autowired
    public StorageServiceImpl(@Value("${posts_folder}") String posts, @Value("${photos_folder}") String photos) {
        this.POSTS_FOLDER = posts;
        this.PHOTOS_FOLDER = photos;

	}
		
	@Override
	public void storePost(MultipartFile file) {
		try {
			Files.copy(file.getInputStream(), Paths.get(POSTS_FOLDER + file.getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new RuntimeException("No se puede guardar el archivo. Error " + e.getMessage());
		}
	}

	@Override
	public Resource loadPost(String filename) {
		try {
			Path file = Paths.get(POSTS_FOLDER + filename);
			Resource resource = new UrlResource(file.toUri());

			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("No se puede leer el archivo ");
			}

		} catch (MalformedURLException e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}

	@Override
	public Stream<Path> loadAllPosts() {
		try {
			return Files.walk(Paths.get(POSTS_FOLDER), 1).filter(path -> !path.equals(Paths.get(POSTS_FOLDER)))
					.map(Paths.get(POSTS_FOLDER)::relativize);
		} catch (RuntimeException | IOException e) {
			throw new RuntimeException("No se pueden cargar los archivos ");
		}
	}

	@Override
	public String deleteFilePost(String filename) {
		try {
			Boolean delete = Files.deleteIfExists(Paths.get(POSTS_FOLDER + filename));
			return "Borrado";
		} catch (IOException e) {
			e.printStackTrace();
			return "Error Borrando ";
		}
	}

	@Override
	public void storePhoto(MultipartFile file) {
		try {
			Files.copy(file.getInputStream(), Paths.get(PHOTOS_FOLDER + file.getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new RuntimeException("No se puede guardar el archivo. Error " + e.getMessage());
		}
	}

	@Override
	public Resource loadPhoto(String filename) {
		try {
			Path file = Paths.get(PHOTOS_FOLDER).resolve(filename);
			Resource resource = new UrlResource(file.toUri());

			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("No se puede leer el archivo ");
			}

		} catch (MalformedURLException e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}

	@Override
	public Stream<Path> loadAllPhotos() {
		try {
			return Files.walk(Paths.get(PHOTOS_FOLDER), 1).filter(path -> !path.equals(Paths.get(PHOTOS_FOLDER)))
					.map(Paths.get(PHOTOS_FOLDER)::relativize);
		} catch (RuntimeException | IOException e) {
			throw new RuntimeException("No se pueden cargar los archivos ");
		}
	}

	@Override
	public String deleteFilePhoto(String filename) {
		try {
			Boolean delete = Files.deleteIfExists(Paths.get(PHOTOS_FOLDER + filename));
			return "Borrado";
		} catch (IOException e) {
			e.printStackTrace();
			return "Error Borrando ";
		}
	}
}
