/**
 * 
 */
package fr.eni.encheres.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import fr.eni.encheres.bll.ArticleVenduService;
import fr.eni.encheres.bo.Categorie;

/**
 * Classe en charge de 
 * @projet : encheres - V1.0
 * @author : kpatingre2024
 * @since: 11 sept. 2024 - 14:42:00
 */
@Component
public class CategorieConverter implements Converter<String, Categorie> {
	private final ArticleVenduService articleVenduService;
	
	/**
	 * Constructeur.
	 * @param articleVenduService
	 */
	public CategorieConverter(ArticleVenduService articleVenduService) {
		this.articleVenduService = articleVenduService;
	}

	@Override
	public Categorie convert(String source) {
		return articleVenduService.findCategorieById(Integer.parseInt(source));
	}

}
