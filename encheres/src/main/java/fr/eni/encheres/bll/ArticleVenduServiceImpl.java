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
		return articles;
	}


	@Override
	public void add(ArticleVendu articleVendu) {
		articleVenduDAO.create(articleVendu);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
