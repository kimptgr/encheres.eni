package fr.eni.encheres.repository;

import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;

public interface ArticleVenduDAO {
	
	void create(ArticleVendu articleVendu);
	
	List<ArticleVendu> readAll();
	
	ArticleVendu readById(Integer noArticle);
	
	ArticleVendu updateById(Integer noArticle);
	
	ArticleVendu deleteById(Integer noArticle);
	
	
}
