/**
 * 
 */
package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.bo.Enchere;

/**
 * Classe en charge de 
 * @projet : encheres - V1.0
 * @author : kpatingre2024
 * @since: 13 sept. 2024 - 14:42:28
 */

public interface EnchereService {
	void addEnchere(Enchere enchere);
	List<Enchere> findAllEncheres();
	List<Enchere> findEnchereByNoArticle(Integer noArticle);
	List<Enchere> findEncheresByNoUser(Integer noUser);
	void modifyEnchere(Enchere enchere);
}