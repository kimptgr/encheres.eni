package fr.eni.encheres.bll;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.repository.ArticleVenduDAO;
import fr.eni.encheres.repository.CategorieDAO;
import fr.eni.encheres.repository.RetraitDAO;
import fr.eni.encheres.repository.UtilisateurDAO;

@Service
public class ArticleVenduServiceImpl implements ArticleVenduService {

////////////////////////////////////////////////////
	//injection de dépendance
	private ArticleVenduDAO articleVenduDAO;
	private CategorieDAO categorieDAO;
	private RetraitDAO retraitDAO;
	private UtilisateurDAO utilisateurDAO;

	public ArticleVenduServiceImpl(ArticleVenduDAO articleVenduDAO, CategorieDAO categorieDAO, RetraitDAO retraitDAO, UtilisateurDAO utilisateurDAO) {
		this.articleVenduDAO = articleVenduDAO;
		this.categorieDAO = categorieDAO;
		this.retraitDAO = retraitDAO;
		this.utilisateurDAO = utilisateurDAO;
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
		articleVendu.setPrixVente(articleVendu.getMiseAPrix());
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

	

//	@Override
//	public List<ArticleVendu> findByCategorie(Integer noCategorie) {
//	    return articleVenduDAO.findByCategorie(noCategorie);
//	
//	}


	@Override
	public Retrait findRetraitByNoArticle(Integer noArticle) {
		return retraitDAO.readByNoArticle(noArticle);
	}
	
	@Override
	public Retrait findDefaultRetraitByUser(String username) {
		Utilisateur userInSession = utilisateurDAO.readByEmail(username);
		Retrait defaultRetrait = new Retrait(userInSession.getRue(), userInSession.getCodePostal() , userInSession.getVille());
		return defaultRetrait;
	}


	@Override
	public List<ArticleVendu> findArticlesFiltres(String noCategorie, String searchTerm, String ouvertes,
			String enCours, String remportees, String venteEncours, String venteNonDebutes, String venteTerminees) {
		return articleVenduDAO.findFilteredArticles(noCategorie, searchTerm, ouvertes, enCours, remportees, venteEncours, venteNonDebutes, venteTerminees);
	}

}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


