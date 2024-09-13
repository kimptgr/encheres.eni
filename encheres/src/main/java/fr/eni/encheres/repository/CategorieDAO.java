/**
 * 
 */
package fr.eni.encheres.repository;

import java.util.List;

import fr.eni.encheres.bo.Categorie;

/**
 * Classe en charge de 
 * @projet : encheres - V1.0
 * @author : kpatingre2024
 * @since: 11 sept. 2024 - 10:17:45
 */
public interface CategorieDAO {
	List<Categorie> readAll();

	Categorie readById(Integer id);
}
