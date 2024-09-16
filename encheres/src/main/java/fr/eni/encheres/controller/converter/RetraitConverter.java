/**
 * 
 */
package fr.eni.encheres.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import fr.eni.encheres.bll.ArticleVenduService;

import fr.eni.encheres.bo.Retrait;

/**
 * Classe en charge de 
 * @projet : encheres - V1.0
 * @author : kpatingre2024
 * @since: 12 sept. 2024 - 13:58:56
 */
@Component
public class RetraitConverter implements Converter<String, Retrait> {
	private final ArticleVenduService articleVenduService;
	
	/**
	 * Constructeur.
	 * @param articleVenduService
	 */
	public RetraitConverter(ArticleVenduService articleVenduService) {
		this.articleVenduService = articleVenduService;
	}

	@Override
	public Retrait convert(String source) {
		return articleVenduService.findRetraitByNoArticle(Integer.parseInt(source));
	}

}