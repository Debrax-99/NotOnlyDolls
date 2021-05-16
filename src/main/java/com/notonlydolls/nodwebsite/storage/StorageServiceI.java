package com.notonlydolls.nodwebsite.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * Clase "Servicio" de guardado y recuperación de archivos
 * 
 * @author Ana Blanco Escudero
 * @since 06-05-21
 */
public interface StorageServiceI {

	/*
    Metodo para guardar los archivos
     */
    public void storePost(MultipartFile file);

    /*
    Metodo para cargar un archivo
     */
    public Resource loadPost(String filename);

    /*
    Metodo para Cargar todos los archivos
     */
    public Stream<Path> loadAllPosts();

    /*
    Metodo para Borrar un archivo
     */
    public String deleteFilePost(String filename);
    
    /*
    Metodo para guardar los archivos
     */
    public void storePhoto(MultipartFile file);

    /*
    Metodo para cargar un archivo
     */
    public Resource loadPhoto(String filename);

    /*
    Metodo para Cargar todos los archivos
     */
    public Stream<Path> loadAllPhotos();

    /*
    Metodo para Borrar un archivo
     */
    public String deleteFilePhoto(String filename);
}
