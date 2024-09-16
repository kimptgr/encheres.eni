package fr.eni.encheres.repository;

import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;

public interface ArticleVenduDAO {
	
	void create(ArticleVendu articleVendu);
	
	List<ArticleVendu> readAll();
	
	ArticleVendu readById(Integer noArticle);
	
	ArticleVendu updateById(Integer noArticle);
	
	ArticleVendu deleteById(Integer noArticle);

//	List<ArticleVendu> findByCategorie(Integer noCategorie);
//	
//	public List<ArticleVendu> findByNom(String searchTerm);
	
	public List<ArticleVendu> findFilteredArticles(Integer noCategorie, String searchTerm);

	ArticleVendu updatePrixVenteById(Integer noArticle, Integer prixVente);
}
