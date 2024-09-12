package fr.eni.encheres.bll;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.repository.ArticleVenduDAO;
import fr.eni.encheres.repository.CategorieDAO;

@Service
public class ArticleVenduServiceImpl implements ArticleVenduService {

////////////////////////////////////////////////////
	//injection de dépendance
	private ArticleVenduDAO articleVenduDAO;
	private CategorieDAO categorieDAO;

	public ArticleVenduServiceImpl(ArticleVenduDAO articleVenduDAO, CategorieDAO categorieDAO) {
		this.articleVenduDAO = articleVenduDAO;
		this.categorieDAO = categorieDAO;
	}
////////////////////////////////////////////////////


	@Override
	public List<ArticleVendu> findAllArticles() {
	    List<ArticleVendu> articles = articleVenduDAO.readAll();
	    if (articles != null) {
	        articles.forEach(System.out::println);
	    } else {
	        System.out.println("Aucun article trouvé");
	    }
	    return articles;
	}
		
	

	@Override
	public void add(ArticleVendu articleVendu) {
		articleVenduDAO.create(articleVendu);
	}


	@Override
	public List<Categorie> findAllCategories() {
		
		return categorieDAO.readAll();
		}

	
	@Override
	public ArticleVendu findById(Integer noArticle) {
		return articleVenduDAO.readById(noArticle);
	}


	@Override
	public Categorie findCategorieById(Integer noCategorie) {
		return categorieDAO.readById(noCategorie);
	}
	

	@Override
	public List<ArticleVendu> findByCategorie(Integer noCategorie) {
	    return articleVenduDAO.findByCategorie(noCategorie);
	
	}
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


