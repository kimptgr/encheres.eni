/**
 *
 */
package fr.eni.encheres.controller;

import fr.eni.encheres.bll.ArticleVenduService;
import fr.eni.encheres.bll.EnchereService;
import fr.eni.encheres.bll.contexte.ContexteService;
import fr.eni.encheres.bo.*;
import fr.eni.encheres.exceptions.BusinessException;
import fr.eni.encheres.exceptions.EnchereException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Classe en charge de
 *
 * @author : kpatingre2024
 * @projet : encheres - V1.0
 * @since: 9 sept. 2024 - 15:01:28
 */

@Controller
@SessionAttributes({"categoriesInSession"})
public class EncheresController {

    // injection de dépendance
    private ArticleVenduService articleVenduService;
    private ContexteService contexteService;
    private EnchereService enchereService;

    public EncheresController(ArticleVenduService articleVenduService, ContexteService contexteService,
                              EnchereService enchereService) {
        this.articleVenduService = articleVenduService;
        this.contexteService = contexteService;
        this.enchereService = enchereService;
    }

    @ModelAttribute("categoriesInSession")
    public List<Categorie> loadCategories() {
        System.out.println("Chargement en Session - CATEGORIES");

        return articleVenduService.findAllCategories();
    }

    @GetMapping
    public String afficherArticlesVendus(
            @RequestParam(name = "Categorie", required = false) String noCategorieParam,
            @RequestParam(name = "searchTerm", required = false) String searchTerm,
            @RequestParam(name = "ouvertes", required = false) String ouvertes,
            @RequestParam(name = "enCours", required = false) String enCours,
            @RequestParam(name = "remportees", required = false) String remportees,
            @RequestParam(name = "venteEncours", required = false) String venteEncours,
            @RequestParam(name = "venteNonDebutes", required = false) String venteNonDebutes,
            @RequestParam(name = "venteTerminees", required = false) String venteTerminees,
            Model model) {

        List<ArticleVendu> articlesVendus = articleVenduService.findArticlesFiltres(noCategorieParam, searchTerm, ouvertes, enCours, remportees, venteEncours, venteNonDebutes, venteTerminees);
        Utilisateur userInSession = contexteService.getUserInSession();
        if (userInSession != null)
            model.addAttribute("userInSession", userInSession);
        model.addAttribute("ArticlesVendus", articlesVendus);

        return "index";
    }

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

    @PostMapping("/detailArticle")
    public String makeAnEnchere(@RequestParam(name = "noArticle") int noArticle, @RequestParam("proposition") Integer proposition, Model model) {
        Utilisateur userInSession = contexteService.getUserInSession();
        if (userInSession != null && userInSession.getNoUtilisateur() >= 1) {
            try {
                var e = new Enchere();
                var av = articleVenduService.findById(noArticle);
                e.setArticleVendus(av);
                e.setDateEnchere(LocalDateTime.now());
                e.setMontantEnchere(proposition);
                e.setUtilisateur(userInSession);
                enchereService.addEnchere(e);
            } catch (EnchereException e) {
                model.addAttribute("articleVendu", articleVenduService.findById(noArticle));
                model.addAttribute("errorMessage", e.getMessage());
                return "view-detail-article";
            }
        }
        return "index";

    }


    @GetMapping("/vendreUnArticle")
    public String sell(Model model) {
        String currentUsernameInSession = contexteService.getUserInSession().getEmail();
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
    public String creerArticle(@Valid @ModelAttribute("articleVendu") ArticleVendu articleVendu, BindingResult bindingResult) {
        Utilisateur userInSession = contexteService.getUserInSession();
        if (userInSession != null && userInSession.getNoUtilisateur() > 0) {
            articleVendu.setVendeur(userInSession);
            articleVendu.setPrixVente(articleVendu.getPrixVente());
            if (bindingResult.hasErrors()) {
                return "view-create-article";
            }
            try {
                articleVenduService.add(articleVendu);
                return "redirect:/";
            } catch (BusinessException e) {
                e.getClefsExternalisations().forEach(key -> {
                    ObjectError error = new ObjectError("globalError", key);
                    bindingResult.addError(error);
                });

                return "view-create-article";
            }
        } else {
            System.out.println("Aucun utilisateur en session");
            return "redirect:/error";
        }
    }

    @ControllerAdvice
    public static class GlobalExceptionHandler {

        @ExceptionHandler(EnchereException.class)
        public String handleEnchereException(EnchereException ex, Model model) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "view-detail-article";
        }
    }
}
