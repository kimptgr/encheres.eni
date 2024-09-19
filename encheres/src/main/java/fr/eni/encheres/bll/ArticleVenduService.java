package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Retrait;

public interface ArticleVenduService {
	
	List<ArticleVendu> findAllArticles();
	
	void add(ArticleVendu articleVendu);

	List<Categorie> findAllCategories();
	
	ArticleVendu findById(Integer noArticle);
	
	Categorie findCategorieById(Integer noCategorie);

	Retrait findRetraitByNoArticle(Integer noArticle);

	Retrait findDefaultRetraitByUser(String username);

//	List<ArticleVendu> findByCategorie(Integer noCategorie);
	
	List<ArticleVendu> findArticlesFiltres(String noCategorie, String searchTerm, String ouvertes,String enCours,String remportees,String venteEncours,String venteNonDebutes,String venteTerminees);

	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
