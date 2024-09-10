package fr.eni.encheres.bll;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.repository.ArticleVenduDAO;

@Service
public class ArticleVenduServiceImpl implements ArticleVenduService {

////////////////////////////////////////////////////
	//injection de dépendance
	private ArticleVenduDAO articleVenduDAO;

	public ArticleVenduServiceImpl(ArticleVenduDAO articleVenduDAO) {
		super();
		this.articleVenduDAO = articleVenduDAO;
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
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


