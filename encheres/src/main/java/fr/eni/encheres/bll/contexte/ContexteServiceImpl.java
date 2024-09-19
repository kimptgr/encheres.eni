/**
 * 
 */
package fr.eni.encheres.bll.contexte;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.repository.UtilisateurDAO;

/**
 * Classe en charge de 
 * @projet : encheres - V1.0
 * @author : aferry2024
 * @since: 10 sept. 2024 - 16:53:09
 */
@Service
public class ContexteServiceImpl implements ContexteService {

	private UtilisateurDAO utilisateurDAO;
	
	
	/**
	 * Constructeur.
	 * @param utilisateurDAO
	 */
	public ContexteServiceImpl(UtilisateurDAO utilisateurDAO) {
		super();
		this.utilisateurDAO = utilisateurDAO;
	}

	@Override
	public Utilisateur chargeEmail(String email) {
		return utilisateurDAO.readByEmail(email);
	}

	@Override
	public Utilisateur chargePseudo(String pseudo) {
		return utilisateurDAO.readByPseudo(pseudo);
	} 
	
	@Override
	public Utilisateur getUserInSession() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 if (authentication != null && authentication.isAuthenticated() && 
			        !(authentication.getPrincipal() instanceof String && authentication.getPrincipal().equals("anonymousUser"))) {
		String currentUsernameInSession = authentication.getName();
		Utilisateur userInSession = chargePseudo(currentUsernameInSession);
		return userInSession; }
		 else return null;
	}

}
