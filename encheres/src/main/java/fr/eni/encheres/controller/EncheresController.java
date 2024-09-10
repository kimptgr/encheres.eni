/**
 * 
 */
package fr.eni.encheres.controller;




import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.eni.encheres.bll.ArticleVenduService;
import fr.eni.encheres.bo.ArticleVendu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Classe en charge de 
 * @projet : encheres - V1.0
 * @author : kpatingre2024
 * @since: 9 sept. 2024 - 15:01:28
 */

@Controller
public class EncheresController {
	
	
	//injection de dépendance
	private ArticleVenduService articleVenduService;
	
	public EncheresController(ArticleVenduService articleVenduService) {
		super();
		this.articleVenduService = articleVenduService;
	}

	

	@GetMapping
	public String afficherArticlesVendus(Model model) {
		System.out.println("\nTous les articles: ");
		List<ArticleVendu> articlesVendus = articleVenduService.findAllArticles();
		// Ajout de l'article dans le modèle
		model.addAttribute("ArticlesVendus", articlesVendus);
		return "index";
	}
	
	

}
