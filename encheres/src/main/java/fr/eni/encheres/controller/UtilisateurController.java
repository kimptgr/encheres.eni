/**
 * 
 */
package fr.eni.encheres.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.eni.encheres.bll.UtilisateurService;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Classe en charge de 
 * @projet : encheres - V1.0
 * @author : aferry2024
 * @since: 10 sept. 2024 - 11:00:33
 */
@Controller
@RequestMapping ()
public class UtilisateurController {

	private UtilisateurService utilisateurService;

	/**
	 * Constructeur.
	 * @param utilisateurService
	 */
	public UtilisateurController(UtilisateurService utilisateurService) {
		super();
		this.utilisateurService = utilisateurService;
	}
	@GetMapping("/create")
	public String addUtilisateur() {

		return "view-eni-encheres-create";
	}
	
	
	@PostMapping("/create")
	public String addUtilisateur(@ModelAttribute() Utilisateur utilisateur) {
		
		System.out.println(utilisateur);
		utilisateurService.addUser(utilisateur);
		return "redirect:/";
		
	}
}
