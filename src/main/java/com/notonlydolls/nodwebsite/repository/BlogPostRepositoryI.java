package com.notonlydolls.nodwebsite.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Clase "Repositorio" que contiene los métodos propios específicos de consultas
 * y gestión de noticias del sitio web
 * 
 * @author Ana Blanco Escudero
 * @since 13-04-20
 */
@Repository
public interface BlogPostRepositoryI extends MongoRepository<BlogPost, String> {

}
