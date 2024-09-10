/**
 * 
 */
package fr.eni.encheres.bll;

import org.springframework.stereotype.Service;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.repository.UtilisateurDAO;

/**
 * Classe en charge de 
 * @projet : encheres - V1.0
 * @author : kim, ophélie, alex
 * @since: 10 sept. 2024 - 10:44:59
 */
@Service
public class UtilisateurServiceImpl implements UtilisateurService{

	private UtilisateurDAO utilisateurDAO;
	
	
	/**
	 * Constructeur.
	 * @param utilisateurDAO
	 */
	public UtilisateurServiceImpl(UtilisateurDAO utilisateurDAO) {
		super();
		this.utilisateurDAO = utilisateurDAO;
	}


	@Override
	public void addUser(Utilisateur utilisateur) {
		utilisateurDAO.create(utilisateur);
		
	}

}