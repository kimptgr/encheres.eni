package fr.eni.encheres.bo;

import java.time.LocalDate;

/**
 * Classe en charge de la création de l'ArticleVendu et de ses attributs
 * 
 * @projet : encheres - V1.0
 * @author : Kim, Ophélie, Alex
 * @since: 9 sept. 2024 - 11:20:43
 */
public class ArticleVendu {
	private Integer noArticle; // primary key identity
	private String nomArticle;
	private String description;
	private LocalDate dateDebutEncheres;
	private LocalDate dateFinEncheres;
	private Integer miseAPrix; // n'apparait pas dans la table ARTICLES_VENDUS
	private Integer prixVente;
	private Integer etatVente;

	private Retrait retrait;
	private Categorie categorie;
	private Enchere enchere;
	private Utilisateur acheteur;
	private Utilisateur vendeur;

	/**
	 * Constructeur par défaut
	 */
	public ArticleVendu() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructeur.
	 * 
	 * @param noArticle
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param miseAPrix
	 * @param prixVente
	 * @param etatVente
	 * @param retrait
	 * @param categorie
	 * @param enchere
	 * @param acheteur
	 * @param vendeur
	 */
	public ArticleVendu(Integer noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, Integer miseAPrix, Integer prixVente, Integer etatVente, Retrait retrait,
			Categorie categorie, Enchere enchere, Utilisateur acheteur, Utilisateur vendeur) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.retrait = retrait;
		this.categorie = categorie;
		this.enchere = enchere;
		this.acheteur = acheteur;
		this.vendeur = vendeur;
	}

	/**
	 * Getter for noArticle.
	 * 
	 * @return the noArticle
	 */
	public Integer getNoArticle() {
		return noArticle;
	}

	/**
	 * Setter pour the noArticle.
	 * 
	 * @param noArticle the noArticle to set
	 */
	public void setNoArticle(Integer noArticle) {
		this.noArticle = noArticle;
	}

	/**
	 * Getter for nomArticle.
	 * 
	 * @return the nomArticle
	 */
	public String getNomArticle() {
		return nomArticle;
	}

	/**
	 * Setter pour the nomArticle.
	 * 
	 * @param nomArticle the nomArticle to set
	 */
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	/**
	 * Getter for description.
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Setter pour the description.
	 * 
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Getter for dateDebutEncheres.
	 * 
	 * @return the dateDebutEncheres
	 */
	public LocalDate getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	/**
	 * Setter pour the dateDebutEncheres.
	 * 
	 * @param dateDebutEncheres the dateDebutEncheres to set
	 */
	public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}

	/**
	 * Getter for dateFinEncheres.
	 * 
	 * @return the dateFinEncheres
	 */
	public LocalDate getDateFinEncheres() {
		return dateFinEncheres;
	}

	/**
	 * Setter pour the dateFinEncheres.
	 * 
	 * @param dateFinEncheres the dateFinEncheres to set
	 */
	public void setDateFinEncheres(LocalDate dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}

	/**
	 * Getter for miseAPrix.
	 * 
	 * @return the miseAPrix
	 */
	public Integer getMiseAPrix() {
		return miseAPrix;
	}

	/**
	 * Setter pour the miseAPrix.
	 * 
	 * @param miseAPrix the miseAPrix to set
	 */
	public void setMiseAPrix(Integer miseAPrix) {
		this.miseAPrix = miseAPrix;
	}

	/**
	 * Getter for prixVente.
	 * 
	 * @return the prixVente
	 */
	public Integer getPrixVente() {
		return prixVente;
	}

	/**
	 * Setter pour the prixVente.
	 * 
	 * @param prixVente the prixVente to set
	 */
	public void setPrixVente(Integer prixVente) {
		this.prixVente = prixVente;
	}

	/**
	 * Getter for etatVente.
	 * 
	 * @return the etatVente
	 */
	public Integer getEtatVente() {
		return etatVente;
	}

	/**
	 * Setter pour the etatVente.
	 * 
	 * @param etatVente the etatVente to set
	 */
	public void setEtatVente(Integer etatVente) {
		this.etatVente = etatVente;
	}

	/**
	 * Getter for retrait.
	 * 
	 * @return the retrait
	 */
	public Retrait getRetrait() {
		return retrait;
	}

	/**
	 * Setter pour the retrait.
	 * 
	 * @param retrait the retrait to set
	 */
	public void setRetrait(Retrait retrait) {
		this.retrait = retrait;
	}

	/**
	 * Getter for categorie.
	 * 
	 * @return the categorie
	 */
	public Categorie getCategorie() {
		return categorie;
	}

	/**
	 * Setter pour the categorie.
	 * 
	 * @param categorie the categorie to set
	 */
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	/**
	 * Getter for enchere.
	 * 
	 * @return the enchere
	 */
	public Enchere getEnchere() {
		return enchere;
	}

	/**
	 * Setter pour the enchere.
	 * 
	 * @param enchere the enchere to set
	 */
	public void setEnchere(Enchere enchere) {
		this.enchere = enchere;
	}

	/**
	 * Getter for acheteur.
	 * 
	 * @return the acheteur
	 */
	public Utilisateur getAcheteur() {
		return acheteur;
	}

	/**
	 * Setter pour the acheteur.
	 * 
	 * @param acheteur the acheteur to set
	 */
	public void setAcheteur(Utilisateur acheteur) {
		this.acheteur = acheteur;
	}

	/**
	 * Getter for vendeur.
	 * 
	 * @return the vendeur
	 */
	public Utilisateur getVendeur() {
		return vendeur;
	}

	/**
	 * Setter pour the vendeur.
	 * 
	 * @param vendeur the vendeur to set
	 */
	public void setVendeur(Utilisateur vendeur) {
		this.vendeur = vendeur;
	}

	@Override
	public String toString() {
		return "ArticleVendu [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", miseAPrix="
				+ miseAPrix + ", prixVente=" + prixVente + ", etatVente=" + etatVente + ", retrait=" + retrait
				+ ", categorie=" + categorie + ", enchere=" + enchere + ", acheteur=" + acheteur + ", vendeur="
				+ vendeur + "]";
	}

}
