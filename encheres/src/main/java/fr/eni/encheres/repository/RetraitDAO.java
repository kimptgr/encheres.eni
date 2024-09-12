/**
 * 
 */
package fr.eni.encheres.repository;

import fr.eni.encheres.bo.Retrait;

/**
 * Classe en charge de 
 * @projet : encheres - V1.0
 * @author : kpatingre2024
 * @since: 12 sept. 2024 - 14:05:15
 */
public interface RetraitDAO {

	/**
	 * Méthode en charge de
	 * @param noArticle
	 * @return
	 */
Retrait readByNoArticle(Integer noArticle);

}
