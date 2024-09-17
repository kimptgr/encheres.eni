package fr.eni.encheres.repository;

import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;

public interface ArticleVenduDAO {
	
	void create(ArticleVendu articleVendu);
	
	List<ArticleVendu> readAll();
	
	ArticleVendu readById(Integer noArticle);
	
	ArticleVendu updateById(Integer noArticle);
	
	ArticleVendu deleteById(Integer noArticle);

//	List<ArticleVendu> findByCategorie(Integer noCategorie);
//	
//	public List<ArticleVendu> findByNom(String searchTerm);
	

	List<ArticleVendu> findFilteredArticles(String noCategorie, String searchTerm, String ouvertes,String enCours,String remportees,String venteEncours,String venteNonDebutes,String venteTerminees) ;

	void updatePrixVenteById(Integer noArticle, Integer prixVente);

}
