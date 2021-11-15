package com.notonlydolls.nodwebsite.services.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * Service class that manages file storage
 * 
 * @author Ana Blanco Escudero
 * @since 06-05-21
 */
public interface StorageServiceI {

	/*
	 * Store post picture
     */
    public void storePost(MultipartFile file);

    /*
     * Retrieve post picture
     */
    public Resource loadPost(String filename);

    /*
     * Retrieve all post pictures
     */
    public Stream<Path> loadAllPosts();

    /*
     * Delete post picture
     */
    public String deleteFilePost(String filename);
    
    /*
     * Store gallery picture
     */
    public void storePhoto(MultipartFile file);

    /*
     * Retrieve gallery picture
     */
    public Resource loadPhoto(String filename);

    /*
     * Retrieve all gallery pictures
     */
    public Stream<Path> loadAllPhotos();

    /*
     * Delete gallery picture
     */
    public String deleteFilePhoto(String filename);
}
