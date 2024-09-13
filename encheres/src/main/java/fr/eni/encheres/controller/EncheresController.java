/**
 * 
 */
package fr.eni.encheres.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.eni.encheres.bll.ArticleVenduService;
import fr.eni.encheres.bll.UtilisateurService;
import fr.eni.encheres.bll.contexte.ContexteService;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.exceptions.BusinessException;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


/**
 * Classe en charge de 
 * @projet : encheres - V1.0
 * @author : kpatingre2024
 * @since: 9 sept. 2024 - 15:01:28
 */

@Controller
@SessionAttributes({ "categoriesInSession", "userInSession" })
public class EncheresController {

	// injection de dépendance
	private ArticleVenduService articleVenduService;
	private ContexteService contexteService;

	public EncheresController(ArticleVenduService articleVenduService, ContexteService contexteService) {
		this.articleVenduService = articleVenduService;
		this.contexteService = contexteService;
	}

	@ModelAttribute("categoriesInSession")
	public List<Categorie> loadCategories() {
		System.out.println("Chargement en Session - CATEGORIES");

		return articleVenduService.findAllCategories();
	}

//#######--BLOC A SUPPRIMER QUAND USERINSESSION D'ALEX--#################################################
//	@ModelAttribute("userInSession")
//	public Utilisateur testuis() {
//		System.out.println("Chargement en Session - CATEGORIES");
//		Utilisateur userTest = new Utilisateur();
//		userTest.setNoUtilisateur(1);
//		userTest.setRue("chemin des saules");
//		userTest.setVille("MaVille");
//		userTest.setCodePostal("90000");
//		return userTest;
//	}
//########--FIN BLOC A SUPPRIMER QUAND USERINSESSION D'ALEX--################################################

	@GetMapping
	public String afficherArticlesVendus(@RequestParam(name = "Categorie", required = false) Integer noCategorie,Model model) {
		List<ArticleVendu> articlesVendus;
		if (noCategorie != null) {
			articlesVendus = articleVenduService.findByCategorie(noCategorie);
		}else {
			articlesVendus = articleVenduService.findAllArticles();
		
		}
		model.addAttribute("ArticlesVendus", articlesVendus);
		
		return "index"; }

		
	@GetMapping("/detailArticle")
	public String afficherUnArticle(@RequestParam(name = "noArticle", required = true) Integer noArticle, Model model) {
		if (noArticle > 0) {
			ArticleVendu articleVendu = articleVenduService.findById(noArticle);
			if (articleVendu != null) {
				model.addAttribute("articleVendu", articleVendu);
				return "view-detail-article";
			} else
				System.out.println("Article inconnu!!");
		} else {
			System.out.println("Identifiant inconnu");
		}
		return "redirect:/view-detail-article";
	}


	@GetMapping("/vendreUnArticle")
	public String sell(HttpSession session, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUsernameInSession = authentication.getName();
		ArticleVendu articleVendu = new ArticleVendu();
	    
		if (!currentUsernameInSession.isBlank()) {
	    	Retrait defaultRetrait = articleVenduService.findDefaultRetraitByUser(currentUsernameInSession);
	        articleVendu.setRetrait(defaultRetrait);
	    } else {
	        System.out.println("Aucun utilisateur en session.");
	    }
		model.addAttribute("articleVendu", articleVendu);
		return "view-create-article";
	}

	@PostMapping("/vendreUnArticle")
	public String creerArticle(@Valid @ModelAttribute("articleVendu") ArticleVendu articleVendu,BindingResult bindingResult) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUsernameInSession = authentication.getName();
		Utilisateur userInSession = contexteService.chargeEmail(currentUsernameInSession);
		if (userInSession != null && userInSession.getNoUtilisateur() > 0) {
	        articleVendu.setVendeur(userInSession);
	        articleVendu.setPrixVente(articleVendu.getPrixVente());

	        if (bindingResult.hasErrors()) {
	            // Si des erreurs de validation existent, retourne la vue de création d'article
	            return "view-create-article";
	        }

	        try {
	            articleVenduService.add(articleVendu);
	            return "redirect:/"; // Redirection vers la page d'accueil ou une autre page après succès
	        } catch (BusinessException e) {
	            // Ajoute les erreurs globales au BindingResult
	            e.getClefsExternalisations().forEach(key -> {
	                ObjectError error = new ObjectError("globalError", key);
	                bindingResult.addError(error);
	            });

	            // Retourne la vue de création d'article avec les erreurs
	            return "view-create-article";
	        }
	    } else {
	        System.out.println("Aucun utilisateur en session");
	        // Optionnel : Redirection ou affichage d'une erreur spécifique
	        return "redirect:/error"; // Par exemple, redirection vers une page d'erreur
	    }
	}
	
	
}
