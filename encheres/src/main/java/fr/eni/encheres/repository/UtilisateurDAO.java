/**
 * 
 */
package fr.eni.encheres.repository;

import java.util.List;

import fr.eni.encheres.bo.Utilisateur;

/**
 * Interface en charge de
 * 
 * @projet : encheres - V1.0
 * @author : kim, ophélie, alex
 * @since: 9 sept. 2024 - 13:33:59
 */
public interface UtilisateurDAO {

	public void create(Utilisateur utilisateur);

	public Utilisateur readById(Integer noUtilisateur);

	public Utilisateur readByPseudo(String pseudo);

	public List<Utilisateur> readAll();

	
	boolean verifByEmail(String email);

	Utilisateur readByEmail(String email);

	
	

}
