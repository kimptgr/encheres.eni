/**
 * 
 */
package fr.eni.encheres.repository;

import java.util.List;

import fr.eni.encheres.bo.Enchere;

/**
 * Classe en charge de 
 * @projet : encheres - V1.0
 * @author : kpatingre2024
 * @since: 13 sept. 2024 - 14:43:24
 */
public interface EnchereDAO {
	void createEnchere(Enchere enchere);
	List<Enchere> readAllEncheres();
	List<Enchere> readEncheresByNoArticle(Integer noArticle);
	void updateEnchere(Enchere enchere);
	List<Enchere> readEncheresByNoUser(Integer noUser);
}
