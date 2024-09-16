/**
 * 
 */
package fr.eni.encheres.bll;

import fr.eni.encheres.bo.Utilisateur;

/**
 * Interface en charge de 
 * @projet : encheres - V1.0
 * @author : kim, ophélie, alex
 * @since: 10 sept. 2024 - 10:41:25
 */
public interface UtilisateurService {
	public void addUser(Utilisateur utilisateur);
	Utilisateur findByEmail(String email);
	Utilisateur findByPseudo(String pseudo);
}
