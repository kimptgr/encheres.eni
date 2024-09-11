package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;

public interface ArticleVenduService {
	
	List<ArticleVendu> findAllArticles();
	
	void add(ArticleVendu articleVendu);

	List<Categorie> findAllCategories();
	
	ArticleVendu findById(Integer noArticle);
	
	Categorie findCategorieById(Integer noCategorie);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
