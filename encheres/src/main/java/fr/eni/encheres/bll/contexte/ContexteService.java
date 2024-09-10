/**
 * 
 */
package fr.eni.encheres.bll.contexte;

import fr.eni.encheres.bo.Utilisateur;

/**
 * Classe en charge de 
 * @projet : encheres - V1.0
 * @author : aferry2024
 * @since: 10 sept. 2024 - 16:51:13
 */
public interface ContexteService {
	public Utilisateur chargeEmail(String email);
	public Utilisateur chargePseudo(String pseudo);
	

}
