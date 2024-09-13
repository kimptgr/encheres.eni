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

import fr.eni.encheres.bll.UtilisateurService;
import fr.eni.encheres.bo.Utilisateur;
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

	/**
	 * Constructeur.
	 * 
	 * @param utilisateurService
	 * @param passwordEncoder
	 */
	public UtilisateurController(UtilisateurService utilisateurService, PasswordEncoder passwordEncoder) {
		this.utilisateurService = utilisateurService;
		this.passwordEncoder = passwordEncoder;

	}

	@GetMapping("/inscription")
	public String addUtilisateur(Model model) {
		model.addAttribute(new Utilisateur());
		return "view-encheres-inscription";
	}

	@PostMapping("/inscription")
	public String addUtilisateur(@Valid @ModelAttribute("utilisateur") Utilisateur utilisateur, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
				return "view-encheres-inscription";
			} else {
				try {
					String password = utilisateur.getMotDePasse();
					String encodedPassword = passwordEncoder.encode(password);
					utilisateur.setMotDePasse(encodedPassword);
					
					return "redirect:/";
				} catch (BusinessException e) {
					// Afficher les messages d’erreur - il faut les injecter dans le contexte de
					// BindingResult
					e.getClefsExternalisations().forEach(key -> {
						ObjectError error = new ObjectError("globalError", key);
						bindingResult.addError(error);
					});

					return "view-encheres-inscription";
				}
			}
		}
	}

