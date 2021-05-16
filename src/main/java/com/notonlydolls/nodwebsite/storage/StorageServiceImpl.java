package com.notonlydolls.nodwebsite.storage;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * Clase "Servicio" de guardado y recuperación de archivos
 * 
 * @author Ana Blanco Escudero
 * @since 06-05-21
 */
@Service
public class StorageServiceImpl implements StorageServiceI {
	
  	// Nombre de la carpeta donde vamos a almacenar las fotos de las noticias
    private final Path postsRoot = Paths.get("../resources/static/images/posts");

  	// Nombre de la carpeta donde vamos a almacenar las fotos de la galería
    private final Path photosRoot = Paths.get("../resources/static/images/photos");

    @Override
    public void storePost(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), 
                       this.postsRoot.resolve(file.getOriginalFilename()));
        } catch (IOException e) {
            throw new RuntimeException("No se puede guardar el archivo. Error " + e.getMessage());
        }
    }

    @Override
    public Resource loadPost(String filename) {
        try {
            Path file = postsRoot.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if(resource.exists() || resource.isReadable()){
                return resource;
            }else{
                throw new RuntimeException("No se puede leer el archivo ");
            }

        }catch (MalformedURLException e){
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public Stream<Path> loadAllPosts(){
        //Files.walk recorre nuestras carpetas (uploads) buscando los archivos
        // el 1 es la profundidad o nivel que queremos recorrer
        // :: Referencias a metodos
        // Relativize sirve para crear una ruta relativa entre la ruta dada y esta ruta
        try{
            return Files.walk(this.postsRoot,1).filter(path -> !path.equals(this.postsRoot))
                    .map(this.postsRoot::relativize);
        }catch (RuntimeException | IOException e){
            throw new RuntimeException("No se pueden cargar los archivos ");
        }
    }

    @Override
    public String deleteFilePost(String filename){
        try {
            Boolean delete = Files.deleteIfExists(this.postsRoot.resolve(filename));
                return "Borrado";
        }catch (IOException e){
            e.printStackTrace();
            return "Error Borrando ";
        }
    }
    
    @Override
    public void storePhoto(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), 
                       this.photosRoot.resolve(file.getOriginalFilename()));
        } catch (IOException e) {
            throw new RuntimeException("No se puede guardar el archivo. Error " + e.getMessage());
        }
    }

    @Override
    public Resource loadPhoto(String filename) {
        try {
            Path file = photosRoot.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if(resource.exists() || resource.isReadable()){
                return resource;
            }else{
                throw new RuntimeException("No se puede leer el archivo ");
            }

        }catch (MalformedURLException e){
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public Stream<Path> loadAllPhotos(){
        //Files.walk recorre nuestras carpetas (uploads) buscando los archivos
        // el 1 es la profundidad o nivel que queremos recorrer
        // :: Referencias a metodos
        // Relativize sirve para crear una ruta relativa entre la ruta dada y esta ruta
        try{
            return Files.walk(this.photosRoot,1).filter(path -> !path.equals(this.photosRoot))
                    .map(this.photosRoot::relativize);
        }catch (RuntimeException | IOException e){
            throw new RuntimeException("No se pueden cargar los archivos ");
        }
    }

    @Override
    public String deleteFilePhoto(String filename){
        try {
            Boolean delete = Files.deleteIfExists(this.postsRoot.resolve(filename));
                return "Borrado";
        }catch (IOException e){
            e.printStackTrace();
            return "Error Borrando ";
        }
    }
}
