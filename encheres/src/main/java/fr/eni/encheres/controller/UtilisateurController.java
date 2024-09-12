/**
 * 
 */
package fr.eni.encheres.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.eni.encheres.bll.UtilisateurService;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Classe en charge de
 * 
 * @projet : encheres - V1.0
 * @author : aferry2024
 * @since: 10 sept. 2024 - 11:00:33
 */
@Controller
@RequestMapping()
public class UtilisateurController {

	private UtilisateurService utilisateurService;
	private PasswordEncoder passwordEncoder;

	/**
	 * Constructeur.
	 * 
	 * @param utilisateurService
	 * @param passwordEncoder
	 */
	public UtilisateurController(UtilisateurService utilisateurService, PasswordEncoder passwordEncoder) {
		super();
		this.utilisateurService = utilisateurService;
		this.passwordEncoder = passwordEncoder;
	}

	@GetMapping("/inscription")
	public String addUtilisateur() {

		return "view-encheres-inscription";
	}

	@PostMapping("/inscription")
	public String addUtilisateur(@ModelAttribute Utilisateur utilisateur) {
		System.err.println(utilisateur.getMotDePasse());
		String password = utilisateur.getMotDePasse();
		String encodedPassword = passwordEncoder.encode(password);
		// System.err.println(encodedPassword);

		System.err.println(utilisateur);
		utilisateur.setMotDePasse(encodedPassword);
		utilisateurService.addUser(utilisateur);

		return "redirect:/";

	}
}
