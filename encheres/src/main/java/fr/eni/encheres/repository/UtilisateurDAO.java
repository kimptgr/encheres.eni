/**
 * 
 */
package fr.eni.encheres.repository;

import java.util.List;

import fr.eni.encheres.bo.Utilisateur;

/**
 * Classe en charge de
 * 
 * @projet : encheres - V1.0
 * @author : aferry2024
 * @since: 9 sept. 2024 - 13:33:59
 */
public interface UtilisateurDAO {

	public void create(Utilisateur utilisateur);

	public Utilisateur readById(Integer noUtilisateur);

	public Utilisateur readByPseudo(String pseudo);
	public Utilisateur readByEmail(String email);

	public List<Utilisateur> readAll();

}
