package com.notonlydolls.nodwebsite.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.notonlydolls.nodwebsite.repository.BlogPost;
import com.notonlydolls.nodwebsite.repository.BlogPostRepositoryI;

/**
 * Service class that manages blog posts
 * 
 * @author Ana Blanco Escudero
 * @since 15-04-21
 */
@Service
public class BlogPostServiceImpl implements BlogPostServiceI {
	
	/** Repository: blog_post */
	@Autowired
	private BlogPostRepositoryI blogPostRepository;

	/** CRUD */
	@Override
	public void insert(BlogPost entity) {
		System.out.println("********************** INSERT **********************");
		blogPostRepository.save(entity);
		System.out.println("****************************************************");
		
	}

	@Override
	public List<BlogPost> searchAll() {
		System.out.println("******************** SEARCH ALL ********************");
		final List<BlogPost> blogPostList = blogPostRepository.findAll();

		if (!CollectionUtils.isEmpty(blogPostList)) {
			for (BlogPost blogPost : blogPostList) {
				System.out.println(blogPost.toString());
			}
		} else {
			System.out.println("No existen noticias en la base de datos.");
		}
		System.out.println("****************************************************");
		return blogPostList;
	}

	@Override
	public Optional<BlogPost> searchById(final String id) {
		System.out.println("******************* SEARCH BY ID *******************");
		final Optional<BlogPost> blogPost = blogPostRepository.findById(id);

		if (blogPost != null) {
			System.out.println("Se ha localizado la noticia con ID " + id);
			System.out.println(blogPost.toString());
			System.out.println("****************************************************");
		} else {
			System.out.println("No existen noticias con ID " + id + " en la base de datos.");
		}
		return blogPost;
	}

	@Override
	public void delete(final String id) {
		System.out.println("********************** DELETE **********************");
		blogPostRepository.deleteById(id);
		System.out.println("****************************************************");
		
	}

	@Override
	public void update(BlogPost entity) {
		System.out.println("********************** UPDATE **********************");
		blogPostRepository.save(entity);
		System.out.println("****************************************************");
		
	}

}
