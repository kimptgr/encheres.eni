/**
 * 
 */
package fr.eni.encheres.bo;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Classe en charge de la création de l'Enchere et de ses attributs
 * 
 * @projet : encheres - V1.0
 * @author : Kim, Ophélie, Alex
 * @since: 9 sept. 2024 - 11:29:50
 */
public class Enchere {
	private LocalDateTime dateEnchere;
	private Integer montantEnchere;

	private ArticleVendu articleVendu;
	private Utilisateur utilisateur;

	/**
	 * Constructeur par défaut
	 */
	public Enchere() {
	}

	/**
	 * Constructeur.
	 * 
	 * @param dateEnchere
	 * @param montant_enchere
	 * @param articleVendus
	 * @param utilisateur
	 */
	public Enchere(LocalDateTime dateEnchere, Integer montant_enchere, ArticleVendu articleVendu,
			Utilisateur utilisateur) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montant_enchere;
		this.articleVendu = articleVendu;
		this.utilisateur = utilisateur;
	}

	/**
	 * Getter for dateEnchere.
	 * 
	 * @return the dateEnchere
	 */
	public LocalDateTime getDateEnchere() {
		return dateEnchere;
	}

	/**
	 * Setter pour the dateEnchere.
	 * 
	 * @param dateEnchere the dateEnchere to set
	 */
	public void setDateEnchere(LocalDateTime dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	/**
	 * Getter for montant_enchere.
	 * 
	 * @return the montant_enchere
	 */
	public Integer getMontantEnchere() {
		return montantEnchere;
	}

	/**
	 * Setter pour the montantEnchere.
	 * 
	 * @param montant_enchere the montantEnchere to set
	 */
	public void setMontantEnchere(Integer montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	/**
	 * Getter for articleVendus.
	 * 
	 * @return the articleVendus
	 */
	public ArticleVendu getArticleVendu() {
		return articleVendu;
	}

	/**
	 * Setter pour the articleVendus.
	 * 
	 * @param articleVendus the articleVendus to set
	 */
	public void setArticleVendus(ArticleVendu articleVendu) {
		this.articleVendu = articleVendu;
	}

	/**
	 * Getter for utilisateur.
	 * 
	 * @return the utilisateur
	 */
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	/**
	 * Setter pour the utilisateur.
	 * 
	 * @param utilisateur the utilisateur to set
	 */
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	@Override
	public String toString() {
		return "Enchere [dateEnchere=" + dateEnchere + ", montant_enchere=" + montantEnchere + ", articleVendus="
				+ articleVendu + ", utilisateur=" + utilisateur + "]";
	}

}
