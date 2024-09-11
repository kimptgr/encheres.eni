/**
 * 
 */
package fr.eni.encheres.controller.security;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

@GetMapping("/connecter")
public String chargeUserInSession(@ModelAttribute("userInSession") Utilisateur userInSession, Principal principal){
	String email = principal.getName();
	Utilisateur charge = contexteService.chargeEmail(email);
	if(charge != null) {
		userInSession.setNoUtilisateur(charge.getNoUtilisateur());
		userInSession.setPseudo(charge.getPseudo());
		userInSession.setNom(charge.getNom());
		userInSession.setPrenom(charge.getPrenom());
		userInSession.setAdministrateur(charge.getAdministrateur());
		
	}else {
		userInSession.setNoUtilisateur(0);
		userInSession.setPseudo(null);
		userInSession.setNom(null);
		userInSession.setPrenom(null);
		userInSession.setAdministrateur(false);
	}
	System.out.println(userInSession);
	return "redirect/:";
	
	
}
}