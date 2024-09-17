/**
 * 
 */
package fr.eni.encheres.controller;

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

import fr.eni.encheres.bll.UtilisateurService;
import fr.eni.encheres.bll.contexte.ContexteService;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.controller.security.ContexteController;
import fr.eni.encheres.exceptions.BusinessException;
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
	public UtilisateurController(UtilisateurService utilisateurService, PasswordEncoder passwordEncoder, ContexteService contexteservice) {
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
		if (utilisateurService.verifByEmail(utilisateur.getEmail())) {
			bindingResult.rejectValue("email", "error.utilisateur", "L'email est déjà utilisé.");
			return "view-encheres-inscription";
		}
		if (!utilisateur.getMotDePasse().equals(mdpConfirm)) {
			bindingResult.rejectValue("motDePasse", "error.utilisateur", "Les mots de passe ne correspondent pas.");
			return "view-sinscrire";
		} else {
			try {
				String password = utilisateur.getMotDePasse();
				String encodedPassword = passwordEncoder.encode(password);
				utilisateur.setMotDePasse(encodedPassword);
				utilisateurService.addUser(utilisateur);

				return "redirect:/";
			} catch (BusinessException e) {
				// Afficher les messages d’erreur - il faut les injecter dans le contexte de
				// BindingResult
				e.getClefsExternalisations().forEach(key -> {
					ObjectError error = new ObjectError("error", key);
					bindingResult.addError(error);
				});

				return "view-encheres-inscription";
			}
		}
	}
	@GetMapping("/detailsProfil")
	public String afficherUnProfil(@RequestParam(name = "pseudo", required = true) String pseudo, Model model) {
		System.err.println(pseudo);
		if (!pseudo.isEmpty() && !pseudo.isBlank()) {
			Utilisateur utilisateur = utilisateurService.findByPseudo(pseudo);
			Utilisateur userInSession = contexteservice.getUserInSession();
			if (utilisateur != null && userInSession !=null) {
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
	 

}
