/**
 * 
 */
package fr.eni.encheres.controller.security;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.eni.encheres.bll.contexte.ContexteService;
import fr.eni.encheres.bo.Utilisateur;





/**
 * Classe en charge de 
 * @projet : encheres - V1.0
 * @author : aferry2024
 * @since: 11 sept. 2024 - 09:44:22
 */
@Controller
@SessionAttributes({"userInSession"})
public class ContexteController {
private ContexteService contexteService;

/**
 * Constructeur.
 * @param contexteService
 */
public ContexteController(ContexteService contexteService) {
	super();
	this.contexteService = contexteService;
	}

@GetMapping("/connexion")
public String connectionUtilisateur(){
	return "view-encheres-connexion";
}


@GetMapping("/session")
public String chargeUserInSession(@ModelAttribute("userInSession") Utilisateur userInSession, Principal principal){
	String email = principal.getName();
	Utilisateur charge = contexteService.chargeEmail(email);
	if(charge != null) {
		userInSession.setNoUtilisateur(charge.getNoUtilisateur());
		userInSession.setEmail(charge.getEmail());
		userInSession.setNom(charge.getNom());
		userInSession.setPrenom(charge.getPrenom());
		userInSession.setRue(charge.getRue());
		userInSession.setVille(charge.getVille());
		userInSession.setCodePostal(charge.getCodePostal());
		System.err.println("--- Chargement de l'utilisateur en Session ---");
		
	}else {
		userInSession.setNoUtilisateur(0);
		userInSession.setEmail(null);
		userInSession.setNom(null);
		userInSession.setPrenom(null);
		userInSession.setAdministrateur(false);
	}
	System.out.println(userInSession);
	return "redirect/:";
	
}

@ModelAttribute("userInSession")
public Utilisateur userInSession() {
	System.out.println("Add Attribut Session");
	return new Utilisateur();
}

private Utilisateur getUserInSession() {
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	String currentUsernameInSession = authentication.getName();
	Utilisateur userInSession = contexteService.chargeEmail(currentUsernameInSession);
	return userInSession;
}

}