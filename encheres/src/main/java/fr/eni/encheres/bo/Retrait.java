/**
 * 
 */
package fr.eni.encheres.bo;

import jakarta.validation.constraints.NotBlank;

/**
 * Classe en charge de la création de Retrait et de ses attributs
 * 
 * @projet : encheres - V1.0
 * @author : Kim, Ophélie, Alex
 * @since: 9 sept. 2024 - 11:33:55
 */
public class Retrait {
	@NotBlank
	private String rue;
	@NotBlank
	private String codePostal;
	@NotBlank
	private String ville;
	
	private ArticleVendu articleVendu;
	
/**
 * Constructeur par défaut
 */
public Retrait() {
}

/**
 * Constructeur.
 * @param rue
 * @param codePostal
 * @param ville
 */
public Retrait(String rue, String codePostal, String ville) {
	super();
	this.rue = rue;
	this.codePostal = codePostal;
	this.ville = ville;
}



/**
 * Constructeur.
 * @param rue
 * @param codePostal
 * @param ville
 * @param articleVendu
 */
public Retrait(String rue, String codePostal, String ville, ArticleVendu articleVendu) {
	this.rue = rue;
	this.codePostal = codePostal;
	this.ville = ville;
	this.articleVendu = articleVendu;
}

/**
 * Getter for rue.
 * @return the rue
 */
public String getRue() {
	return rue;
}

/**
 * Setter pour the rue.
 * @param rue the rue to set
 */
public void setRue(String rue) {
	this.rue = rue;
}

/**
 * Getter for codePostal.
 * @return the codePostal
 */
public String getCodePostal() {
	return codePostal;
}

/**
 * Setter pour the codePostal.
 * @param codePostal the codePostal to set
 */
public void setCodePostal(String codePostal) {
	this.codePostal = codePostal;
}

/**
 * Getter for ville.
 * @return the ville
 */
public String getVille() {
	return ville;
}

/**
 * Setter pour the ville.
 * @param ville the ville to set
 */
public void setVille(String ville) {
	this.ville = ville;
}

/**
 * Getter for articleVendu.
 * @return the articleVendu
 */
public ArticleVendu getArticleVendu() {
	return articleVendu;
}

/**
 * Setter pour the articleVendu.
 * @param articleVendu the articleVendu to set
 */
public void setArticleVendu(ArticleVendu articleVendu) {
	this.articleVendu = articleVendu;
}

@Override
public String toString() {
	return "Retrait [rue=" + rue + ", codePostal=" + codePostal + ", ville=" + ville + ", articleVendu=" + articleVendu
			+ "]";
}


}
