/**
 * 
 */
package fr.eni.encheres.bll;

import org.springframework.stereotype.Service;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.repository.UtilisateurDAO;

/**
 * Classe en charge de
 * 
 * @projet : encheres - V1.0
 * @author : kim, ophélie, alex
 * @since: 10 sept. 2024 - 10:44:59
 */
@Service
public class UtilisateurServiceImpl implements UtilisateurService {

	/**
	 * 
	 */
	private static final int CREDIT = 200;
	private UtilisateurDAO utilisateurDAO;

	/**
	 * Constructeur.
	 * 
	 * @param utilisateurDAO
	 */
	public UtilisateurServiceImpl(UtilisateurDAO utilisateurDAO) {
		super();
		this.utilisateurDAO = utilisateurDAO;
	}

	@Override
	public void addUser(Utilisateur utilisateur) {
		utilisateur.setCredit(CREDIT);
		utilisateur.setAdministrateur(false);
		utilisateurDAO.create(utilisateur);

	}

	@Override

	public boolean verifByEmail(String email) {
		return utilisateurDAO.verifByEmail(email);
	}

	@Override
	public Utilisateur findByEmail(String email) {
		return utilisateurDAO.readByEmail(email);
	}

	@Override
	public Utilisateur findByPseudo(String pseudo) {
		return utilisateurDAO.readByPseudo(pseudo);
	}

	@Override
	public Utilisateur findById(Integer noUtilisateur) {
		return utilisateurDAO.readById(noUtilisateur);
	}

}
