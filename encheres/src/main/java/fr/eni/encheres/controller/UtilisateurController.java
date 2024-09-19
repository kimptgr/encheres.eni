/**
 * 
 */
package fr.eni.encheres.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.eni.encheres.bll.UtilisateurService;
import fr.eni.encheres.bll.contexte.ContexteService;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.controller.security.ContexteController;
import fr.eni.encheres.exceptions.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

/**
 * Classe en charge de
 * 
 * @projet : encheres - V1.0
 * @author : aferry2024
 * @since: 10 sept. 2024 - 11:00:33
 */
@Controller
@RequestMapping()
@SessionAttributes({ "userInSession" })
public class UtilisateurController {

	private UtilisateurService utilisateurService;
	private PasswordEncoder passwordEncoder;
	private ContexteService contexteservice;

	/**
	 * Constructeur.
	 * 
	 * @param utilisateurService
	 * @param passwordEncoder
	 */
	public UtilisateurController(UtilisateurService utilisateurService, PasswordEncoder passwordEncoder,
			ContexteService contexteservice) {
		this.utilisateurService = utilisateurService;
		this.passwordEncoder = passwordEncoder;
		this.contexteservice = contexteservice;

	}

	@GetMapping("/inscription")
	public String addUtilisateur(Model model) {

		model.addAttribute(new Utilisateur());
		return "view-encheres-inscription";
	}

	@PostMapping("/inscription")
	public String addUtilisateur(@Valid @ModelAttribute("utilisateur") Utilisateur utilisateur,
			BindingResult bindingResult, @RequestParam("mdpConfirm") String mdpConfirm, Model model) {
		if (bindingResult.hasErrors()) {
			return "view-encheres-inscription";

		}
		if (utilisateurService.verifByPseudo(utilisateur.getPseudo())) {
			bindingResult.rejectValue("pseudo", "error.utilisateur", "Le pseudo est déjà pris.");

			if (utilisateurService.verifByEmail(utilisateur.getEmail())) {
				bindingResult.rejectValue("email", "error.utilisateur", "L'email est déjà utilisé.");

				if (!utilisateur.getMotDePasse().equals(mdpConfirm)) {
					bindingResult.rejectValue("motDePasse", "error.utilisateur",
							"Les mots de passe ne correspondent pas.");
				}
			}
			return "view-encheres-inscription";
		} else {
			String password = utilisateur.getMotDePasse();
			String encodedPassword = passwordEncoder.encode(password);
			utilisateur.setMotDePasse(encodedPassword);
			utilisateurService.addUser(utilisateur);

			return "redirect:/";
		}
	}

	@GetMapping("/detailsProfil")
	public String afficherUnProfil(@RequestParam(name = "pseudo", required = true) String pseudo, Model model,
			Principal principal) {
		System.err.println(pseudo);
		if (!pseudo.isEmpty() && !pseudo.isBlank()) {
			Utilisateur utilisateur = utilisateurService.findByPseudo(pseudo);

			Utilisateur userInSession = contexteservice.getUserInSession();
			if (utilisateur != null && userInSession != null) {
				model.addAttribute("utilisateur", utilisateur);
				model.addAttribute("userInSession", userInSession);

				return "view-detail-user";
			} else
				System.out.println("Utilisateur inconnu!!");
		} else {
			System.out.println("Identifiant inconnu");
		}
		return "redirect:/";
	}

	@PostMapping("/detailsProfil/modifierProfil")
	public String afficherFormulaireModification(@RequestParam(name = "pseudo", required = true) String pseudo,
			Model model) {
		Utilisateur utilisateur = utilisateurService.findByPseudo(pseudo);
		Utilisateur userInSession = contexteservice.getUserInSession();
		if (utilisateur != null && userInSession != null) {
			model.addAttribute("utilisateur", utilisateur);
			model.addAttribute("userInSession", userInSession);
			
			return "view-encheres-modify-user";
		}
		return "redirect:/";

	}

}
