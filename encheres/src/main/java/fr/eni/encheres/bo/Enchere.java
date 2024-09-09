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
	private Integer montant_enchere;

	private List<ArticleVendu> articleVendus;
	private Utilisateur utilisateur;

	/**
	 * Constructeur par défaut
	 */
	public Enchere() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructeur.
	 * 
	 * @param dateEnchere
	 * @param montant_enchere
	 * @param articleVendus
	 * @param utilisateur
	 */
	public Enchere(LocalDateTime dateEnchere, Integer montant_enchere, List<ArticleVendu> articleVendus,
			Utilisateur utilisateur) {
		super();
		this.dateEnchere = dateEnchere;
		this.montant_enchere = montant_enchere;
		this.articleVendus = articleVendus;
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
	public Integer getMontant_enchere() {
		return montant_enchere;
	}

	/**
	 * Setter pour the montant_enchere.
	 * 
	 * @param montant_enchere the montant_enchere to set
	 */
	public void setMontant_enchere(Integer montant_enchere) {
		this.montant_enchere = montant_enchere;
	}

	/**
	 * Getter for articleVendus.
	 * 
	 * @return the articleVendus
	 */
	public List<ArticleVendu> getArticleVendus() {
		return articleVendus;
	}

	/**
	 * Setter pour the articleVendus.
	 * 
	 * @param articleVendus the articleVendus to set
	 */
	public void setArticleVendus(List<ArticleVendu> articleVendus) {
		this.articleVendus = articleVendus;
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
		return "Enchere [dateEnchere=" + dateEnchere + ", montant_enchere=" + montant_enchere + ", articleVendus="
				+ articleVendus + ", utilisateur=" + utilisateur + "]";
	}

}
