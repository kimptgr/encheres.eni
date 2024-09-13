/**
 * 
 */
package fr.eni.encheres.bll.contexte;

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
	public boolean chargeEmail(String email) {
		return utilisateurDAO.readByEmail(email);
	}

	@Override
	public Utilisateur chargePseudo(String pseudo) {
		return utilisateurDAO.readByPseudo(pseudo);
	}

}
