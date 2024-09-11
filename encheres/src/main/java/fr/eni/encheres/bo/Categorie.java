/**
 * 
 */
package fr.eni.encheres.bo;

import java.util.List;

/**
 * Classe en charge dede la création de Catégorie et de ses attributs
 * 
 * @projet : encheres - V1.0
 * @author : Kim, Ophélie, Alex
 * @since: 9 sept. 2024 - 11:38:26
 */
public class Categorie {
	private Integer noCategorie; // primary key identity
	private String libelle;
	
	private List<ArticleVendu> articleVendu;
	/**
	 * Constructeur apr défaut
	 */
	public Categorie() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Constructeur.
	 * @param noCategorie
	 * @param libelle
	 * @param articleVendu
	 */
	public Categorie(Integer noCategorie, String libelle, List<ArticleVendu> articleVendu) {
		super();
		this.noCategorie = noCategorie;
		this.libelle = libelle;
		this.articleVendu = articleVendu;
	}
	
	
	public Categorie(Integer noCategorie, String libelle) {
		super();
		this.noCategorie = noCategorie;
		this.libelle = libelle;
	}
	/**
	 * Getter for noCategorie.
	 * @return the noCategorie
	 */
	public Integer getNoCategorie() {
		return noCategorie;
	}
	/**
	 * Setter pour the noCategorie.
	 * @param noCategorie the noCategorie to set
	 */
	public void setNoCategorie(Integer noCategorie) {
		this.noCategorie = noCategorie;
	}
	/**
	 * Getter for libelle.
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}
	/**
	 * Setter pour the libelle.
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	/**
	 * Getter for articleVendu.
	 * @return the articleVendu
	 */
	public List<ArticleVendu> getArticleVendu() {
		return articleVendu;
	}
	/**
	 * Setter pour the articleVendu.
	 * @param articleVendu the articleVendu to set
	 */
	public void setArticleVendu(List<ArticleVendu> articleVendu) {
		this.articleVendu = articleVendu;
	}
	@Override
	public String toString() {
		return "Categorie [noCategorie=" + noCategorie + ", libelle=" + libelle + ", articleVendu=" + articleVendu
				+ "]";
	}

	
}
